package com.example.mail;

import org.springframework.stereotype.Service;

public interface EmailService {

    void sendSimpleMail(String sendTo,String title,String content);
}
