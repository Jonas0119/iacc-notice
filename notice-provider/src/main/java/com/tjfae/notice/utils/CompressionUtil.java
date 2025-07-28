package com.tjfae.notice.utils;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.IOUtils;

public class CompressionUtil {
    public enum CompressionType {
        GZIP, ZIP
    }

    private static final int NUM_THREADS = 5;
    private static final CompressionUtil INSTANCE = new CompressionUtil();
    private final ExecutorService pool;

    private CompressionUtil() {
        this.pool = Executors.newFixedThreadPool(NUM_THREADS);
    }

    public static CompressionUtil getInstance() {
        return INSTANCE;
    }

    public static OutputStream getCompressionWrapper(OutputStream sourceStream, CompressionType type) throws IOException {
        switch (type) {
            case GZIP:
                return new GZIPOutputStream(sourceStream);
            case ZIP:
                return new ZipOutputStream(sourceStream);
            default:
                throw new IllegalArgumentException("Possible values :" + Arrays.toString(CompressionType.values()));
        }
    }

    public static InputStream getCompressedStream(final InputStream sourceStream, final CompressionType type) throws IOException {
        if (sourceStream == null) {
            throw new IllegalArgumentException("Source Stream cannot be NULL");
        }
        final PipedInputStream resultStream = new PipedInputStream();
        final PipedOutputStream intermediateStream = new PipedOutputStream(resultStream);
        final OutputStream zipperOutStream = getCompressionWrapper(intermediateStream, type);
        Runnable copyTask = () -> {
            try {
                IOUtils.copy(sourceStream, zipperOutStream);
                zipperOutStream.close();
                intermediateStream.close();
                sourceStream.close();
            } catch (IOException e) {
                // handle exception
            }
        };
        getInstance().pool.submit(copyTask);
        return resultStream;
    }

    public static void main(String[] args) throws IOException {
        final String input = "abcdefghij";
        final InputStream sourceStream = new ByteArrayInputStream(input.getBytes());
        final InputStream compressedStream = getCompressedStream(sourceStream, CompressionType.GZIP);
        final GZIPInputStream decompressedStream = new GZIPInputStream(compressedStream);
        final List<String> lines = IOUtils.readLines(decompressedStream);
        final String output = lines.get(0);
        System.out.println("test passed ?" + input.equals(output));
    }
}
