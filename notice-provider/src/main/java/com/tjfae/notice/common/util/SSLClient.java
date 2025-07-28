// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.common.util;

import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ClientConnectionManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.scheme.SchemeSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.TrustManager;
import javax.net.ssl.SSLContext;
import org.apache.http.impl.client.DefaultHttpClient;
import java.security.cert.X509Certificate;

public class SSLClient extends DefaultHttpClient
{
    public SSLClient() throws Exception {
        final SSLContext ctx = SSLContext.getInstance("TLS");
        final X509TrustManager tm = new TrustAllTrustManager();
        ctx.init(null, new TrustManager[] { tm }, null);
        final SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        final ClientConnectionManager ccm = this.getConnectionManager();
        final SchemeRegistry sr = ccm.getSchemeRegistry();
        sr.register(new Scheme("https", 443, (SchemeSocketFactory)ssf));
    }
    
    private static class TrustAllTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
            // 信任所有客户端证书
        }
        
        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
            // 信任所有服务器证书
        }
        
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
}
