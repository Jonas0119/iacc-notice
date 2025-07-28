// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.service.impl;

import org.slf4j.LoggerFactory;
import java.io.IOException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import java.io.FileInputStream;
import org.springframework.util.ResourceUtils;
import org.springframework.core.io.DefaultResourceLoader;
import java.io.OutputStream;
import com.aspose.words.Document;
import java.io.FileOutputStream;
import java.io.File;
import java.io.InputStream;
import com.aspose.words.License;
import org.slf4j.Logger;

public class AsposeUtil
{
    private static Logger log;
    
    private static void getLicense() throws Exception {
        try (final InputStream is = getInputStream("License.xml")) {
            final License license = new License();
            license.setLicense(is);
        }
        catch (final Throwable $ex) {
            throw $ex;
        }
    }
    
    public static void wordToPdf(final InputStream word, final String pdfPath) throws Exception {
        try {
            getLicense();
            final File file = new File(pdfPath);
            try (final FileOutputStream os = new FileOutputStream(file)) {
                final Document doc = new Document(word);
                doc.save((OutputStream)os, 40);
            }
        }
        catch (final Throwable $ex) {
            throw $ex;
        }
    }
    
    public static void wordToPdf(final InputStream word, final OutputStream pdf) throws Exception {
        try {
            getLicense();
            final Document doc = new Document(word);
            doc.save(pdf, 40);
        }
        catch (final Throwable $ex) {
            throw $ex;
        }
    }
    
    public static void wordDownload(final InputStream is, final OutputStream ot) throws Exception {
        try {
            getLicense();
            AsposeUtil.log.info("\u52a0\u8f7dLicense\u7ed3\u675f");
            final Document doc = new Document(is);
            AsposeUtil.log.info("Document\u6784\u5efa\u7ed3\u675f");
            doc.save(ot, 20);
            AsposeUtil.log.info("docx\u6587\u4ef6\u751f\u6210\u7ed3\u675f");
        }
        catch (final Throwable $ex) {
            throw $ex;
        }
    }
    
    public static Document getDoc(final InputStream word) throws Exception {
        try {
            getLicense();
            final Document doc = new Document(word);
            return doc;
        }
        catch (final Throwable $ex) {
            throw $ex;
        }
    }
    
    public static Document getDoc(final String path) throws Exception {
        try {
            getLicense();
            final Document doc = new Document(path);
            return doc;
        }
        catch (final Throwable $ex) {
            throw $ex;
        }
    }
    
    public static void test(final InputStream word, final OutputStream pdf) throws Exception {
        try {
            getLicense();
            final Document document = new Document();
        }
        catch (final Throwable $ex) {
            throw $ex;
        }
    }
    
    public static InputStream getInputStream(String path) throws IOException {
        path = "classpath:" + path;
        final ResourceLoader resourceLoader = (ResourceLoader)new DefaultResourceLoader();
        final Resource resource = resourceLoader.getResource(path);
        InputStream inputStream = resource.getInputStream();
        if (inputStream == null) {
            final File file = ResourceUtils.getFile(path);
            inputStream = new FileInputStream(file);
        }
        return inputStream;
    }
    
    static {
        AsposeUtil.log = LoggerFactory.getLogger((Class)AsposeUtil.class);
    }
}
