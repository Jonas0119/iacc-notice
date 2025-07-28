// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.utils.http;

import org.slf4j.LoggerFactory;
import org.apache.http.entity.mime.HttpMultipartMode;
import java.nio.charset.Charset;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.ContentType;
import com.tjfae.notice.utils.string.StringUtils;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.springframework.web.multipart.MultipartFile;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.Consts;
import java.util.Iterator;
import org.apache.http.client.methods.RequestBuilder;
import java.util.Map;
import org.apache.http.protocol.HttpContext;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;

public class MyHttpClient
{
    private static Logger logger;
    private static int timeout;
    private static int retryExecutionCount;
    protected static CloseableHttpClient httpsClient;
    protected static CloseableHttpClient httpClient;
    
    public static void setTimeout(final int timeout) {
        MyHttpClient.timeout = timeout;
    }
    
    public static void setRetryExecutionCount(final int retryExecutionCount) {
        MyHttpClient.retryExecutionCount = retryExecutionCount;
    }
    
    public static <T> T execute(final HttpUriRequest request, final ResponseHandler<T> responseHandler) throws Exception {
        try {
            T t = null;
            if (isHttps(request)) {
                t = (T)MyHttpClient.httpsClient.execute(request, (ResponseHandler)responseHandler, (HttpContext)HttpClientContext.create());
            }
            else {
                t = (T)MyHttpClient.httpClient.execute(request, (ResponseHandler)responseHandler, (HttpContext)HttpClientContext.create());
            }
            return t;
        }
        catch (final Exception e) {
            e.printStackTrace();
            MyHttpClient.logger.error(e.getMessage());
            throw e;
        }
    }
    
    public static <T> T execute(final String uri, final Map<String, Object> params, final String method, final ResponseHandler<T> responseHandler) throws Exception {
        if ("post".equals(method)) {
            return (T)execute(uri, (Map)params, (ResponseHandler)responseHandler);
        }
        final RequestBuilder requestBuilder = RequestBuilder.get().setUri(uri);
        if (params != null) {
            for (final Map.Entry<String, Object> entry : params.entrySet()) {
                requestBuilder.addParameter((String)entry.getKey(), entry.getValue().toString());
            }
        }
        return (T)execute(requestBuilder.build(), (ResponseHandler)responseHandler);
    }
    
    public static <T> T execute(final String uri, final Map<String, Object> params, final ResponseHandler<T> responseHandler) throws Exception {
        final RequestBuilder requestBuilder = RequestBuilder.post().setUri(uri);
        if (params != null) {
            for (final Map.Entry<String, Object> entry : params.entrySet()) {
                requestBuilder.addParameter((String)entry.getKey(), entry.getValue().toString());
            }
        }
        return (T)execute(requestBuilder.build(), (ResponseHandler)responseHandler);
    }
    
    public static <T> T execute(final String uri, final String data, final ResponseHandler<T> responseHandler) throws Exception {
        final RequestBuilder requestBuilder = RequestBuilder.post().setUri(uri);
        final StringEntity stringEntity = new StringEntity(data, Consts.UTF_8);
        stringEntity.setContentType("application/json");
        requestBuilder.setEntity((HttpEntity)stringEntity);
        return (T)execute(requestBuilder.build(), (ResponseHandler)responseHandler);
    }
    
    public static <T> T execute(final String uri, final HttpEntity data, final ResponseHandler<T> responseHandler) throws Exception {
        final RequestBuilder requestBuilder = RequestBuilder.post().setUri(uri);
        requestBuilder.setEntity(data);
        return (T)execute(requestBuilder.build(), (ResponseHandler)responseHandler);
    }
    
    public static <T> T execute(final String uri, final String data, final Map<String, String> params, final String method, final String mediaType, final MultipartFile file, final String fileParamName, final ResponseHandler<T> responseHandler) throws Exception {
        return (T)execute(uri, (Object)data, (Map)params, (Map)null, method, mediaType, file, fileParamName, (ResponseHandler)responseHandler);
    }
    
    public static <T> T execute(final String uri, final Object data, final Map<String, String> params, final Map<String, Object> headers, final String method, final String mediaType, final MultipartFile file, final String fileParamName, final ResponseHandler<T> responseHandler) throws Exception {
        RequestBuilder requestBuilder = null;
        ContentType contentType = null;
        if ("post".equals(method)) {
            requestBuilder = RequestBuilder.post().setUri(uri);
        }
        else if ("get".equals(method)) {
            requestBuilder = RequestBuilder.get().setUri(uri);
        }
        if (data != null) {
            requestBuilder.setEntity((HttpEntity)data);
        }
        if (StringUtils.isNotBlank(mediaType)) {
            contentType = ContentType.create(mediaType);
        }
        else {
            contentType = ContentType.APPLICATION_FORM_URLENCODED;
        }
        if (file != null) {
            final MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setCharset(Charset.forName("utf-8"));
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.addBinaryBody(fileParamName, file.getInputStream(), contentType, file.getOriginalFilename());
            if (params != null) {
                for (final Map.Entry<String, String> entry : params.entrySet()) {
                    builder.addTextBody((String)entry.getKey(), (String)entry.getValue());
                }
            }
            final HttpEntity entity = builder.build();
            requestBuilder.setEntity(entity);
        }
        else if (params != null) {
            for (final Map.Entry<String, String> entry2 : params.entrySet()) {
                requestBuilder.addParameter((String)entry2.getKey(), (String)entry2.getValue());
            }
        }
        if (headers != null) {
            for (final Map.Entry<String, Object> entry3 : headers.entrySet()) {
                requestBuilder.addHeader((String)entry3.getKey(), entry3.getValue().toString());
            }
        }
        return (T)execute(requestBuilder.build(), (ResponseHandler)responseHandler);
    }
    
    private static boolean isHttps(final HttpUriRequest request) {
        final String uriScheme = request.getURI().getScheme();
        return !StringUtils.isBlank(uriScheme) && uriScheme.contains("https");
    }
    
    public static String getImageContentTypeByExt(final String ext) {
        if (StringUtils.isBlank(ext)) {
            return ContentType.IMAGE_JPEG.toString();
        }
        if ("jpeg".equalsIgnoreCase(ext) || "jpg".equalsIgnoreCase(ext)) {
            return ContentType.IMAGE_JPEG.toString();
        }
        if ("png".equalsIgnoreCase(ext)) {
            return ContentType.IMAGE_PNG.toString();
        }
        if ("bmp".equalsIgnoreCase(ext)) {
            return ContentType.IMAGE_BMP.toString();
        }
        if ("gif".equalsIgnoreCase(ext)) {
            return ContentType.IMAGE_GIF.toString();
        }
        if ("svg".equalsIgnoreCase(ext)) {
            return ContentType.IMAGE_SVG.toString();
        }
        if ("tiff".equalsIgnoreCase(ext)) {
            return ContentType.IMAGE_TIFF.toString();
        }
        if ("webp".equalsIgnoreCase(ext)) {
            return ContentType.IMAGE_WEBP.toString();
        }
        return null;
    }
    
    static {
        MyHttpClient.logger = LoggerFactory.getLogger((Class)MyHttpClient.class);
        MyHttpClient.timeout = 16000;
        MyHttpClient.retryExecutionCount = 2;
        MyHttpClient.httpsClient = HttpClientFactory.createHttpClient(100, 10, MyHttpClient.timeout, MyHttpClient.retryExecutionCount, true);
        MyHttpClient.httpClient = HttpClientFactory.createHttpClient(100, 10, MyHttpClient.timeout, MyHttpClient.retryExecutionCount, false);
    }
}
