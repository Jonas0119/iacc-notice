// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.common.exception;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.tjfae.common.core.exception.BusinessException;
import com.tjfae.common.core.web.domain.AjaxResult;
import org.slf4j.Logger;

public class GlobalExceptionHandler
{
    private static final Logger log;
    
    @ExceptionHandler({ Exception.class })
    @ResponseStatus(HttpStatus.OK)
    public AjaxResult exceptionHandler(final Exception exception) {
        GlobalExceptionHandler.log.error("\u3010\u62a5\u9519\u4fe1\u606f\u3011\uff1a{},{}", (Object)exception.getMessage(), (Object)exception);
        if (exception instanceof BusinessException) {
            return AjaxResult.error(exception.getMessage());
        }
        return AjaxResult.error("\u7f51\u7edc\u5f02\u5e38\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5");
    }
    
    static {
        log = LoggerFactory.getLogger((Class)GlobalExceptionHandler.class);
    }
}
