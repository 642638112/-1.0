package com.musketeer.baselibrary.net;

import android.os.Build;

import com.musketeer.baselibrary.bean.ParamsEntity;
import com.musketeer.baselibrary.exception.NetErrorException;
import com.musketeer.baselibrary.util.StringUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhongxuqi on 15-10-25.
 */
public class DefaultHttpClient extends HttpClient {
    private static final int CONNECTTION_TIMEOUT = 5 * 1000;
    private static final int READ_TIMEOUT = 5 * 1000;
    private static final String BOUNDARY = java.util.UUID.randomUUID().toString();
    private static final String PREFIX = "--", LINEND = "\r\n";
    private static final String MULTIPART_FROM_DATA = "multipart/form-data";
    private static final String CHARSET = "UTF-8";

    private static final String DEFAULT_USER_AGENT = getDefaultUserAgent();
    private final Map<String, String> inMemoryCookies = new HashMap<>();

    private void initConn(HttpURLConnection conn) {
        conn.setConnectTimeout(CONNECTTION_TIMEOUT);
        conn.setReadTimeout(READ_TIMEOUT);
        conn.setAllowUserInteraction(false);
        conn.setInstanceFollowRedirects(false);
        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setRequestProperty("User-Agent", DEFAULT_USER_AGENT);
        conn.setRequestProperty("Connection", "close");
        conn.setRequestProperty("Accept-Charset", CHARSET);

        synchronized (inMemoryCookies) {
            StringBuilder strBuilder = new StringBuilder("");
            if (!StringUtils.isEmpty(getSessionId())) {
                strBuilder.append("JSESSIONID=").append(getSessionId());
            }
            for (Map.Entry<String, String> entry : inMemoryCookies.entrySet()) {
                if (strBuilder.length() != 0) {
                    strBuilder.append("; ");
                }
                strBuilder.append(entry.getKey()).append("=").append(entry.getValue());
            }
            conn.setRequestProperty("Cookie", strBuilder.toString());
        }
        conn.setRequestProperty("Connection", "close");
    }

    private void checkResponse(HttpURLConnection conn) throws IOException, NetErrorException{
        final int statusCode = conn.getResponseCode();
        if (statusCode != 200) {
            throw new NetErrorException("Expected status code " + statusCode + ", got " + statusCode);
        }
    }

    private void saveCookies(HttpURLConnection conn) {
        synchronized (inMemoryCookies) {
            final Map<String, List<String>> headerFields = conn.getHeaderFields();
            if (headerFields != null) {
                final List<String> newCookies = headerFields.get("Set-Cookie");
                if (newCookies != null) {
                    for (final String newCookie : newCookies) {
                        final String rawCookie = newCookie.split(";", 2)[0];
                        final int i = rawCookie.indexOf('=');
                        final String name = rawCookie.substring(0, i);
                        final String value = rawCookie.substring(i + 1);
                        inMemoryCookies.put(name, value);
                    }
                }
            }
        }
    }

    @Override
    public String doGet(String path, ParamsEntity params) throws Exception {
        URL url;
        if (params != null) {
            url = new URL(path + params.toRequestString());
        } else {

            url = new URL(path);
        }
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        initConn(conn);
        conn.setRequestMethod("GET");

        conn.connect();
        checkResponse(conn);
        saveCookies(conn);

        BufferedReader reader;
        if (isStatusCodeError(conn.getResponseCode())) {
            reader=new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        } else {
            reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
        }
        StringBuilder resultBuilder = new StringBuilder("");
        String line = null;
        while ((line = reader.readLine())!=null) {
            resultBuilder.append(line);
        }
        reader.close();
        conn.disconnect();

        return resultBuilder.toString();
    }

    @Override
    public String doPost(String path, ParamsEntity params) throws Exception {
        long length = 0;
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        initConn(conn);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("connection", "keep-alive");
        conn.setRequestProperty("Charsert", "UTF-8");
        conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);

        DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());

        // 首先组拼文本类型的参数
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.getMap().entrySet()) {
            sb.append(PREFIX);
            sb.append(BOUNDARY);
            sb.append(LINEND);
            sb.append("Content-Disposition: form-data; name=\"").append(entry.getKey()).append("\"" + LINEND);
            sb.append("Content-Type: text/plain; charset=" + CHARSET + LINEND);
            sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
            sb.append(LINEND);
            sb.append(entry.getValue());
            sb.append(LINEND);
        }
        length += sb.toString().getBytes().length;
        outStream.write(sb.toString().getBytes());

        // 发送文件数据
        for (Map.Entry<String, File> file : params.getMapFile().entrySet()) {
            StringBuilder sb1 = new StringBuilder();
            sb1.append(PREFIX);
            sb1.append(BOUNDARY);
            sb1.append(LINEND);
            // name是post中传参的键 filename是文件的名称
            //"Content-Disposition: form-data; name=\"file\"; name=\"" + file.getKey() + "\";filename=\"" + file.getKey() + "\"" + LINEND
            sb1.append("Content-Disposition: form-data; name=\"file\"; name=\"")
                    .append(file.getKey()).append("\";filename=\"").append(file.getKey()).append("\"" + LINEND);
            sb1.append("Content-Type: application/octet-stream; charset=" + CHARSET + LINEND);
            sb1.append(LINEND);
            outStream.write(sb1.toString().getBytes());

            InputStream is = new FileInputStream(file.getValue());
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
                length += len;
            }
            is.close();
            outStream.write(LINEND.getBytes());
            length += LINEND.getBytes().length;
        }

        // 请求结束标志
        byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
        outStream.write(end_data);
        length += end_data.length;
        conn.setRequestProperty("Content-Length", Long.toString(length));
        outStream.flush();

        conn.connect();
        checkResponse(conn);
        saveCookies(conn);

        InputStream in = conn.getInputStream();
        byte[] buffer = new byte[1024];
        int len;
        StringBuilder result = new StringBuilder();
        while ((len = in.read(buffer)) != -1) {
            result.append(new String(Arrays.copyOfRange(buffer, 0, len)));
        }
        outStream.close();
        conn.disconnect();

        return result.toString();
    }

    /**
     * Get the default Http User Agent for this client.
     */
    private static String getDefaultUserAgent() {
        return "PixmobHttpClient (" + Build.MANUFACTURER + " " + Build.MODEL + "; Android "
                + Build.VERSION.RELEASE + "/" + Build.VERSION.SDK_INT + ")";
    }

    private static boolean isStatusCodeError(int sc) {
        final int i = sc / 100;
        return i == 4 || i == 5;
    }
}
