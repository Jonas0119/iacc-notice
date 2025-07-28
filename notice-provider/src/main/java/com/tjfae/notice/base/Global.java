// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.base;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;

/**
 * 全局工具类
 * 提供分页等通用功能
 */
@Component("noticeGlobal")  // 指定不同的Bean名称避免冲突
public class Global {
    
    // 日期格式化工具
    public static SimpleDateFormat DateFullFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat DateNoFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    
    /**
     * 开始分页
     * @param pageIndex 页码
     * @param pageSize 页大小
     */
    public static void startPage(int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
    }
}
