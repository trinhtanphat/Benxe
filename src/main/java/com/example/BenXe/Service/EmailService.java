package com.example.BenXe.Service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class EmailService {
    JavaMailSender javaMailSender = new JavaMailSender() {
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

        @Override
        public void send(SimpleMailMessage simpleMessage) throws MailException {

        }

        @Override
        public void send(SimpleMailMessage... simpleMessages) throws MailException {

        }
    };
    private JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

//    @Autowired
//    public EmailService(JavaMailSender mailSender) {
//        this.mailSender = mailSender;
//    }

    public void sendEmail(String to, String subject, String text) {
        mailSender.setHost("smtp.gmail.com"); // Thiết lập địa chỉ máy chủ SMTP
        mailSender.setPort(587); // Thiết lập cổng SMTP
        mailSender.setUsername("shoeshop.gov@gmail.com"); // Thiết lập tên đăng nhập SMTP
        mailSender.setPassword("yunhamctbfcfnusm"); // Thiết lập mật khẩu SMTP

// Các cài đặt bổ sung, ví dụ: cấu hình TLS/SSL, timeout, v.v.
        mailSender.getJavaMailProperties().setProperty("mail.smtp.starttls.enable", "true");
        mailSender.getJavaMailProperties().setProperty("mail.smtp.timeout", "5000");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("trinhtanphat2403@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}