// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.common.util;

import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
import com.tjfae.common.core.exception.BusinessException;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.OutputStream;
import java.net.URLConnection;
import java.io.FileOutputStream;
import java.net.URL;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import java.io.InputStream;
import com.aliyun.oss.model.ObjectMetadata;
import java.io.FileInputStream;
import java.io.File;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class OSSUnit
{
    private static Logger log;
    private static Logger LOG;
    private static String ENDPOINT;
    private static String ACCESS_KEY_ID;
    private static String ACCESS_KEY_SECRET;
    private static String ENDPOINT_SHOW;
    private static String BUCKET_NAME;
    
    @Value("${ENDPOINT}")
    public void setENDPOINT(final String ENDPOINT) {
        OSSUnit.ENDPOINT = ENDPOINT;
    }
    
    @Value("${ENDPOINT_SHOW}")
    public void setENDPOINT_SHOW(final String ENDPOINT_SHOW) {
        OSSUnit.ENDPOINT_SHOW = ENDPOINT_SHOW;
    }
    
    @Value("${AccessKeyId}")
    public void setAccessKeyId(final String accessKeyId) {
        OSSUnit.ACCESS_KEY_ID = accessKeyId;
    }
    
    @Value("${AccessKeySecret}")
    public void setAccessKeySecret(final String accessKeySecret) {
        OSSUnit.ACCESS_KEY_SECRET = accessKeySecret;
    }
    
    @Value("${BUCKET_NAME}")
    public void setBUCKET_NAME(final String BUCKET_NAME) {
        OSSUnit.BUCKET_NAME = BUCKET_NAME;
    }
    
    public static OSSClient getOSSClient() {
        return new OSSClient(OSSUnit.ENDPOINT, OSSUnit.ACCESS_KEY_ID, OSSUnit.ACCESS_KEY_SECRET);
    }
    
    public static final boolean createBucket(final OSSClient client, final String bucketName) {
        final Bucket bucket = client.createBucket(bucketName);
        return bucketName.equals(bucket.getName());
    }
    
    public static final void deleteBucket(final OSSClient client, final String bucketName) {
        client.deleteBucket(bucketName);
        OSSUnit.LOG.info("\u5220\u9664" + bucketName + "Bucket\u6210\u529f");
    }
    
    public static final String uploadObject2OSS(final OSSClient client, final File file, final String bucketName, final String diskName) throws Exception {
        String resultStr = null;
        try {
            final InputStream is = new FileInputStream(file);
            final String fileName = file.getName();
            final Long fileSize = file.length();
            final ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength((long)is.available());
            metadata.setCacheControl("no-cache");
            metadata.setHeader("Pragma", (Object)"no-cache");
            metadata.setContentEncoding("utf-8");
            metadata.setContentType(getContentType(fileName));
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            final PutObjectResult putResult = client.putObject(bucketName, diskName + fileName, is, metadata);
            resultStr = putResult.getETag();
        }
        catch (final Exception e) {
            OSSUnit.LOG.error("\u4e0a\u4f20\u963f\u91cc\u4e91OSS\u670d\u52a1\u5668\u5f02\u5e38." + e.getMessage(), (Throwable)e);
            throw e;
        }
        return resultStr;
    }
    
    public static final InputStream getOSS2InputStream(final OSSClient client, final String bucketName, final String diskName, final String key) {
        final OSSObject ossObj = client.getObject(bucketName, diskName + key);
        return ossObj.getObjectContent();
    }
    
    public static void deleteFile(final OSSClient client, final String bucketName, final String diskName, final String key) {
        client.deleteObject(bucketName, diskName + key);
        OSSUnit.LOG.info("\u5220\u9664" + bucketName + "\u4e0b\u7684\u6587\u4ef6" + diskName + key + "\u6210\u529f");
    }
    
    public static final String getContentType(final String fileName) {
        final String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        if (".bmp".equalsIgnoreCase(fileExtension)) {
            return "image/bmp";
        }
        if (".gif".equalsIgnoreCase(fileExtension)) {
            return "image/gif";
        }
        if (".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension) || ".png".equalsIgnoreCase(fileExtension)) {
            return "image/jpeg";
        }
        if (".html".equalsIgnoreCase(fileExtension)) {
            return "text/html";
        }
        if (".txt".equalsIgnoreCase(fileExtension)) {
            return "text/plain";
        }
        if (".vsd".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.visio";
        }
        if (".ppt".equalsIgnoreCase(fileExtension) || ".pptx".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if (".doc".equalsIgnoreCase(fileExtension) || ".docx".equalsIgnoreCase(fileExtension)) {
            return "application/msword";
        }
        if (".xml".equalsIgnoreCase(fileExtension)) {
            return "text/xml";
        }
        if (".pdf".equalsIgnoreCase(fileExtension)) {
            return "application/pdf";
        }
        return "applicatoin/octet-stream";
    }
    
    public static String getUrl(final OSSClient ossClient, final String key, final String bucketName) {
        return bucketName + "." + OSSUnit.ENDPOINT + "/" + key;
    }
    
    public static String getShowUrl(final OSSClient ossClient, final String key, final String bucketName) {
        return bucketName + "." + OSSUnit.ENDPOINT_SHOW + "/" + key;
    }
    
    public static void download(String urlString, final String savePath, final String filename) throws Exception {
        if (urlString.indexOf("http") == -1) {
            urlString = "https:" + urlString;
        }
        final URL url = new URL(urlString);
        final URLConnection con = url.openConnection();
        con.setConnectTimeout(5000);
        final InputStream is = con.getInputStream();
        final byte[] bs = new byte[1024];
        final File sf = new File(savePath);
        if (!sf.exists()) {
            sf.mkdirs();
        }
        final OutputStream os = new FileOutputStream(sf.getPath() + System.getProperty("file.separator") + filename);
        int len;
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        os.close();
        is.close();
    }
    
    public static String upload(final MultipartFile file) {
        final OSSClient ossClient = getOSSClient();
        try {
            String fronturl = "";
            final String fileName = file.getOriginalFilename();
            OSSUnit.log.info("OSS\u6587\u4ef6\u540d: " + fileName);
            final String prefix = fileName.substring(fileName.lastIndexOf("."));
            final String fileOtherName = "listed";
            final File newFile = File.createTempFile(fileOtherName, prefix);
            file.transferTo(newFile);
            final String excelFileName = newFile.getName();
            uploadObject2OSS(ossClient, newFile, OSSUnit.BUCKET_NAME, "");
            fronturl = getUrl(ossClient, excelFileName, OSSUnit.BUCKET_NAME);
            newFile.delete();
            OSSUnit.log.info("OSS\u6587\u4ef6\u8fd4\u56de\u5730\u5740: " + fronturl);
            return "https://" + fronturl;
        }
        catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            ossClient.shutdown();
        }
    }
    
    public static final String uploadInfo(final String url, final String fileName) {
        OSSUnit.log.info("url={},fileName={}", (Object)url, (Object)fileName);
        final OSSClient ossClient = getOSSClient();
        try {
            String fronturl = "";
            final String prefix = fileName.substring(fileName.lastIndexOf("."));
            final String fileOtherName = "listed";
            final File newFile = File.createTempFile(fileOtherName, prefix);
            FileUtils.copyURLToFile(new URL(url), newFile);
            final String excelFileName = newFile.getName();
            uploadObject2OSS(ossClient, newFile, OSSUnit.BUCKET_NAME, "");
            fronturl = getUrl(ossClient, excelFileName, OSSUnit.BUCKET_NAME);
            newFile.delete();
            OSSUnit.log.info("OSS\u6587\u4ef6\u8fd4\u56de\u5730\u5740: " + fronturl);
            return "https://" + fronturl;
        }
        catch (final Exception e) {
            OSSUnit.log.error("OSSUnit.uploadInfo\u65b9\u6cd5\u5f02\u5e38", (Throwable)e);
            throw new BusinessException("\u516c\u544a\u63a8\u9001\u5931\u8d25");
        }
        finally {
            ossClient.shutdown();
        }
    }
    
    public static Map<String, String> discoveryUpload(final MultipartFile file, final String type) {
        final OSSClient ossClient = getOSSClient();
        try {
            Map<String, String> map = new HashMap<String, String>();
            String fronturl = "";
            final String fileName = file.getOriginalFilename();
            OSSUnit.log.info("OSS\u6587\u4ef6\u540d: " + fileName);
            final String prefix = fileName.substring(fileName.lastIndexOf("."));
            final String fileOtherName = "listed";
            final File newFile = File.createTempFile(fileOtherName, prefix);
            file.transferTo(newFile);
            final String excelFileName = newFile.getName();
            uploadObject2OSS(ossClient, newFile, OSSUnit.BUCKET_NAME, "");
            fronturl = getUrl(ossClient, excelFileName, OSSUnit.BUCKET_NAME);
            if (type.equals("0")) {
                map = FileUtil.getImgWidth(newFile);
            }
            if (type.equals("1")) {
                map = FileUtil.getVideoWidth(newFile);
            }
            newFile.delete();
            OSSUnit.log.info("OSS\u6587\u4ef6\u8fd4\u56de\u5730\u5740: " + fronturl);
            map.put("path", "https://" + fronturl);
            return map;
        }
        catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            ossClient.shutdown();
        }
    }
    
    static {
        log = LoggerFactory.getLogger((Class)OSSUnit.class);
        LOG = LoggerFactory.getLogger((Class)OSSUnit.class);
    }
}
