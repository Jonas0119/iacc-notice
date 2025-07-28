// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.utils;

import java.util.concurrent.ConcurrentHashMap;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.HttpMethod;
import java.util.Date;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import com.tjfae.notice.utils.string.StringUtils;
import java.io.InputStream;
import org.springframework.web.multipart.MultipartFile;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSS;
import java.util.Map;

public class OssClientUtil
{
    private static volatile Map<String, OSS> OssClientMap;
    
    public static OSS getOssClient(final String endPoint, final String accessKeyId, final String accessKeySecret, final String bucketName) {
        OSS ossClient = OssClientUtil.OssClientMap.get(endPoint);
        if (null == ossClient) {
            synchronized (OssClientUtil.class) {
                ossClient = OssClientUtil.OssClientMap.get(endPoint);
                if (null == ossClient) {
                    ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
                    OssClientUtil.OssClientMap.put(endPoint, ossClient);
                }
                if (!ossClient.doesBucketExist(bucketName)) {
                    ossClient.createBucket(bucketName);
                    final CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                    createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                    ossClient.createBucket(createBucketRequest);
                }
            }
        }
        else if (!ossClient.doesBucketExist(bucketName)) {
            ossClient.createBucket(bucketName);
            final CreateBucketRequest createBucketRequest2 = new CreateBucketRequest(bucketName);
            createBucketRequest2.setCannedACL(CannedAccessControlList.PublicRead);
            ossClient.createBucket(createBucketRequest2);
        }
        return ossClient;
    }
    
    public static boolean uploadFile(final OSS ossClient, final String bucketName, final MultipartFile file, final String fileName) {
        try {
            return uploadFile(ossClient, bucketName, file.getInputStream(), fileName);
        }
        catch (final Exception e) {
            return false;
        }
    }
    
    public static boolean uploadFile(final OSS ossClient, final String bucketName, final InputStream inputStream, final String fileName) {
        if (StringUtils.isBlank(fileName)) {
            System.out.println("No file name.");
            return false;
        }
        try {
            final PutObjectResult putObjectResult = ossClient.putObject(bucketName, fileName, inputStream);
            if (putObjectResult == null) {
                return false;
            }
            final boolean exists = ossClient.doesObjectExist(bucketName, fileName);
            if (!exists) {
                return false;
            }
            ossClient.setObjectAcl(bucketName, fileName, CannedAccessControlList.PublicRead);
        }
        catch (final Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static OSSObject downloadFile(final OSS ossClient, final String bucketName, final String fileName) {
        URL reqUrl = null;
        final Date expiration = new Date(new Date().getTime() + 3600000L);
        final GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, fileName, HttpMethod.GET);
        request.setExpiration(expiration);
        reqUrl = ossClient.generatePresignedUrl(request);
        final Map<String, String> customHeaders = new HashMap<String, String>();
        final OSSObject object = ossClient.getObject(reqUrl, (Map)customHeaders);
        return object;
    }
    
    public static String getFileUrl(final OSS ossClient, final String bucketName, final String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return "";
        }
        final boolean exists = ossClient.doesObjectExist(bucketName, fileName);
        if (!exists) {
            return "";
        }
        final Date expiration = new Date(new Date().getTime() + 600000L);
        URL url = null;
        try {
            final GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, fileName);
            request.setExpiration(expiration);
            url = ossClient.generatePresignedUrl(request);
        }
        catch (final Exception e) {
            System.out.println(e.getMessage() + " " + e.toString());
        }
        String res = "";
        if (url != null) {
            res = url.toString();
        }
        return res;
    }
    
    public static void close(final OSS ossClient) {
        ossClient.shutdown();
    }
    
    public static InputStream downloadFile(final String fileUrl) throws Exception {
        final URL url = new URL(fileUrl);
        final HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestProperty("Content-Type", "application/octet-stream");
        con.setRequestProperty("Connection", "Keep-Alive");
        con.connect();
        final BufferedInputStream bin = new BufferedInputStream(con.getInputStream());
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final byte[] buffer = new byte[1024];
        int len;
        while ((len = bin.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        baos.flush();
        final byte[] dataBytes = baos.toByteArray();
        final InputStream is = new ByteArrayInputStream(dataBytes);
        return is;
    }
    
    static {
        OssClientUtil.OssClientMap = new ConcurrentHashMap();
    }
}
