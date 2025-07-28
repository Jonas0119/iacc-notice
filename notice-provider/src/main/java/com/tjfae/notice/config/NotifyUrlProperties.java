// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "notify-url")
public class NotifyUrlProperties
{
    private List<Config> urlList;
    
    public Config getBySource(final String source) {
        final List<Config> configs = this.getUrlList();
        for (final Config config : configs) {
            if (config.getSource().equals(source)) {
                return config;
            }
        }
        return null;
    }
    
    public NotifyUrlProperties() {
        this.urlList = new ArrayList();
    }
    
    public List<Config> getUrlList() {
        return this.urlList;
    }
    
    public void setUrlList(final List<Config> urlList) {
        this.urlList = urlList;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof NotifyUrlProperties)) {
            return false;
        }
        final NotifyUrlProperties other = (NotifyUrlProperties)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        final Object this$urlList = this.getUrlList();
        final Object other$urlList = other.getUrlList();
        if (this$urlList == null) {
            if (other$urlList == null) {
                return true;
            }
        }
        else if (this$urlList.equals(other$urlList)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof NotifyUrlProperties;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $urlList = this.getUrlList();
        result = result * 59 + (($urlList == null) ? 43 : $urlList.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "NotifyUrlProperties(urlList=" + this.getUrlList() + ")";
    }
    
    public static class Config
    {
        private String source;
        private String statusNotify;
        private String urlNotify;
        private String whites;
        
        public String getSource() {
            return this.source;
        }
        
        public String getStatusNotify() {
            return this.statusNotify;
        }
        
        public String getUrlNotify() {
            return this.urlNotify;
        }
        
        public String getWhites() {
            return this.whites;
        }
        
        public void setSource(final String source) {
            this.source = source;
        }
        
        public void setStatusNotify(final String statusNotify) {
            this.statusNotify = statusNotify;
        }
        
        public void setUrlNotify(final String urlNotify) {
            this.urlNotify = urlNotify;
        }
        
        public void setWhites(final String whites) {
            this.whites = whites;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Config)) {
                return false;
            }
            final Config other = (Config)o;
            if (!other.canEqual((Object)this)) {
                return false;
            }
            final Object this$source = this.getSource();
            final Object other$source = other.getSource();
            Label_0065: {
                if (this$source == null) {
                    if (other$source == null) {
                        break Label_0065;
                    }
                }
                else if (this$source.equals(other$source)) {
                    break Label_0065;
                }
                return false;
            }
            final Object this$statusNotify = this.getStatusNotify();
            final Object other$statusNotify = other.getStatusNotify();
            Label_0102: {
                if (this$statusNotify == null) {
                    if (other$statusNotify == null) {
                        break Label_0102;
                    }
                }
                else if (this$statusNotify.equals(other$statusNotify)) {
                    break Label_0102;
                }
                return false;
            }
            final Object this$urlNotify = this.getUrlNotify();
            final Object other$urlNotify = other.getUrlNotify();
            Label_0139: {
                if (this$urlNotify == null) {
                    if (other$urlNotify == null) {
                        break Label_0139;
                    }
                }
                else if (this$urlNotify.equals(other$urlNotify)) {
                    break Label_0139;
                }
                return false;
            }
            final Object this$whites = this.getWhites();
            final Object other$whites = other.getWhites();
            if (this$whites == null) {
                if (other$whites == null) {
                    return true;
                }
            }
            else if (this$whites.equals(other$whites)) {
                return true;
            }
            return false;
        }
        
        protected boolean canEqual(final Object other) {
            return other instanceof Config;
        }
        
        @Override
        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $source = this.getSource();
            result = result * 59 + (($source == null) ? 43 : $source.hashCode());
            final Object $statusNotify = this.getStatusNotify();
            result = result * 59 + (($statusNotify == null) ? 43 : $statusNotify.hashCode());
            final Object $urlNotify = this.getUrlNotify();
            result = result * 59 + (($urlNotify == null) ? 43 : $urlNotify.hashCode());
            final Object $whites = this.getWhites();
            result = result * 59 + (($whites == null) ? 43 : $whites.hashCode());
            return result;
        }
        
        @Override
        public String toString() {
            return "NotifyUrlProperties.Config(source=" + this.getSource() + ", statusNotify=" + this.getStatusNotify() + ", urlNotify=" + this.getUrlNotify() + ", whites=" + this.getWhites() + ")";
        }
    }
}
