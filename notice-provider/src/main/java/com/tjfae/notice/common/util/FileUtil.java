// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.common.util;

import org.bytedeco.javacv.FrameRecorder;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Dimension;
import org.bytedeco.javacv.Frame;
import java.awt.image.RenderedImage;
import java.awt.image.ImageObserver;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

public class FileUtil
{
    public static Map<String, String> getImgWidth(final File file) {
        InputStream is = null;
        BufferedImage src = null;
        final Map<String, String> res = new HashMap<String, String>();
        try {
            is = new FileInputStream(file);
            src = ImageIO.read(is);
            final Integer wide = src.getWidth();
            final Integer high = src.getHeight();
            is.close();
            res.put("wide", wide.toString());
            res.put("high", high.toString());
        }
        catch (final Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    
    public static Map<String, String> getVideoWidth(final File inputVideo) throws FrameGrabber.Exception {
        final Map<String, String> res = new HashMap<String, String>();
        final FFmpegFrameGrabber ff = new FFmpegFrameGrabber(inputVideo);
        ff.setOption("rtsp_transport", "tcp");
        ff.start();
        res.put("wide", ff.getImageWidth() + "");
        res.put("high", ff.getImageHeight() + "");
        ff.stop();
        ff.release();
        return res;
    }
    
    public static boolean fetchFrame(final String videofile, final String outPutFile, final String framefile) throws Exception {
        boolean ok = false;
        final File targetFile = new File(framefile);
        final FFmpegFrameGrabber ff = new FFmpegFrameGrabber(videofile);
        ff.start();
        final String rotate_old = ff.getVideoMetadata("rotate");
        System.out.println(ff.getVideoBitrate());
        if (ff.getVideoBitrate() > 2000000) {
            recordByFrame(videofile, outPutFile, rotate_old);
            ok = true;
        }
        final int lenght = ff.getLengthInFrames();
        int i = 0;
        Frame f = null;
        while (i < lenght) {
            f = ff.grabFrame();
            if (i > 48 && f.image != null) {
                break;
            }
            ++i;
        }
        final int owidth = f.imageWidth;
        final int oheight = f.imageHeight;
        final int width = 300;
        final int height = (int)(width / (double)owidth * oheight);
        final Java2DFrameConverter converter = new Java2DFrameConverter();
        final BufferedImage fecthedImage = converter.getBufferedImage(f);
        final BufferedImage bi = new BufferedImage(width, height, 5);
        bi.getGraphics().drawImage(fecthedImage.getScaledInstance(width, height, 4), 0, 0, null);
        ImageIO.write(bi, "jpg", targetFile);
        ff.stop();
        if (rotate_old != null && !rotate_old.isEmpty()) {
            final int rotate = Integer.parseInt(rotate_old);
            rotatePhonePhoto(framefile, rotate);
        }
        if (ff.getVideoBitrate() > 2000000) {
            recordByFrame(videofile, outPutFile, rotate_old);
        }
        return ok;
    }
    
    public static String rotatePhonePhoto(final String fullPath, final int angel) {
        try {
            final BufferedImage src = ImageIO.read(new File(fullPath));
            final int src_width = src.getWidth(null);
            final int src_height = src.getHeight(null);
            int swidth = src_width;
            int sheight = src_height;
            if (angel == 90 || angel == 270) {
                swidth = src_height;
                sheight = src_width;
            }
            final Rectangle rect_des = new Rectangle(new Dimension(swidth, sheight));
            final BufferedImage res = new BufferedImage(rect_des.width, rect_des.height, 1);
            final Graphics2D g2 = res.createGraphics();
            g2.translate((rect_des.width - src_width) / 2, (rect_des.height - src_height) / 2);
            g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);
            g2.drawImage(src, null, null);
            ImageIO.write(res, "jpg", new File(fullPath));
        }
        catch (final Exception e) {
            e.printStackTrace();
        }
        return fullPath;
    }
    
    private static void recordByFrame(final String inputVideo, final String outputVideo, final String rotate) throws FrameRecorder.Exception, FrameGrabber.Exception {
        final FFmpegFrameGrabber ff = new FFmpegFrameGrabber(inputVideo);
        ff.setOption("rtsp_transport", "tcp");
        ff.start();
        final FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputVideo, 2);
        recorder.setVideoBitrate(1048576);
        recorder.setVideoCodec(27);
        recorder.setImageHeight(ff.getImageHeight());
        recorder.setImageWidth(ff.getImageWidth());
        recorder.setVideoMetadata("rotate", rotate);
        recorder.start();
        final long t1 = System.currentTimeMillis();
        Frame frame = null;
        while ((frame = ff.grabFrame()) != null) {
            final long t2 = System.currentTimeMillis();
            if (t2 - t1 > 7200000L) {
                break;
            }
            recorder.record(frame);
        }
        ff.stop();
        ff.release();
        recorder.stop();
        recorder.release();
    }
}
