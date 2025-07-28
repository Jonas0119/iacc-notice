// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.common.util;

import javax.net.ssl.SSLSession;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;
import org.slf4j.LoggerFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import com.alibaba.fastjson.JSON;
import org.apache.http.client.methods.HttpPost;
import java.util.Date;
import java.util.Map;
import java.io.InputStream;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import java.security.SecureRandom;
import javax.net.ssl.TrustManager;
import javax.net.ssl.SSLContext;
import java.io.PrintWriter;
import java.net.URLConnection;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.ConnectException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import org.slf4j.Logger;

public class HttpUtils
{
    private static Logger log;
    
    public static String sendGet(final String url, final String param) {
        return sendGet(url, param, "UTF-8");
    }
    
    public static String sendGet(final String url, final String param, final String contentType) {
        final StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            final String urlNameString = url + "?" + param;
            HttpUtils.log.info("sendGet - {}", (Object)urlNameString);
            final URL realUrl = new URL(urlNameString);
            final URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), contentType));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            HttpUtils.log.info("recv - {}", (Object)result);
        }
        catch (final ConnectException e) {
            HttpUtils.log.error("\u8c03\u7528HttpUtils.sendGet ConnectException, url=" + url + ",param=" + param, (Throwable)e);
        }
        catch (final SocketTimeoutException e2) {
            HttpUtils.log.error("\u8c03\u7528HttpUtils.sendGet SocketTimeoutException, url=" + url + ",param=" + param, (Throwable)e2);
        }
        catch (final IOException e3) {
            HttpUtils.log.error("\u8c03\u7528HttpUtils.sendGet IOException, url=" + url + ",param=" + param, (Throwable)e3);
        }
        catch (final Exception e4) {
            HttpUtils.log.error("\u8c03\u7528HttpsUtil.sendGet Exception, url=" + url + ",param=" + param, (Throwable)e4);
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            }
            catch (final Exception ex) {
                HttpUtils.log.error("\u8c03\u7528in.close Exception, url=" + url + ",param=" + param, (Throwable)ex);
            }
        }
        return result.toString();
    }
    
    public static String sendPost(final String url, final String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        final StringBuilder result = new StringBuilder();
        try {
            final String urlNameString = url;
            HttpUtils.log.info("sendPost - {}", (Object)urlNameString);
            final URL realUrl = new URL(urlNameString);
            final URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            HttpUtils.log.info("recv - {}", (Object)result);
        }
        catch (final ConnectException e) {
            HttpUtils.log.error("\u8c03\u7528HttpUtils.sendPost ConnectException, url=" + url + ",param=" + param, (Throwable)e);
        }
        catch (final SocketTimeoutException e2) {
            HttpUtils.log.error("\u8c03\u7528HttpUtils.sendPost SocketTimeoutException, url=" + url + ",param=" + param, (Throwable)e2);
        }
        catch (final IOException e3) {
            HttpUtils.log.error("\u8c03\u7528HttpUtils.sendPost IOException, url=" + url + ",param=" + param, (Throwable)e3);
        }
        catch (final Exception e4) {
            HttpUtils.log.error("\u8c03\u7528HttpsUtil.sendPost Exception, url=" + url + ",param=" + param, (Throwable)e4);
        }
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            }
            catch (final IOException ex) {
                HttpUtils.log.error("\u8c03\u7528in.close Exception, url=" + url + ",param=" + param, (Throwable)ex);
            }
        }
        return result.toString();
    }
    
    public static String sendSSLPost(final String url, final String param) {
        final StringBuilder result = new StringBuilder();
        final String urlNameString = url + "?" + param;
        try {
            HttpUtils.log.info("sendSSLPost - {}", (Object)urlNameString);
            final SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[] { (TrustManager)new TrustAnyTrustManager() }, new SecureRandom());
            final URL console = new URL(urlNameString);
            final HttpsURLConnection conn = (HttpsURLConnection)console.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier((HostnameVerifier)new TrustAnyHostnameVerifier());
            conn.connect();
            final InputStream is = conn.getInputStream();
            final BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String ret = "";
            while ((ret = br.readLine()) != null) {
                if (ret != null && !"".equals(ret.trim())) {
                    result.append(new String(ret.getBytes("ISO-8859-1"), "utf-8"));
                }
            }
            HttpUtils.log.info("recv - {}", (Object)result);
            conn.disconnect();
            br.close();
        }
        catch (final ConnectException e) {
            HttpUtils.log.error("\u8c03\u7528HttpUtils.sendSSLPost ConnectException, url=" + url + ",param=" + param, (Throwable)e);
        }
        catch (final SocketTimeoutException e2) {
            HttpUtils.log.error("\u8c03\u7528HttpUtils.sendSSLPost SocketTimeoutException, url=" + url + ",param=" + param, (Throwable)e2);
        }
        catch (final IOException e3) {
            HttpUtils.log.error("\u8c03\u7528HttpUtils.sendSSLPost IOException, url=" + url + ",param=" + param, (Throwable)e3);
        }
        catch (final Exception e4) {
            HttpUtils.log.error("\u8c03\u7528HttpsUtil.sendSSLPost Exception, url=" + url + ",param=" + param, (Throwable)e4);
        }
        return result.toString();
    }
    
    public String sendJsonPost(final String requestName, final String url, final Map<String, Object> paramMap) {
        String result = null;
        final Date date = new Date();
        try {
            final HttpClient client = (HttpClient)new SSLClient();
            HttpUtils.log.info("\u6807\u8bc6\u3010" + date + "\u3011\u8bf7\u6c42\u8bf4\u660e\u3010" + requestName + "\u3011\u8bf7\u6c42\u8fde\u63a5\u3010" + url + "\u3011\u8bf7\u6c42\u5f00\u59cb\uff0c\u8bf7\u6c42\u53c2\u6570\u3010" + paramMap.toString() + "\u3011");
            final HttpPost post = new HttpPost(url);
            post.addHeader("Content-type", "application/json;charset=UTF-8");
            final StringEntity reqEntity = new StringEntity(JSON.toJSONString((Object)paramMap), "utf-8");
            reqEntity.setContentEncoding("UTF-8");
            reqEntity.setContentType("application/json");
            post.setEntity((HttpEntity)reqEntity);
            final RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1000).setConnectTimeout(1000).build();
            post.setConfig(requestConfig);
            final HttpResponse response = client.execute((HttpUriRequest)post);
            HttpUtils.log.info("\u6807\u8bc6\u3010" + date + "\u3011\u8bf7\u6c42\u5b8c\u6210\uff0c\u8fd4\u56de\u4ee3\u7801\u3010" + response.getStatusLine().getStatusCode() + "\u3011");
            if (response != null && response.getStatusLine().getStatusCode() == 200) {
                final HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "utf-8");
                }
                else {
                    HttpUtils.log.info("\u6807\u8bc6\u3010" + date + "\u3011\uff0c\u7ed3\u679c\u4e3anull");
                }
            }
            else {
                HttpUtils.log.info("\u6807\u8bc6\u3010" + date + "\u3011\u8fd4\u56de\u7ed3\u679c\u5f02\u5e38,\u8fd4\u56de\u4ee3\u7801\u3010" + response.getStatusLine().getStatusCode() + "\u3011");
            }
        }
        catch (final Exception ex) {
            HttpUtils.log.error("\u6807\u8bc6\u3010" + date + "\u3011\u8bf7\u6c42\u8bf4\u660e\u3010" + requestName + "\u3011\u8bf7\u6c42\u8fde\u63a5\u3010" + url + "\u3011\u8bf7\u6c42\u5f02\u5e38\u7ed3\u675f\uff0c\u8bf7\u6c42\u53c2\u6570\u3010" + paramMap.toString() + "\u3011\u8bf7\u6c42\u5f02\u5e38\uff0c\u5f02\u5e38\u539f\u56e0", (Throwable)ex);
        }
        return result;
    }
    
    static {
        HttpUtils.log = LoggerFactory.getLogger((Class)HttpUtils.class);
    }
    
    private static class TrustAnyTrustManager implements X509TrustManager
    {
        @Override
        public void checkClientTrusted(final X509Certificate[] chain, final String authType) {
        }
        
        @Override
        public void checkServerTrusted(final X509Certificate[] chain, final String authType) {
        }
        
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
    
    private static class TrustAnyHostnameVerifier implements HostnameVerifier
    {
        @Override
        public boolean verify(final String hostname, final SSLSession session) {
            return true;
        }
    }
}
