// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.TYPE, ElementType.METHOD })
@Documented
public @interface RequestValidate {
    String type() default "";
    
    Class classType();
}
