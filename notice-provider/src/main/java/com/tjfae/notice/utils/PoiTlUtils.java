// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.utils;

import java.util.ArrayList;
import org.jsoup.nodes.Node;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import java.util.HashMap;
import java.util.Map;
import com.deepoove.poi.util.TableTools;
import org.jsoup.Jsoup;
import java.io.OutputStream;
import java.awt.image.RenderedImage;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageReadParam;
import java.util.Iterator;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import com.deepoove.poi.policy.PictureRenderPolicy;
import com.deepoove.poi.data.PictureRenderData;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageReader;
import java.io.File;
import java.net.URL;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.List;
import java.util.ListIterator;
import org.apache.commons.lang3.StringUtils;
import cn.hutool.core.util.ReUtil;
import com.deepoove.poi.policy.TextRenderPolicy;
import com.deepoove.poi.data.TextRenderData;
import org.apache.poi.xwpf.usermodel.VerticalAlign;
import java.util.Collection;
import cn.hutool.core.collection.CollUtil;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import com.deepoove.poi.xwpf.BodyContainer;
import org.jsoup.nodes.Element;
import com.deepoove.poi.policy.AbstractRenderPolicy;
import org.springframework.stereotype.Component;

@Component
public class PoiTlUtils
{
    public static AbstractRenderPolicy<Object> createHtmlRenderPolicy(final String filePath, final int maxWidth, final int compressWidth) {
        // TODO: 实现HTML渲染策略，当前返回null以让编译通过
        return null;
    }
    
    public static String htmlInit(String s) {
        final String regex = "<(\\w+)([^<>]*)>([^<>]+)</\\1>";
        final String str = s.replaceAll(regex, "\\^");
        final String[] result = str.split("\\^");
        for (int i = 0; i < result.length; ++i) {
            if (!result[i].equals("")) {
                s = s.replaceAll(result[i], "<span>" + result[i] + "</span>");
            }
        }
        return s;
    }
    
    private static XWPFParagraph parseHtmlToWord(final Element ele, final BodyContainer bodyContainer, XWPFParagraph xwpfParagraph, final boolean isParent, final String filePath, final int maxWidth, final int compressWidth) throws Exception {
        if ("img".equals(ele.tagName())) {
            parseImgToWord(ele.attr("src"), xwpfParagraph, filePath, maxWidth, compressWidth);
            return xwpfParagraph;
        }
        if ("table".equals(ele.tagName())) {
            final XWPFParagraph prev = getPrevXWPFParagraph(bodyContainer, xwpfParagraph);
            if (prev.getRuns().size() == 0) {
                prev.createRun();
            }
            xwpfParagraph = bodyContainer.insertNewParagraph((XWPFRun)CollUtil.get((Collection)prev.getRuns(), -1));
            parseTableToWord(bodyContainer, ele, xwpfParagraph.createRun(), filePath, maxWidth, compressWidth);
            return xwpfParagraph;
        }
        if ("sup".equalsIgnoreCase(ele.tagName())) {
            final XWPFRun run = xwpfParagraph.createRun();
            run.setText(ele.text());
            run.setBold(true);
            run.setFontSize(12);
            run.setFontFamily("Times New Roman", XWPFRun.FontCharRange.ascii);
            run.setFontFamily("\u5b8b\u4f53", XWPFRun.FontCharRange.eastAsia);
            run.setSubscript(VerticalAlign.SUPERSCRIPT);
            TextRenderPolicy.Helper.renderTextRun(run, (Object)new TextRenderData(ele.text()));
            return xwpfParagraph;
        }
        if ("strong".equalsIgnoreCase(ele.tagName()) || "b".equalsIgnoreCase(ele.tagName())) {
            final XWPFRun run = xwpfParagraph.createRun();
            run.setText(ele.text());
            run.setBold(true);
            TextRenderPolicy.Helper.renderTextRun(run, (Object)new TextRenderData(ele.text()));
            return xwpfParagraph;
        }
        final String text = ele.ownText();
        final boolean continueItr = true;
        final boolean enabledBreak = ReUtil.isMatch("(p|h[12345]|li|img)", (CharSequence)ele.tagName());
        if (enabledBreak) {
            final XWPFRun run2 = xwpfParagraph.createRun();
            run2.addBreak();
        }
        if (StringUtils.isNotBlank((CharSequence)text)) {
            final XWPFRun run2 = xwpfParagraph.createRun();
            TextRenderPolicy.Helper.renderTextRun(run2, (Object)new TextRenderData(text));
        }
        if (continueItr && ele.children().size() > 0) {
            final ListIterator<Element> itr = ele.children().listIterator();
            while (itr.hasNext()) {
                final Element me = itr.next();
                xwpfParagraph = parseHtmlToWord(me, bodyContainer, xwpfParagraph, false, filePath, maxWidth, compressWidth);
            }
        }
        return xwpfParagraph;
    }
    
