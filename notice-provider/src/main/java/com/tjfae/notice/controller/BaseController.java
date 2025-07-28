package com.tjfae.notice.controller;

import java.util.Set;
import java.beans.PropertyDescriptor;
import org.springframework.beans.BeanWrapper;
import java.util.HashSet;
import org.springframework.beans.BeanWrapperImpl;
import com.tjfae.common.core.web.domain.AjaxResult;
import com.github.pagehelper.PageInfo;
import com.tjfae.common.core.web.page.TableDataInfo;
import java.util.List;
import com.tjfae.common.core.web.page.PageDomain;
import com.github.pagehelper.PageHelper;
import com.tjfae.common.core.utils.sql.SqlUtil;
import com.tjfae.common.core.utils.StringUtils;
import com.tjfae.common.core.web.page.TableSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 基础控制器
 * 提供通用的分页、响应处理等功能
 */
public class BaseController {
    
    protected void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }
    
    protected void startPage1(Integer pageNum, Integer pageSize) {
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            PageHelper.startPage(pageNum, pageSize);
        }
    }
    
    protected TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(200);
        rspData.setRows(list);
        rspData.setMsg("查询成功");
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }
    
    protected AjaxResult toAjax(int rows) {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }
    
    protected String[] getNullPropertyNames(Object source) {
        BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}

/**
 * 系统健康检查控制器
 */
@RestController
class HealthController {
    
    @GetMapping("/health")
    public AjaxResult health() {
        return AjaxResult.success("系统运行正常");
    }
    
    @GetMapping("/")
    public AjaxResult index() {
        return AjaxResult.success("IACC司法存证通知公告系统已启动");
    }
}
