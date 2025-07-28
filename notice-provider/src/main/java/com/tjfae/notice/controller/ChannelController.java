// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.controller;

import org.slf4j.LoggerFactory;
import com.tjfae.notice.request.NoticeChannelCancelReq;
import org.springframework.web.bind.annotation.RequestMethod;
import io.swagger.annotations.ApiOperation;
import com.tjfae.common.log.annotation.TjfaeLog;
import com.tjfae.common.core.web.domain.AjaxResult;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.annotation.Validated;
import com.tjfae.notice.request.NoticeChannelPushReq;
import javax.annotation.Resource;
import com.tjfae.notice.service.impl.ArbitrateServiceImpl;
import org.slf4j.Logger;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;

@Api(tags = { "\u6e20\u9053\u63a8\u9001\u63a5\u53e3" })
@RestController
@RequestMapping({ "/api/channel" })
@RefreshScope
public class ChannelController extends BaseController
{
    private static final Logger log;
    @Resource
    private ArbitrateServiceImpl arbitrateService;
    
    @TjfaeLog
    @ApiOperation(value = "\u516c\u793a\u516c\u544a\u63a8\u9001", produces = "application/json")
    @RequestMapping(method = { RequestMethod.POST }, value = { "/noticePush" })
    public AjaxResult noticePush(@Validated @RequestBody final NoticeChannelPushReq req, final HttpServletRequest request) {
        final Long id = this.arbitrateService.noticePush(req, request);
        return AjaxResult.success((Object)id);
    }
    
    @TjfaeLog
    @ApiOperation(value = "\u516c\u793a\u516c\u544a\u4fee\u6539", produces = "application/json")
    @RequestMapping(method = { RequestMethod.POST }, value = { "/noticeUpdate" })
    public AjaxResult noticeUpdate(@Validated @RequestBody final NoticeChannelPushReq req, final HttpServletRequest request) {
        this.arbitrateService.noticeUpdate(req, request);
        return AjaxResult.success("\u6210\u529f");
    }
    
    @TjfaeLog
    @ApiOperation(value = "\u516c\u793a\u516c\u544a\u53d6\u6d88", produces = "application/json")
    @RequestMapping(method = { RequestMethod.POST }, value = { "/noticeCancel" })
    public AjaxResult noticeCancel(@Validated @RequestBody final NoticeChannelCancelReq req, final HttpServletRequest request) {
        this.arbitrateService.noticeCancel(req, request);
        return AjaxResult.success("\u6210\u529f");
    }
    
    static {
        log = LoggerFactory.getLogger((Class)ChannelController.class);
    }
}
