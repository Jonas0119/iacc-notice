// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.service.impl;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.ByteArrayResource;
import org.apache.commons.io.IOUtils;
import javax.mail.internet.MimeUtility;
import org.springframework.core.io.InputStreamResource;
import java.io.InputStream;
import javax.mail.internet.MimeMessage;
import javax.mail.MessagingException;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import java.io.File;
import com.tjfae.notice.entity.ToEmail;
import org.springframework.beans.factory.annotation.Value;
import javax.annotation.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl
{
    @Resource
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;
    
    public Object enclosureEmail(final ToEmail toEmail, final File attachment) throws Exception {
        final MimeMessage message = this.mailSender.createMimeMessage();
        try {
            final MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(this.from);
            helper.setTo(toEmail.getTos());
            helper.setSubject(toEmail.getSubject());
            helper.setText(toEmail.getContent(), true);
            final FileSystemResource file = new FileSystemResource(attachment);
            final String filename = file.getFilename();
            helper.addAttachment(filename, (InputStreamSource)file);
            this.mailSender.send(message);
            return null;
        }
        catch (final MessagingException e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public Object enclosureEmail(final ToEmail toEmail, final String path) throws Exception {
        final MimeMessage message = this.mailSender.createMimeMessage();
        try {
            final MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(this.from);
            helper.setTo(toEmail.getTos());
            helper.setSubject(toEmail.getSubject());
            helper.setText(toEmail.getContent(), true);
            final FileSystemResource file = new FileSystemResource(path);
            final String filename = file.getFilename();
            helper.addAttachment(filename, (InputStreamSource)file);
            this.mailSender.send(message);
            return null;
        }
        catch (final MessagingException e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public Object enclosureEmail(final ToEmail toEmail, final InputStream inputStream, final String filename) throws Exception {
        final MimeMessage message = this.mailSender.createMimeMessage();
        try {
            final MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(this.from);
            helper.setTo(toEmail.getTos());
            helper.setSubject(toEmail.getSubject());
            helper.setText(toEmail.getContent(), true);
            final InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
            helper.addAttachment(MimeUtility.encodeWord(filename), (InputStreamSource)new ByteArrayResource(IOUtils.toByteArray(inputStreamResource.getInputStream())));
            this.mailSender.send(message);
            return null;
        }
        catch (final MessagingException e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    private File MultipartFileToFile(final MultipartFile multiFile) {
        final String fileName = multiFile.getOriginalFilename();
        final String prefix = fileName.substring(fileName.lastIndexOf("."));
        try {
            final File file = File.createTempFile(fileName, prefix);
            multiFile.transferTo(file);
            return file;
        }
        catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
