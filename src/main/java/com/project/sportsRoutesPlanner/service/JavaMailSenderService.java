package com.project.sportsRoutesPlanner.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JavaMailSenderService {

    private JavaMailSender javaMailSender;

    @Autowired
    public JavaMailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendHTML(String from, String emailAddress, String confirm_account, String linkCreator) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(emailAddress);
        mail.setFrom(from);
        mail.setSubject(confirm_account);
        mail.setText(linkCreator);
        javaMailSender.send(mail);
    }
}
