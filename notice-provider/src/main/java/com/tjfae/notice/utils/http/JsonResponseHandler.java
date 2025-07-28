// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.utils.http;

import java.io.IOException;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import com.tjfae.notice.utils.json.JsonUtils;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;

public class JsonResponseHandler
{
    public static <T> ResponseHandler<T> createResponseHandler(final Class<T> clazz) {
        return (ResponseHandler<T>)new JsonResponseHandler.JsonResponseHandlerImpl((Class)clazz);
    }
    
    public static class JsonResponseHandlerImpl<T> implements ResponseHandler<T>
    {
        private Class<T> clazz;
        
        public JsonResponseHandlerImpl(final Class<T> clazz) {
            this.clazz = clazz;
        }
        
        public T handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
            final int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                final HttpEntity entity = response.getEntity();
                final String str = EntityUtils.toString(entity, "utf-8");
                return (T)JsonUtils.jsonStr2Object(str, this.clazz);
            }
            final String str2 = EntityUtils.toString(response.getEntity(), "utf-8");
            Map<String, Object> res = null;
            try {
                res = JsonUtils.convertJson2Map(str2);
            }
            catch (final Exception e) {
                throw new ClientProtocolException("Unexpected response status: " + status + " response:" + str2);
            }
            if (res.containsKey("error")) {
                final String str3 = res.get("error").toString();
                throw new ClientProtocolException(str3);
            }
            throw new ClientProtocolException(str2);
        }
    }
}
