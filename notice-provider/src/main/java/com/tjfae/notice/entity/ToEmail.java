// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.entity;

import java.util.Arrays;

public class ToEmail
{
    private String[] tos;
    private String subject;
    private String content;
    
    public ToEmail() {
        this.tos = new String[1];
        this.content = "";
    }
    
    public ToEmail(final String to, final String subject) {
        this(to, subject, "");
    }
    
    public ToEmail(final String to, final String subject, final String content) {
        this.tos = new String[1];
        this.content = "";
        this.tos[0] = to;
        this.subject = subject;
        this.content = content;
    }
    
    public String[] getTos() {
        return this.tos;
    }
    
    public String getSubject() {
        return this.subject;
    }
    
    public String getContent() {
        return this.content;
    }
    
    public void setTos(final String[] tos) {
        this.tos = tos;
    }
    
    public void setSubject(final String subject) {
        this.subject = subject;
    }
    
    public void setContent(final String content) {
        this.content = content;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ToEmail)) {
            return false;
        }
        final ToEmail other = (ToEmail)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (!Arrays.deepEquals(this.getTos(), other.getTos())) {
            return false;
        }
        final Object this$subject = this.getSubject();
        final Object other$subject = other.getSubject();
        Label_0081: {
            if (this$subject == null) {
                if (other$subject == null) {
                    break Label_0081;
                }
            }
            else if (this$subject.equals(other$subject)) {
                break Label_0081;
            }
            return false;
        }
        final Object this$content = this.getContent();
        final Object other$content = other.getContent();
        if (this$content == null) {
            if (other$content == null) {
                return true;
            }
        }
        else if (this$content.equals(other$content)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ToEmail;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + Arrays.deepHashCode(this.getTos());
        final Object $subject = this.getSubject();
        result = result * 59 + (($subject == null) ? 43 : $subject.hashCode());
        final Object $content = this.getContent();
        result = result * 59 + (($content == null) ? 43 : $content.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "ToEmail(tos=" + Arrays.deepToString(this.getTos()) + ", subject=" + this.getSubject() + ", content=" + this.getContent() + ")";
    }
}
