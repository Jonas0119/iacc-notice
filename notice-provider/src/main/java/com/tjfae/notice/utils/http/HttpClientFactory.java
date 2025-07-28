// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.utils.http;

import org.apache.http.HttpRequest;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.client.protocol.HttpClientContext;
import javax.net.ssl.SSLException;
import org.apache.http.conn.ConnectTimeoutException;
import java.net.UnknownHostException;
import java.io.InterruptedIOException;
import org.apache.http.protocol.HttpContext;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.UnrecoverableKeyException;
import java.security.KeyStore;
import javax.net.ssl.SSLContext;
import java.security.NoSuchAlgorithmException;
import java.security.KeyManagementException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;

public class HttpClientFactory
{
    private static String[] supportedProtocols;
    
    public static CloseableHttpClient createHttpClient(final int maxTotal, final int maxPerRoute, final int timeout, final int retryExecutionCount, final boolean isHttps) {
        try {
            if (isHttps) {
                final SSLContext sslContext = SSLContexts.custom().useSSL().build();
                final SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                final PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
                poolingHttpClientConnectionManager.setMaxTotal(maxTotal);
                poolingHttpClientConnectionManager.setDefaultMaxPerRoute(maxPerRoute);
                final SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(timeout).build();
                poolingHttpClientConnectionManager.setDefaultSocketConfig(socketConfig);
                return HttpClientBuilder.create().setConnectionManager((HttpClientConnectionManager)poolingHttpClientConnectionManager).setSSLSocketFactory((LayeredConnectionSocketFactory)sf).setRetryHandler((HttpRequestRetryHandler)new HttpRequestRetryHandlerImpl(retryExecutionCount)).build();
            }
            return HttpClientBuilder.create().setRetryHandler((HttpRequestRetryHandler)new HttpRequestRetryHandlerImpl(retryExecutionCount)).build();
        }
        catch (final KeyManagementException e) {
            e.printStackTrace();
        }
        catch (final NoSuchAlgorithmException e2) {
            e2.printStackTrace();
        }
        return null;
    }
    
    public static CloseableHttpClient createKeyMaterialHttpClient(final KeyStore keystore, final String keyPassword, final int timeout, final int retryExecutionCount) {
        return createKeyMaterialHttpClient(keystore, keyPassword, HttpClientFactory.supportedProtocols, timeout, retryExecutionCount);
    }
    
    public static CloseableHttpClient createKeyMaterialHttpClient(final KeyStore keystore, final String keyPassword, final String[] supportedProtocols, final int timeout, final int retryExecutionCount) {
        try {
            final SSLContext sslContext = SSLContexts.custom().useSSL().loadKeyMaterial(keystore, keyPassword.toCharArray()).build();
            final SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(sslContext, supportedProtocols, (String[])null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            final SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(timeout).build();
            return HttpClientBuilder.create().setDefaultSocketConfig(socketConfig).setSSLSocketFactory((LayeredConnectionSocketFactory)sf).setRetryHandler((HttpRequestRetryHandler)new HttpRequestRetryHandlerImpl(retryExecutionCount)).build();
        }
        catch (final KeyManagementException e) {
            e.printStackTrace();
        }
        catch (final NoSuchAlgorithmException e2) {
            e2.printStackTrace();
        }
        catch (final UnrecoverableKeyException e3) {
            e3.printStackTrace();
        }
        catch (final KeyStoreException e4) {
            e4.printStackTrace();
        }
        return null;
    }
    
    static {
        HttpClientFactory.supportedProtocols = new String[] { "TLSv1" };
    }
    
    private static class HttpRequestRetryHandlerImpl implements HttpRequestRetryHandler
    {
        private int retryExecutionCount;
        
        public HttpRequestRetryHandlerImpl(final int retryExecutionCount) {
            this.retryExecutionCount = retryExecutionCount;
        }
        
        public boolean retryRequest(final IOException exception, final int executionCount, final HttpContext context) {
            if (executionCount > this.retryExecutionCount) {
                return false;
            }
            if (exception instanceof InterruptedIOException) {
                return false;
            }
            if (exception instanceof UnknownHostException) {
                return false;
            }
            if (exception instanceof ConnectTimeoutException) {
                return true;
            }
            if (exception instanceof SSLException) {
                return false;
            }
            final HttpClientContext clientContext = HttpClientContext.adapt(context);
            final HttpRequest request = clientContext.getRequest();
            final boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
            return idempotent;
        }
    }
}
