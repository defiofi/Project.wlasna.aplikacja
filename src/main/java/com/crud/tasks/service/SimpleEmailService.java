package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j      //ADNOTACJA LOGERA
@Service
@RequiredArgsConstructor
public class SimpleEmailService {             //Klasa do stworzenia i wysłania mail
    private final JavaMailSender javaMailSender;

    public void send(Mail mail){
        log.info("Rozpoczęcie przygotowania maila...");
        try{
            SimpleMailMessage mailMessage = createMailMessage(mail);
            javaMailSender.send(mailMessage);
            log.info("email wyslany");
        } catch (MailException e){
            log.error("Process wyslania maila nieudany"+ e.getMessage(), e);
        }
    }
    private SimpleMailMessage createMailMessage(Mail mail){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        if (mail.getToCc() != "" && mail.getToCc() != null ){
            mailMessage.setCc(mail.getToCc());
        }
        return mailMessage;
    }
}
