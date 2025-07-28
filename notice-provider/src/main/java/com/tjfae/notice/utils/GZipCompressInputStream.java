// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.utils;

import java.util.zip.Deflater;
import java.util.zip.DeflaterInputStream;
import java.util.zip.CRC32;
import java.io.FilterInputStream;
import java.io.ByteArrayInputStream;
import java.util.Enumeration;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;

public class GZipCompressInputStream extends SequenceInputStream {
    public GZipCompressInputStream(final InputStream in) throws IOException {
        this(in, 512);
    }

    public GZipCompressInputStream(final InputStream in, final int bufferSize) throws IOException {
        super(new StatefullGzipStreamEnumerator(in, bufferSize));
    }

    enum StreamState {
        HEADER, CONTENT, TRAILER
    }

    protected static class StatefullGzipStreamEnumerator implements Enumeration<InputStream> {
        protected final InputStream in;
        protected final int bufferSize;
        protected StreamState state;
        static final int GZIP_MAGIC = 35615;
        static final byte[] GZIP_HEADER;
        protected InternalGzipCompressingInputStream contentStream;

        public StatefullGzipStreamEnumerator(final InputStream in, final int bufferSize) {
            this.in = in;
            this.bufferSize = bufferSize;
            this.state = StreamState.HEADER;
        }

        @Override
        public boolean hasMoreElements() {
            return this.state != null;
        }

        @Override
        public InputStream nextElement() {
            switch (this.state) {
                case HEADER:
                    this.state = StreamState.CONTENT;
                    return this.createHeaderStream();
                case CONTENT:
                    this.state = StreamState.TRAILER;
                    return this.createContentStream();
                case TRAILER:
                    this.state = null;
                    return this.createTrailerStream();
                default:
                    return null;
            }
        }

        protected InputStream createHeaderStream() {
            return new ByteArrayInputStream(GZIP_HEADER);
        }

        protected InputStream createContentStream() {
            return (this.contentStream = new InternalGzipCompressingInputStream(new CRC32InputStream(this.in), this.bufferSize));
        }

        protected InputStream createTrailerStream() {
            return new ByteArrayInputStream(this.contentStream.createTrailer());
        }

        static {
            GZIP_HEADER = new byte[] { 31, -117, 8, 0, 0, 0, 0, 0, 0, 0 };
        }
    }

    protected static class CRC32InputStream extends FilterInputStream {
        protected CRC32 crc;
        protected long byteCount;

        public CRC32InputStream(final InputStream in) {
            super(in);
            this.crc = new CRC32();
        }

        @Override
        public int read() throws IOException {
            final int val = super.read();
            if (val >= 0) {
                this.crc.update(val);
                ++this.byteCount;
            }
            return val;
        }

        @Override
        public int read(final byte[] b, final int off, int len) throws IOException {
            len = super.read(b, off, len);
            if (len >= 0) {
                this.crc.update(b, off, len);
                this.byteCount += len;
            }
            return len;
        }

        public long getCrcValue() {
            return this.crc.getValue();
        }

        public long getByteCount() {
            return this.byteCount;
        }
    }

    protected static class InternalGzipCompressingInputStream extends DeflaterInputStream {
        protected final CRC32InputStream crcIn;
        protected static final int TRAILER_SIZE = 8;

        public InternalGzipCompressingInputStream(final CRC32InputStream in, final int bufferSize) {
            super(in, new Deflater(-1, true), bufferSize);
            this.crcIn = in;
        }

        @Override
        public void close() throws IOException {
            if (this.in != null) {
                try {
                    this.def.end();
                    this.in.close();
                } finally {
                    // this.in = null; // can't set final field to null
                }
            }
        }

        public byte[] createTrailer() {
            final byte[] trailer = new byte[8];
            this.writeTrailer(trailer, 0);
            return trailer;
        }

        private void writeTrailer(final byte[] buf, final int offset) {
            this.writeInt((int)this.crcIn.getCrcValue(), buf, offset);
            this.writeInt((int)this.crcIn.getByteCount(), buf, offset + 4);
        }

        private void writeInt(final int i, final byte[] buf, final int offset) {
            this.writeShort(i & 0xFFFF, buf, offset);
            this.writeShort(i >> 16 & 0xFFFF, buf, offset + 2);
        }

        private void writeShort(final int s, final byte[] buf, final int offset) {
            buf[offset] = (byte)(s & 0xFF);
            buf[offset + 1] = (byte)((s >> 8) & 0xFF);
        }
    }
}
