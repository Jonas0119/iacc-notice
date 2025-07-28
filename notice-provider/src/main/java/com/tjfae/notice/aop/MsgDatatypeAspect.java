// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.aop;

import org.slf4j.LoggerFactory;
import com.tjfae.common.core.exception.BusinessException;
import org.aspectj.lang.annotation.Before;
import com.tjfae.notice.annotation.RequestValidate;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Component
public class MsgDatatypeAspect
{
    private static final Logger logger;
    
    @Pointcut("@annotation(com.tjfae.notice.annotation.RequestValidate)")
    public void validatePointCut() {
    }
    
    @Before("validatePointCut() && @annotation(requestValid)")
    public void doBefore(final JoinPoint joinPoint, final RequestValidate requestValid) throws Exception {
        this.handleDataType(joinPoint, requestValid);
    }
    
    protected void handleDataType(final JoinPoint joinPoint, final RequestValidate requestValid) throws Exception {
        final Object[] args = joinPoint.getArgs();
        for (int pos = 0; pos < args.length; ++pos) {
            if (requestValid.classType().getName().equals(args[pos].getClass().getName())) {
                try {
                    final boolean result = (boolean)args[pos].getClass().getMethod("validate", (Class<?>[])new Class[0]).invoke(args[pos], new Object[0]);
                    if (!result) {
                        throw new BusinessException("validate parameters failed.");
                    }
                }
                catch (final Exception e) {
                    throw new BusinessException(e.getMessage());
                }
            }
        }
    }
    
    static {
        logger = LoggerFactory.getLogger((Class)MsgDatatypeAspect.class);
    }
}
