// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.response;

public enum ResultCode
{
    SUCCESS("200", "\u6210\u529f"), 
    BAD_REQUEST("400", "\u53c2\u6570\u6216\u8bed\u6cd5\u6709\u8bef"), 
    UNAUTHORIZED("401", "\u8ba4\u8bc1\u5931\u8d25"), 
    SERVER_ERROR("500", "\u670d\u52a1\u5668\u5185\u90e8\u9519\u8bef"), 
    CryptoException("501", "\u8bf7\u6c42\u5931\u8d25:\u6587\u4ef6\u8bc1\u4e66\u5f02\u5e38"), 
    InvalidArgumentException("502", "\u8bf7\u6c42\u5931\u8d25:\u65e0\u6548\u7684\u53c2\u6570"), 
    NoSuchAlgorithmException("503", "\u8bf7\u6c42\u5931\u8d25:\u7b97\u6cd5\u5f02\u5e38"), 
    TransactionException("504", "\u8bf7\u6c42\u5931\u8d25:\u8bf7\u6c42\u4e8b\u52a1\u5f02\u5e38"), 
    ProposalException("506", "\u8bf7\u6c42\u5931\u8d25:\u63d0\u6848\u5f02\u5e38"), 
    InterruptedException("507", "\u8bf7\u6c42\u5931\u8d25:\u88ab\u6253\u65ad"), 
    ExecutionException("508", "\u8bf7\u6c42\u5931\u8d25:\u6267\u884c\u5f02\u5e38"), 
    OrdererSizeException("509", "\u6ca1\u6709\u8db3\u591f\u7684\u80cc\u4e66\u8282\u70b9"), 
    IOException("510", "IO\u5f02\u5e38"), 
    ClassNotFoundException("511", "\u6587\u4ef6\u4e0d\u5b58\u5728"), 
    StoreIOKeyException("512", "\u65e0\u6cd5\u4ece\u6587\u4ef6\u52a0\u8f7d\u952e\u503c"), 
    PEMException("513", "\u8bfb\u53d6\u79c1\u94a5\u53d1\u751f\u5f02\u5e38"), 
    CreateCaClientException("514", "\u521b\u5efaCA\u5ba2\u6237\u7aef\u53d1\u751f\u5f02\u5e38"), 
    EnrollException("515", "\u767b\u8bb0\u5931\u8d25"), 
    RegisterException("516", "\u6ce8\u518c\u5931\u8d25"), 
    CRLException("517", "\u8bc1\u4e66\u5df2\u88ab\u540a\u9500"), 
    TimeOutException("518", "\u8bc1\u4e66\u5df2\u8fc7\u671f"), 
    VerifyFailure("519", "\u9a8c\u7b7e\u5931\u8d25"), 
    SignFailure("520", "\u52a0\u7b7e\u5931\u8d25"), 
    ExamineText("521", "\u60a8\u4e0a\u94fe\u6587\u672c\u4e0d\u5408\u89c4"), 
    ExamineCheckFailed("522", "\u60a8\u4e0a\u94fe\u6587\u672c\u4e0d\u5408\u89c4");
    
    private String code;
    private String msg;
    
    private ResultCode(final String code, final String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    public String getCode() {
        return this.code;
    }
    
    public String getMsg() {
        return this.msg;
    }
}
