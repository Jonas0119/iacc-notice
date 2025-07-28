// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.utils;

import org.slf4j.LoggerFactory;
import com.tjfae.common.core.exception.BusinessException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import org.springframework.util.StringUtils;
import java.io.InputStream;
import java.io.File;
import org.slf4j.Logger;

public class FileUtils
{
    private static Logger logger;
    static int flag;
    
    public static void main(final String[] args) {
        final File file = new File("D:\\test");
        deleteFileWithHeader(file, "fabcar");
        if (FileUtils.flag == 1) {
            System.out.println("\u6587\u4ef6\u5220\u9664\u6210\u529f\uff01");
        }
    }
    
    public static void deleteFile(final File file) {
        if (file == null || !file.exists()) {
            FileUtils.flag = 0;
            System.out.println("\u6587\u4ef6\u5220\u9664\u5931\u8d25,\u8bf7\u68c0\u67e5\u6587\u4ef6\u8def\u5f84\u662f\u5426\u6b63\u786e");
            return;
        }
        if (file.isDirectory()) {
            final File[] listFiles;
            final File[] files = listFiles = file.listFiles();
            for (final File f : listFiles) {
                final String name = file.getName();
                System.out.println(name);
                if (f.isDirectory()) {
                    deleteFile(f);
                }
                else {
                    f.delete();
                }
            }
        }
        file.delete();
    }
    
    public static void deleteFileWithHeader(final File file, final String header) {
        if (file.exists() && file.isDirectory()) {
            final File[] listFiles;
            final File[] files = listFiles = file.listFiles();
            for (final File oneFile : listFiles) {
                if (oneFile.getName().startsWith(header)) {
                    deleteFile(oneFile);
                }
            }
        }
    }
    
    public static File saveFile(final String path, final String fileName, final String extentName, final InputStream inputStream) {
        if (StringUtils.isEmpty((Object)path) || inputStream == null) {
            return null;
        }
        final File targetFolder = new File(path);
        if (!targetFolder.exists() && !targetFolder.isDirectory()) {
            targetFolder.mkdirs();
        }
        deleteFileWithHeader(targetFolder, fileName);
        final String targetFileName = fileName + extentName;
        final File targetFile = new File(targetFolder.getPath(), targetFileName);
        FileOutputStream downloadOutput = null;
        final int buffersize = 2048;
        try {
            downloadOutput = new FileOutputStream(targetFile);
            final byte[] buffer = new byte[buffersize];
            int n = 0;
            while (-1 != (n = inputStream.read(buffer))) {
                downloadOutput.write(buffer, 0, n);
            }
        }
        catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            try {
                downloadOutput.close();
            }
            catch (final IOException e2) {
                e2.printStackTrace();
            }
        }
        return targetFile;
    }
    
    public static String getFileHash(final File file) {
        String hash = "";
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            hash = DigestUtils.md5Hex(IOUtils.toByteArray(inputStream));
        }
        catch (final Exception e) {
            FileUtils.logger.error("HASH\u83b7\u53d6\u5f02\u5e38\uff1a" + e);
        }
        finally {
            IOUtils.closeQuietly(inputStream);
        }
        return hash;
    }
    
    public static File transferToFile(final MultipartFile multipartFile) {
        FileUtils.logger.info("transferToFile\u65b9\u6cd5   \u6587\u4ef6\u8f6c\u6362\u5f00\u59cb...");
        final long start = System.currentTimeMillis();
        final String fileName = multipartFile.getOriginalFilename();
        final File file = new File(fileName);
        try (final BufferedInputStream bis = new BufferedInputStream(multipartFile.getInputStream());
             final BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
            final byte[] buf = new byte[1024];
            int len;
            while ((len = bis.read(buf)) != -1) {
                bos.write(buf);
            }
        }
        catch (final Exception e) {
            FileUtils.logger.error("transferToFile\u65b9\u6cd5  \u6587\u4ef6\u8f6c\u6362\u5931\u8d25...", (Throwable)e);
            throw new BusinessException("\u6587\u4ef6\u4e0a\u4f20\u5f02\u5e38...");
        }
        final long end = System.currentTimeMillis();
        FileUtils.logger.info("\u8017\u65f6:{}ms", (Object)(end - start));
        return file;
    }
    
    static {
        FileUtils.logger = LoggerFactory.getLogger((Class)FileUtils.class);
        FileUtils.flag = 1;
    }
}
