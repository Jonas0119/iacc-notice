// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.common.util;

public enum ResultCode {
    SUCCESS("0000", "成功"),
    EXIST("0001", "用户已存在"),
    STATE("0002", "用户已禁用"),
    BAD_REQUEST("0003", "参数或语法有误"),
    UNAUTHORIZED("0004", "认证失败"),
    LOGIN_ERROR("0005", "登录失败,用户名或密码无效"),
    FORBIDDEN("0006", "权限不足,禁止访问"),
    OPERATE_ERROR("0008", "导出数据为空,操作失败"),
    SERVER_ERROR("0010", "服务器内部错误"),
    TOKEN_TIME_OUT("0011", "签名已经过期"),
    TOKEN_CHECK_FAILED("0012", "签名验证失败"),
    PHONE_NOT_REGISTER("0016", "手机号未注册"),
    PHONE_OR_PASSWORD_ERROR("0017", "手机号或密码错误"),
    FAIL("0018", "操作失败"),
    GUESS_ISUP("0019", "竞猜已下架"),
    GUESS_ISGET("0020", "已经退款"),
    BANNED_ISEXIST("0021", "已存在禁言用户"),
    BANNED_NOTEXIST_USER("0022", "该用户不存在"),
    GROUP_NOTEXIST("0023", "该群组不存在"),
    GROUP_ISEXIST("0024", "该群组已存在列表中"),
    LISTED_ISEXIST("0025", "该用户用户与存在平台用户列表中"),
    AUTHNAME_ISEXIST("0026", "该认证名称已存在"),
    USER_NOTEXIST("0027", "该个人/企业用户不存在"),
    MANAGER_EXIST("0028", "该产品经理与产品类型已存在"),
    SOURCE_ERROR("0029", "分数必须在[-1000, 1000]范围内"),
    BASE_SOURCE_ERROR("0030", "基础分数必须在[0, 100]范围内"),
    TYPE_EXIST("0028", "产品类型已存在"),
    AFFICHE_CONFIG_REPEAT("0100", "请勿重复配置!"),
    AFFICHE_TYPE_NO("0101", "未找到该公告!"),
    AFFICHE_COMPANY_NO("0102", "主体不存在！"),
    AFFICHE_CONTENT_NO("0103", "公告内容不可为空！"),
    AFFICHE_TEMPLATE_VALUE_NO("0104", "公告模版值不可为空!"),
    AFFICHE_FILE_UPLOAD_FAIL("0105", "上传附件失败!"),
    AFFICHE_NO("0106", "未查询到该公告！"),
    AFFICHE_COMPANY_CONFIG_NO("0107", "公告主体未配置!"),
    AFFICHE_FILE_NO("0108", "未找到该附件!"),
    AFFICHE_CONFIG_COMPANY_NOT_CHANGE("0109", "公告主体不允许修改!"),
    AFFICHE_FILE_SIZE_LIMIT("0110", "公告附件最多上传5个！"),
    AFFICHE_EXCEL_SIZE_LIMIT("0111", "超出Excel最大限制"),
    SUBJECT_NOT_EXIST("0200", "请联系信用部补充主体信息"),
    INTEGRAL_RULE_EXIST_ERROR("0031", "操作失败，重复设置板块动作积分"),
    AWARD_RULE_COUPON_ERROR("0032", "代金券ID错误，无匹配活动"),
    AWARD_RULE_LIST_ERROR("0033", "积分配置比例数据不能为空"),
    CREDIT_UN_SIGN_ERROR("0034", "报告未签章"),
    CREDIT_UN_CONFIRM_ERROR("0035", "报告未确认"),
    CREDIT_TYPE_ALLREADY_ERROR("0036", "该行业类型已创建"),
    CREDIT_TYPE_NOTICE_ERROR("0037", "承诺函数调用结果为空"),
    INCOME_ORG_NOTICE_ERROR("0038", "此主体下面已存在此部门,不可重复添加"),
    INCOME_UPDATE_NOTICE_ERROR("0039", "参数传递不正确,缺少唯一Id"),
    INCOME_DATA_NOTICE_ERROR("0040", "数据不存在"),
    INCOME_USER_ERROR("0041", "此部门下存在人员,不能删除"),
    INCOME_USER_ADD_ERROR("0042", "此人员已存在,不能重复添加"),
    INCOME_INFO_SUBMIT_ERROR("0043", "提交单据失败"),
    INCOME_INFO_AUDIT_ERROR("0044", "单据已审核,不可重复审核"),
    INCOME_INFO_IS_CHARGE_ERROR("0045", "单据已记账,不可重复记账"),
    INCOME_INFO_IS_PROCEEDS_ERROR("0046", "单据已收款,不可重复收款"),
    INCOME_INFO_CANCEL_ERROR("0047", "单据已作废,不可重复作废"),
    INCOME_INFO_ARCHIVED_ERROR("0048", "单据已归档,不可重复归档"),
    INCOME_INFO_CANCEL_INFO("0049", "单据已作废,不可归档"),
    INCOME_INFO_ARCHIVED_INFO("0050", "单据已归档,不可作废"),
    INCOME_ASSESS_ERROR("0051", "分配金额大不等于考核收入"),
    INCOME_INFO_SUBMIT_INFO("0052", "提交单据失败,单据已提交"),
    INCOME_INFO_BATCH_INFO("0053", "只有复审通过和待财务归档才可执行此操作"),
    INCOME_AUDIT_ERROR("0054", "此角色无此权限"),
    INCOME_ASSESS_MONEY_ERROR("0055", "分配金额不能小于或者等于零"),
    INCOME_IS_EXISTS_USER_ERROR("0056", "此项目经理不在组织架构下的部门中,请为此项目经理添加部门"),
    INCOME_ORG_ERROR("0057", "部门不存在,不可修改");

    private final String code;
    private final String msg;

    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