    private static XWPFParagraph getPrevXWPFParagraph(final BodyContainer bodyContainer, final XWPFParagraph xwpfParagraph) {
        final List<XWPFParagraph> xwpfParagraphs = bodyContainer.getTarget().getParagraphs();
        for (int i = 0; i < xwpfParagraphs.size(); ++i) {
            if (xwpfParagraphs.get(i).equals(xwpfParagraph)) {
                return xwpfParagraphs.get(i + 1);
            }
        }
        return xwpfParagraph;
    }
    
    private static void parseImgToWord(String imgUrl, final XWPFParagraph xwpfParagraph, final String filePath, final int maxWidth, final int compressWidth) throws Exception {
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
            PictureRenderData pictureRenderData = new PictureRenderData(width, height, ".jpg", img);
            if (maxWidth > 0 && width > maxWidth) {
                height /= (int)(width / new Double(maxWidth));
                width = maxWidth;
                if (height > 900) {
                    height = 900;
                    width /= (int)(height / new Double(900.0));
                }
                pictureRenderData = new PictureRenderData(width, height, ".jpg", img);
                if (img.getWidth() > ((compressWidth == 0) ? 1920 : compressWidth)) {
                    pictureRenderData = new PictureRenderData(width, height, ".jpg", trans(img, img.getWidth(), img.getHeight()));
                }
            }
            final XWPFRun run = xwpfParagraph.createRun();
            PictureRenderPolicy.Helper.renderPicture(run, pictureRenderData);
        }
    }
    
    public static byte[] trans(final BufferedImage source, final int w, final int h) {
        byte[] b = null;
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            final BufferedImage tag = new BufferedImage(w, h, 1);
            tag.getGraphics().drawImage(source, 0, 0, w, h, null);
            ImageIO.write(tag, "jpg", os);
            b = os.toByteArray();
        }
        catch (final Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                os.close();
            }
            catch (final Exception ex) {}
        }
        return b;
    }
    
    private static void parseTableToWord(final BodyContainer bodyContainer, final Element ele, final XWPFRun run, final String filePath, final int maxWidth, final int compressWidth) throws Exception {
        final Document tableDoc = Jsoup.parse(simplifyTable(ele.outerHtml()));
        final Elements trList = tableDoc.getElementsByTag("tr");
        final Elements tdList = getElementsThOrTdByTag((Element)trList.get(0));
        final XWPFTable xwpfTable = bodyContainer.insertNewTable(run, trList.size(), tdList.size());
        TableTools.widthTable(xwpfTable, 14.65f, tdList.size());
        TableTools.borderTable(xwpfTable, 4);
        final Map<String, Boolean>[][] array = new Map[trList.size()][tdList.size()];
        for (int row = 0; row < trList.size(); ++row) {
            final Element trElement = (Element)trList.get(row);
            final Elements tds = getElementsThOrTdByTag(trElement);
            for (int col = 0; col < tds.size(); ++col) {
                final Element colElement = (Element)tds.get(col);
                final String colspan = colElement.attr("colspan");
                final String rowspan = colElement.attr("rowspan");
                final String style = colElement.attr("style");
                final StringBuilder styleSB = new StringBuilder();
                if (!StringUtils.isEmpty((CharSequence)colspan)) {
                    for (int colCount = Integer.parseInt(colspan), i = 0; i < colCount - 1; ++i) {
                        (array[row][col + i + 1] = new HashMap<String, Boolean>()).put("mergeCol", true);
                    }
                }
                if (!StringUtils.isEmpty((CharSequence)rowspan)) {
                    for (int rowCount = Integer.parseInt(rowspan), i = 0; i < rowCount - 1; ++i) {
                        (array[row + i + 1][col] = new HashMap<String, Boolean>()).put("mergeRow", true);
                    }
                }
                final XWPFTableCell tableCell = xwpfTable.getRow(row).getCell(col);
                if (StringUtils.isEmpty((CharSequence)colspan)) {
                    if (col == 0) {
                        if (tableCell.getCTTc().getTcPr() == null) {
                            tableCell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
                        }
                        else if (tableCell.getCTTc().getTcPr().getHMerge() == null) {
                            tableCell.getCTTc().getTcPr().addNewHMerge().setVal(STMerge.RESTART);
                        }
                        else {
                            tableCell.getCTTc().getTcPr().getHMerge().setVal(STMerge.RESTART);
                        }
                    }
                    else if (array[row][col] != null && array[row][col].get("mergeCol") != null && array[row][col].get("mergeCol")) {
                        if (tableCell.getCTTc().getTcPr() == null) {
                            tableCell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
                            continue;
                        }
                        if (tableCell.getCTTc().getTcPr().getHMerge() == null) {
                            tableCell.getCTTc().getTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
                            continue;
                        }
                        tableCell.getCTTc().getTcPr().getHMerge().setVal(STMerge.CONTINUE);
                        continue;
                    }
                    else if (tableCell.getCTTc().getTcPr() == null) {
                        tableCell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
                    }
                    else if (tableCell.getCTTc().getTcPr().getHMerge() == null) {
                        tableCell.getCTTc().getTcPr().addNewHMerge().setVal(STMerge.RESTART);
                    }
                    else {
                        tableCell.getCTTc().getTcPr().getHMerge().setVal(STMerge.RESTART);
                    }
                }
                else if (tableCell.getCTTc().getTcPr() == null) {
                    tableCell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
                }
                else if (tableCell.getCTTc().getTcPr().getHMerge() == null) {
                    tableCell.getCTTc().getTcPr().addNewHMerge().setVal(STMerge.RESTART);
                }
                else {
                    tableCell.getCTTc().getTcPr().getHMerge().setVal(STMerge.RESTART);
                }
                if (StringUtils.isEmpty((CharSequence)rowspan)) {
                    if (array[row][col] != null && array[row][col].get("mergeRow") != null && array[row][col].get("mergeRow")) {
                        if (tableCell.getCTTc().getTcPr() == null) {
                            tableCell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
                            continue;
                        }
                        if (tableCell.getCTTc().getTcPr().getVMerge() == null) {
                            tableCell.getCTTc().getTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
                            continue;
                        }
                        tableCell.getCTTc().getTcPr().getVMerge().setVal(STMerge.CONTINUE);
                        continue;
                    }
                    else if (tableCell.getCTTc().getTcPr() == null) {
                        tableCell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
                    }
                    else if (tableCell.getCTTc().getTcPr().getVMerge() == null) {
                        tableCell.getCTTc().getTcPr().addNewVMerge().setVal(STMerge.RESTART);
                    }
                    else {
                        tableCell.getCTTc().getTcPr().getVMerge().setVal(STMerge.RESTART);
                    }
                }
                else if (tableCell.getCTTc().getTcPr() == null) {
                    tableCell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
                }
                else if (tableCell.getCTTc().getTcPr().getVMerge() == null) {
                    tableCell.getCTTc().getTcPr().addNewVMerge().setVal(STMerge.RESTART);
                }
                else {
                    tableCell.getCTTc().getTcPr().getVMerge().setVal(STMerge.RESTART);
                }
                tableCell.removeParagraph(0);
                final XWPFParagraph paragraph = tableCell.addParagraph();
                paragraph.setStyle(styleSB.toString());
                if (!StringUtils.isEmpty((CharSequence)style) && style.contains("text-align:center")) {
                    paragraph.setAlignment(ParagraphAlignment.CENTER);
                }
                parseHtmlToWord(colElement, bodyContainer, paragraph, true, filePath, maxWidth, compressWidth);
            }
        }
    }
    
    private static String simplifyTable(final String tableContent) {
        if (StringUtils.isEmpty((CharSequence)tableContent)) {
            return null;
        }
        final Document tableDoc = Jsoup.parse(tableContent);
        final Elements trElements = tableDoc.getElementsByTag("tr");
        if (trElements != null) {
            final Iterator<Element> eleIterator = trElements.iterator();
            Integer rowNum = 0;
            while (eleIterator.hasNext()) {
                ++rowNum;
                final Element trElement = eleIterator.next();
                trElement.removeAttr("class");
                final Elements tdElements = getElementsThOrTdByTag(trElement);
                final List<Element> tdEleList = covertElements2List(tdElements);
                for (int i = 0; i < tdEleList.size(); ++i) {
                    final Element curTdElement = tdEleList.get(i);
                    curTdElement.removeAttr("class");
                    final Element ele = curTdElement.clone();
                    final String colspanValStr = curTdElement.attr("colspan");
                    if (!StringUtils.isEmpty((CharSequence)colspanValStr)) {
                        ele.removeAttr("colspan");
                        final Integer colspanVal = Integer.parseInt(colspanValStr);
                        for (int k = 0; k < colspanVal - 1; ++k) {
                            curTdElement.after(ele.outerHtml());
                        }
                    }
                }
            }
            final List<Element> trEleList = covertElements2List(trElements);
            final Element firstTrEle = trElements.first();
            final Elements tdElements2 = (firstTrEle == null) ? new Elements() : getElementsThOrTdByTag(firstTrEle);
            final Integer tdCount = tdElements2.size();
            for (int j = 0; j < tdElements2.size(); ++j) {
                for (final Element trElement2 : trEleList) {
                    final List<Element> tdElementList = covertElements2List(getElementsThOrTdByTag(trElement2));
                    try {
                        tdElementList.get(j);
                    }
                    catch (final Exception e) {
                        continue;
                    }
                    final Node curTdNode = (Node)tdElementList.get(j);
                    final Node cNode = curTdNode.clone();
                    final String rowspanValStr = curTdNode.attr("rowspan");
                    if (!StringUtils.isEmpty((CharSequence)rowspanValStr)) {
                        cNode.removeAttr("rowspan");
                        Element nextTrElement = trElement2.nextElementSibling();
                        final Integer rowspanVal = Integer.parseInt(rowspanValStr);
                        for (int l = 0; l < rowspanVal - 1; ++l) {
                            final Node tempNode = cNode.clone();
                            final List<Node> nodeList = new ArrayList<Node>();
                            nodeList.add(tempNode);
                            if (l > 0) {
                                nextTrElement = nextTrElement.nextElementSibling();
                            }
                            Integer indexNum = j + 1;
                            if (j == 0) {
                                indexNum = 0;
                            }
                            if (indexNum.equals(tdCount)) {
                                nextTrElement.appendChild(tempNode);
                            }
                            else {
                                nextTrElement.insertChildren((int)indexNum, (Collection)nodeList);
                            }
                        }
                    }
                }
            }
        }
        final Element tableEle = tableDoc.getElementsByTag("table").first();
        final String tableHtml = tableEle.outerHtml();
        return tableHtml;
    }
    
    private static Elements getElementsThOrTdByTag(final Element element) {
        final Elements th = element.getElementsByTag("th");
        if (th == null || th.isEmpty()) {
            return element.getElementsByTag("td");
        }
        return th;
    }
    
    private static List<Element> covertElements2List(final Elements curElements) {
        final List<Element> elementList = new ArrayList<Element>();
        for (final Element curlement : curElements) {
            elementList.add(curlement);
        }
        return elementList;
    }
}
