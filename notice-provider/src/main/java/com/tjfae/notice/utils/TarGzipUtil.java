// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.utils;

import java.nio.file.Path;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import com.tjfae.common.core.exception.BusinessException;
import org.springframework.util.StringUtils;
import java.io.IOException;
import org.apache.commons.compress.archivers.ArchiveEntry;
import java.util.Iterator;
import java.util.Collection;
import java.io.ByteArrayInputStream;
import org.apache.commons.io.IOUtils;
import java.io.FileInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.io.FilenameUtils;
import org.hyperledger.fabric.sdk.helper.Utils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.File;

public class TarGzipUtil
{
    private TarGzipUtil() {
    }
    
    public static InputStream generateTarGzInputStream(final File src, final String pathPrefix) throws IOException {
        final File sourceDirectory = src;
        final ByteArrayOutputStream bos = new ByteArrayOutputStream(500000);
        final String sourcePath = sourceDirectory.getAbsolutePath();
        final TarArchiveOutputStream archiveOutputStream = new TarArchiveOutputStream((OutputStream)new GzipCompressorOutputStream((OutputStream)new BufferedOutputStream(bos)));
        archiveOutputStream.setLongFileMode(2);
        try {
            final Collection<File> childrenFiles = FileUtils.listFiles(sourceDirectory, (String[])null, true);
            for (final File childFile : childrenFiles) {
                final String childPath = childFile.getAbsolutePath();
                String relativePath = childPath.substring(sourcePath.length() + 1, childPath.length());
                if (pathPrefix != null) {
                    relativePath = Utils.combinePaths(pathPrefix, new String[] { relativePath });
                }
                relativePath = FilenameUtils.separatorsToUnix(relativePath);
                final ArchiveEntry archiveEntry = (ArchiveEntry)new TarArchiveEntry(childFile, relativePath);
                final FileInputStream fileInputStream = new FileInputStream(childFile);
                archiveOutputStream.putArchiveEntry(archiveEntry);
                try {
                    IOUtils.copy((InputStream)fileInputStream, (OutputStream)archiveOutputStream);
                }
                finally {
                    IOUtils.closeQuietly((InputStream)fileInputStream);
                    archiveOutputStream.closeArchiveEntry();
                }
            }
        }
        finally {
            IOUtils.closeQuietly((OutputStream)archiveOutputStream);
        }
        return new ByteArrayInputStream(bos.toByteArray());
    }
    
    public static File findFileSk(final File directory) {
        final File[] matches = directory.listFiles((dir, name) -> name.endsWith("_sk"));
        if (null == matches) {
            throw new RuntimeException(String.format("Matches returned null does %s directory exist?", directory.getAbsoluteFile().getName()));
        }
        if (matches.length != 1) {
            throw new RuntimeException(String.format("Expected in %s only 1 sk file but found %d", directory.getAbsoluteFile().getName(), matches.length));
        }
        return matches[0];
    }
    
    public static String upzipTarGzInputStream(final String pathParent, final String relativePath, final String fileName, final InputStream inputStream) throws Exception {
        if (StringUtils.isEmpty((Object)pathParent) || inputStream == null) {
            throw new BusinessException("no path or input stream exists!");
        }
        Path pathFolder = Paths.get(pathParent, new String[0]);
        if (!StringUtils.isEmpty((Object)relativePath)) {
            pathFolder = Paths.get(pathParent, relativePath);
        }
        final File extractFolder = new File(pathFolder.toString());
        if (!extractFolder.exists() && !extractFolder.isDirectory()) {
            extractFolder.mkdirs();
        }
        com.tjfae.notice.utils.FileUtils.deleteFileWithHeader(extractFolder, fileName);
        final String downloadFileName = fileName + ".tar.gz";
        final File downloadFile = new File(extractFolder.getPath(), downloadFileName);
        final FileOutputStream downloadOutput = new FileOutputStream(downloadFile);
        TarArchiveInputStream fin = null;
        String tarRootPath = "";
        final int buffersize = 2048;
        try {
            final byte[] buffer = new byte[buffersize];
            int n = 0;
            while (-1 != (n = inputStream.read(buffer))) {
                downloadOutput.write(buffer, 0, n);
            }
            fin = new TarArchiveInputStream((InputStream)new GzipCompressorInputStream((InputStream)new FileInputStream(downloadFile)));
            TarArchiveEntry entry;
            while ((entry = fin.getNextTarEntry()) != null) {
                if (entry.isDirectory()) {
                    continue;
                }
                if (StringUtils.isEmpty((Object)tarRootPath)) {
                    final String[] arrayStr = entry.getName().split("/");
                    if (arrayStr.length > 0) {
                        tarRootPath = arrayStr[0];
                    }
                }
                final File curfile = new File(extractFolder, entry.getName());
                final File parent = curfile.getParentFile();
                if (!parent.exists()) {
                    parent.mkdirs();
                }
                IOUtils.copy((InputStream)fin, (OutputStream)new FileOutputStream(curfile));
            }
        }
        catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            try {
                downloadOutput.close();
                fin.close();
            }
            catch (final IOException e2) {
                e2.printStackTrace();
            }
        }
        return tarRootPath;
    }
}
