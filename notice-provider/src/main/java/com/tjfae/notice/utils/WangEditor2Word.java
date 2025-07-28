// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.utils;

import java.io.OutputStream;
import java.awt.image.RenderedImage;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.io.ByteArrayOutputStream;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import javax.imageio.ImageReadParam;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageReader;
import java.io.File;
import java.net.URL;
import java.util.Base64;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;
import com.deepoove.poi.policy.TextRenderPolicy;
import com.deepoove.poi.data.TextRenderData;
import org.apache.commons.lang3.StringUtils;
import java.io.IOException;
import com.deepoove.poi.policy.PictureRenderPolicy;
import com.deepoove.poi.data.PictureRenderData;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import com.deepoove.poi.policy.AbstractRenderPolicy;
import org.springframework.stereotype.Component;

@Component
public class WangEditor2Word
{
    public static AbstractRenderPolicy<Object> policy(final String filePath, final int maxWidth, final int compressWidth) {
        // TODO: 实现WangEditor2Word渲染策略，当前返回null以让编译通过
        return null;
    }
    
    private static void copyXWPFParagraph(final XWPFParagraph xwpfParagraph, final XWPFParagraph sourceParagraph, final String filePath, final int maxWidth, final int compressWidth) throws Exception {
        final List<XWPFRun> runs = sourceParagraph.getRuns();
        if (runs != null) {
            xwpfParagraph.setStyle(sourceParagraph.getStyle());
            xwpfParagraph.setAlignment(sourceParagraph.getAlignment());
            xwpfParagraph.setFirstLineIndent(sourceParagraph.getFirstLineIndent());
            for (final XWPFRun run : runs) {
                final String text = run.getText(run.getTextPosition());
                if (text == null) {
                    final List<XWPFPicture> piclist = run.getEmbeddedPictures();
                    if (piclist == null) {
                        continue;
                    }
                    for (int k = 0; k < piclist.size(); ++k) {
                        final XWPFPicture pic = piclist.get(k);
                        if (pic != null) {
                            if (pic.getPictureData() != null) {
                                final byte[] picByte = pic.getPictureData().getData();
                                String picExt = ".jpeg";
                                if (pic.getPictureData().getFileName().contains(".")) {
                                    picExt = pic.getPictureData().getFileName().substring(pic.getPictureData().getFileName().lastIndexOf("."));
                                }
                                ByteArrayInputStream in = null;
                                try {
                                    in = new ByteArrayInputStream(picByte);
                                    final BufferedImage img = ImageIO.read(in);
                                    int width = img.getWidth();
                                    int height = img.getHeight();
                                    PictureRenderData pictureRenderData = new PictureRenderData(width, height, picExt, img);
                                    if (maxWidth > 0 && width > maxWidth) {
                                        height /= (int)(width / new Double(maxWidth));
                                        width = maxWidth;
                                        pictureRenderData = new PictureRenderData(width, height, picExt, img);
                                        if (compressWidth > 0 && img.getWidth() > compressWidth) {
                                            pictureRenderData = new PictureRenderData(width, height, picExt, transImg(img, picExt, img.getWidth(), img.getHeight()));
                                        }
                                    }
                                    final XWPFRun xrun = xwpfParagraph.createRun();
                                    final CTRPr ctrPr = run.getCTR().getRPr();
                                    if (ctrPr.getRPrChange() != null) {
                                        ctrPr.unsetRPrChange();
                                    }
                                    xrun.getCTR().setRPr(run.getCTR().getRPr());
                                    PictureRenderPolicy.Helper.renderPicture(xrun, pictureRenderData);
                                    xwpfParagraph.createRun().addBreak();
                                }
                                catch (final Exception e) {
                                    e.printStackTrace();
                                    try {
                                        in.close();
                                    }
                                    catch (final IOException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                                finally {
                                    try {
                                        in.close();
                                    }
                                    catch (final IOException e3) {
                                        e3.printStackTrace();
                                    }
                                }
                            }
                            else if (!StringUtils.isEmpty((CharSequence)pic.getDescription())) {
                                copyImgToWord(pic.getDescription(), xwpfParagraph, filePath, ".jpg", maxWidth, compressWidth);
                            }
                        }
                    }
                }
                else if (run.text() == null || !run.text().startsWith("Evaluation Only. Created with Aspose.Words.")) {
                    final XWPFRun xrun2 = xwpfParagraph.createRun();
                    final CTRPr ctrPr2 = run.getCTR().getRPr();
                    if (ctrPr2.getRPrChange() != null) {
                        ctrPr2.unsetRPrChange();
                    }
                    xrun2.getCTR().setRPr(run.getCTR().getRPr());
                    xrun2.setText(run.text());
                    xrun2.setBold(run.isBold());
                    xrun2.setItalic(run.isItalic());
                    xrun2.setColor(run.getColor());
                    xrun2.setUnderline(run.getUnderline());
                    xrun2.setUnderlineColor(run.getUnderlineColor());
                    xrun2.setSubscript(run.getSubscript());
                    if (run.getFontSize() > 0) {
                        xrun2.setFontSize(run.getFontSize());
                    }
                    run.setFontFamily("\u5b8b\u4f53");
                    if (run.getFontFamily() != null && run.getFontFamily().contains("\u5b8b")) {
                        xrun2.setFontFamily(run.getFontFamily());
                    }
                    TextRenderPolicy.Helper.renderTextRun(xrun2, (Object)new TextRenderData(run.text()));
                }
                else {
                    xwpfParagraph.removeRun(0);
                }
            }
        }
    }
    
    private static void copyImgToWord(String imgUrl, final XWPFParagraph xwpfParagraph, final String filePath, final String picExt, final int maxWidth, final int compressWidth) throws Exception {
        BufferedImage img = null;
        try {
            if (imgUrl.startsWith("data:")) {
                final int start = imgUrl.indexOf(",");
                imgUrl = imgUrl.substring(start + 1);
                imgUrl = imgUrl.replaceAll(" ", "+");
                imgUrl = imgUrl.replaceAll("\r", "");
                imgUrl = imgUrl.replaceAll("\n", "");
                final Base64.Decoder decoder = Base64.getDecoder();
                final byte[] bytes = decoder.decode(imgUrl);
                final ByteArrayInputStream in = new ByteArrayInputStream(bytes);
                img = ImageIO.read(in);
                in.close();
            }
            else if (imgUrl.startsWith("http")) {
                img = ImageIO.read(new URL(imgUrl));
            }
            else {
                if (filePath == null) {
                    return;
                }
                try {
                    img = ImageIO.read(new File(filePath + imgUrl));
                }
                catch (final Exception e) {
                    final ImageInputStream stream = ImageIO.createImageInputStream(new File(filePath + imgUrl));
                    final Iterator<ImageReader> iter = ImageIO.getImageReaders(stream);
                    while (iter.hasNext()) {
                        ImageReader reader = null;
                        try {
                            reader = iter.next();
                            final ImageReadParam param = reader.getDefaultReadParam();
                            reader.setInput(stream, true, true);
                            final Iterator<ImageTypeSpecifier> imageTypes = reader.getImageTypes(0);
                            while (imageTypes.hasNext()) {
                                final ImageTypeSpecifier imageTypeSpecifier = imageTypes.next();
                                final int bufferedImageType = imageTypeSpecifier.getBufferedImageType();
                                if (bufferedImageType == 10) {
                                    param.setDestinationType(imageTypeSpecifier);
                                    break;
                                }
                            }
                            img = reader.read(0, param);
                            if (null != img) {
                                break;
                            }
                            continue;
                        }
                        catch (final Exception e2) {
                            throw e2;
                        }
                        finally {
                            if (null != reader) {
                                reader.dispose();
                            }
                            if (null != stream) {
                                stream.close();
                            }
                        }
                    }
                }
            }
        }
        catch (final Exception e) {
            e.printStackTrace();
        }
        if (img != null) {
            int width = img.getWidth();
            int height = img.getHeight();
            PictureRenderData pictureRenderData = new PictureRenderData(width, height, picExt, img);
            if (maxWidth > 0 && width > maxWidth) {
                height /= (int)(width / new Double(maxWidth));
                width = maxWidth;
                pictureRenderData = new PictureRenderData(width, height, picExt, img);
                if (compressWidth > 0 && img.getWidth() > compressWidth) {
                    pictureRenderData = new PictureRenderData(width, height, picExt, transImg(img, picExt, img.getWidth(), img.getHeight()));
                }
            }
            final XWPFRun xrun = xwpfParagraph.createRun();
            PictureRenderPolicy.Helper.renderPicture(xrun, pictureRenderData);
            xwpfParagraph.createRun().addBreak();
        }
    }
    
    private static void copyXWPFTableRow(final XWPFTable xwpfTable, final XWPFTableRow sourceRow, final int rowIndex, final String filePath, final int maxWidth, final int compressWidth) throws Exception {
        final XWPFTableRow targetRow = xwpfTable.insertNewTableRow(rowIndex);
        targetRow.getCtRow().setTrPr(sourceRow.getCtRow().getTrPr());
        final List<XWPFTableCell> cellList = sourceRow.getTableCells();
        if (null != cellList) {
            for (final XWPFTableCell sourceCell : cellList) {
                final XWPFTableCell targetCell = targetRow.addNewTableCell();
                targetCell.getCTTc().setTcPr(sourceCell.getCTTc().getTcPr());
                targetCell.getCTTc().getTcPr().setVAlign(sourceCell.getCTTc().getTcPr().getVAlign());
                targetCell.getCTTc().getTcPr().setTcBorders(sourceCell.getCTTc().getTcPr().getTcBorders());
                if (sourceCell.getParagraphs() != null && sourceCell.getParagraphs().size() > 0) {
                    final List<XWPFParagraph> paragraphs = sourceCell.getParagraphs();
                    for (int i = 0; i < paragraphs.size(); ++i) {
                        try {
                            final XWPFParagraph sourceParagraph = paragraphs.get(i);
                            final XWPFParagraph xwpfParagraph = targetCell.getParagraphs().get(i);
                            copyXWPFParagraph(xwpfParagraph, sourceParagraph, filePath, maxWidth, compressWidth);
                        }
                        catch (final Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                else {
                    targetCell.setText(sourceCell.getText());
                }
            }
        }
    }
    
    private static String transHtml(final String filePath, String html) {
        try {
            html = html.replace("<br/>", "<p></p>");
            html = html.replace("<br>", "<p></p>");
            String string = "";
            final String[] arr = html.split("<img");
            for (int i = 0; i < arr.length; ++i) {
                final String str = arr[i];
                if (str.contains("src=")) {
                    String src = "";
                    final int index = str.indexOf("src=");
                    final char ch = str.charAt(index + 4);
                    for (int j = index + 5; j < str.length(); ++j) {
                        final char c = str.charAt(j);
                        if (c == ch) {
                            break;
                        }
                        src += c;
                    }
                }
                string = string + (string.equals("") ? "" : "<img") + str;
            }
            return string;
        }
        catch (final Exception e) {
            e.printStackTrace();
            return html;
        }
    }
    
    private static byte[] transImg(final BufferedImage source, final String picExt, final int w, final int h) {
        byte[] b = null;
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            final BufferedImage tag = new BufferedImage(w, h, 1);
            tag.getGraphics().drawImage(source, 0, 0, w, h, null);
            ImageIO.write(tag, picExt.replace(".", ""), os);
            b = os.toByteArray();
        }
        catch (final Exception e) {
            e.printStackTrace();
            try {
                os.close();
            }
            catch (final Exception e1) {
                e1.printStackTrace();
            }
        }
        finally {
            try {
                os.close();
            }
            catch (final Exception e2) {
                e2.printStackTrace();
            }
        }
        return b;
    }
}
