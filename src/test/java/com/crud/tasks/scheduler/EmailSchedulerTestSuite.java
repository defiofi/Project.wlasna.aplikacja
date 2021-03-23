package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class EmailSchedulerTestSuite {
    @Mock
    private JavaMailSender javaMailSender;
    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void TestEmailSchedulerSendInformationMail(){
        //GIVEN
        SimpleEmailService simpleEmailService = new SimpleEmailService(javaMailSender);
        EmailScheduler emailScheduler = new EmailScheduler(simpleEmailService, taskRepository, new AdminConfig());

        //WHEN
        Mail mail = emailScheduler.sendInformationEmail();

        //THEN
        assertEquals("Tasks: Once a day email", mail.getSubject());
        assertTrue( mail.getMessage().length()>0);
    }
}
