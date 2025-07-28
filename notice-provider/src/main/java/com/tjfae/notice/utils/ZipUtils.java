// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.utils;

import java.util.Iterator;
import java.io.BufferedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.zip.Checksum;
import java.util.zip.CheckedOutputStream;
import java.util.zip.CRC32;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.Map;

public class ZipUtils
{
    public static void zipFile(final Map<String, InputStream> fileInputStream, final OutputStream out) {
        try {
            final OutputStream is = out;
            final CheckedOutputStream cos = new CheckedOutputStream(is, new CRC32());
            final ZipOutputStream zos = new ZipOutputStream(cos);
            zipFilePost(zos, (Map)fileInputStream);
            zos.close();
            cos.close();
            is.close();
            System.out.println("\u538b\u7f29\u5b8c\u6210");
        }
        catch (final Exception ex) {}
    }
    
    private static void zipFilePost(final ZipOutputStream zos, final Map<String, InputStream> fileInputStream) {
        try {
            for (final Map.Entry<String, InputStream> i : fileInputStream.entrySet()) {
                final String zosNameTemp = i.getKey();
                zos.putNextEntry(new ZipEntry(zosNameTemp));
                final BufferedInputStream bis = new BufferedInputStream(i.getValue());
                final byte[] b = new byte[1024];
                int len = -1;
                while ((len = bis.read(b)) != -1) {
                    zos.write(b, 0, len);
                }
                bis.close();
                i.getValue().close();
            }
        }
        catch (final Exception ex) {}
    }
}
