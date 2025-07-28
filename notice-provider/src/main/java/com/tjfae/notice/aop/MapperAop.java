// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.aop;

import java.util.Iterator;
import com.tjfae.common.core.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import com.tjfae.notice.annotation.SaveAll;
import org.aspectj.lang.annotation.Before;
import java.lang.reflect.Field;
import com.tjfae.notice.service.BaseInterface;
import com.tjfae.notice.annotation.Save;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Component
public class MapperAop
{
    @Autowired
    private ApplicationContext appContext;
    
    @Pointcut("@annotation(com.tjfae.notice.annotation.Save)")
    public void savePointCut() {
    }
    
    @Before("savePointCut() && @annotation(saveAnnotation)")
    public void beforeSave(final JoinPoint joinPoint, final Save saveAnnotation) throws Exception {
        final Object[] args = joinPoint.getArgs();
        final Class claz = args[0].getClass();
        final Field field = claz.getDeclaredField("id");
        field.setAccessible(true);
        final String fieldStr = (field.get(args[0]) == null) ? "0" : field.get(args[0]).toString();
        final String beanName = saveAnnotation.value();
        final Class classType = saveAnnotation.classType();
        final BaseInterface t = (BaseInterface)this.appContext.getBean(beanName, classType);
        final Object obj = t.findById(Long.valueOf(fieldStr));
        if (obj == null) {
            t.insert(args[0]);
        }
        else {
            t.update(args[0]);
        }
    }
    
    @Pointcut("@annotation(com.tjfae.notice.annotation.SaveAll)")
    public void saveAllPointCut() {
    }
    
    @Before("saveAllPointCut() && @annotation(saveAllAnnotation)")
    public void beforeSaveAll(final JoinPoint joinPoint, final SaveAll saveAllAnnotation) throws Exception {
        final Object[] args = joinPoint.getArgs();
        final String beanName = saveAllAnnotation.value();
        final Class classType = saveAllAnnotation.classType();
        final BaseInterface t = (BaseInterface)this.appContext.getBean(beanName, classType);
        final List temp = (List)args[0];
        final List needInsert = new ArrayList();
        final List needUpdate = new ArrayList();
        Field field = null;
        String fieldStr = "";
        for (final Object o : temp) {
            if (field == null) {
                final Class clz = o.getClass();
                field = clz.getDeclaredField("id");
                field.setAccessible(true);
            }
            fieldStr = ((field.get(o) == null) ? "" : field.get(o).toString());
            if (StringUtils.isEmpty(fieldStr) || "0".equals(fieldStr)) {
                needInsert.add(o);
            }
            else {
                needUpdate.add(o);
            }
        }
        if (!needInsert.isEmpty()) {
            t.insertBatch(needInsert);
        }
        if (!needUpdate.isEmpty()) {
            t.updateBatch(needUpdate);
        }
    }
}
