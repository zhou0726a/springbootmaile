package com.example.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private Email email;

    @Autowired
    private JavaMailSender mailSender;
    @Override
    public void sendSimpleMail(String sendTo, String title, String content) {
        SimpleMailMessage messgae = new SimpleMailMessage();
        messgae.setTo(sendTo);
        messgae.setFrom(email.getEmailFrom());
        messgae.setSubject(title);
        messgae.setText(content);
        mailSender.send(messgae);
    }
}
