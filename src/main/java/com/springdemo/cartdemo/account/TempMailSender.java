package com.springdemo.cartdemo.account;

import java.io.InputStream;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;


@Component
public class TempMailSender implements JavaMailSender {

    @Override
    public void send(SimpleMailMessage simpleMessage) throws MailException {
        System.out.println(simpleMessage);
    }

    @Override
    public void send(SimpleMailMessage... simpleMessages) throws MailException {
    }

    @Override
    public MimeMessage createMimeMessage() {
        return null;
    }

    @Override
    public MimeMessage createMimeMessage(InputStream contentStream) throws MailException {
        return null;
    }

    @Override
    public void send(MimeMessage mimeMessage) throws MailException {
    }

    @Override
    public void send(MimeMessage... mimeMessages) throws MailException {
    }

    @Override
    public void send(MimeMessagePreparator mimeMessagePreparator) throws MailException {
    }

    @Override
    public void send(MimeMessagePreparator... mimeMessagePreparators) throws MailException {
    }

}
