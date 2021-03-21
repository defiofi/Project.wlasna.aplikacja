package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.pretty.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Slf4j      //ADNOTACJA LOGERA
@Service
@RequiredArgsConstructor
public class SimpleEmailService {             //Klasa do stworzenia i wysłania mail
    private final JavaMailSender javaMailSender;
    @Autowired
    private MailCreatorService mailCreatorService;

    public void send(Mail mail){
        log.info("Rozpoczęcie przygotowania maila...");
        try{
            MimeMessagePreparator mailMessage = createMimeMessage(mail);
            javaMailSender.send(mailMessage);
            log.info("email wyslany");
        } catch (MailException e){
            log.error("Process wyslania maila nieudany"+ e.getMessage(), e);
        }
    }

    private MimeMessagePreparator createMimeMessage(final Mail mail){
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()), true);
        };
    }

    public SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()));
        return mailMessage;
    }
}
