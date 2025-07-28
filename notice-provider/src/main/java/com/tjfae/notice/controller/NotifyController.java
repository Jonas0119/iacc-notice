// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.controller;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;
import io.swagger.annotations.ApiOperation;
import com.tjfae.common.log.annotation.TjfaeLog;
import com.tjfae.notice.entity.NoticePublish;
import com.tjfae.common.core.web.domain.AjaxResult;
import org.springframework.web.bind.annotation.PathVariable;
import javax.annotation.Resource;
import com.tjfae.notice.service.impl.ArbitrateServiceImpl;
import org.slf4j.Logger;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;

@Api(tags = { "\u901a\u77e5\u63a5\u53e3" })
@RestController
@RequestMapping({ "/notify" })
@RefreshScope
public class NotifyController extends BaseController
{
    private static final Logger log;
    @Resource
    private ArbitrateServiceImpl arbitrateService;
    
    @TjfaeLog
    @ApiOperation(value = "\u516c\u793a\u516c\u544a\u5ba1\u6838\u7ed3\u679c\u901a\u77e5", produces = "application/json")
    @RequestMapping(method = { RequestMethod.POST }, value = { "/statusNotify/{id}" })
    public AjaxResult statusNotify(@PathVariable("id") final Long id) {
        final NoticePublish noticePublish = new NoticePublish();
        noticePublish.setId(id);
        return this.arbitrateService.statusNotify(noticePublish);
    }
    
    @TjfaeLog
    @ApiOperation(value = "\u516c\u793a\u516c\u544a\u94fe\u63a5\u901a\u77e5", produces = "application/json")
    @RequestMapping(method = { RequestMethod.POST }, value = { "/urlNotify/{id}" })
    public AjaxResult urlNotify(@PathVariable("id") final Long id) {
        final NoticePublish noticePublish = new NoticePublish();
        noticePublish.setId(id);
        return this.arbitrateService.urlNotify(noticePublish);
    }
    
    static {
        log = LoggerFactory.getLogger((Class)NotifyController.class);
    }
}
