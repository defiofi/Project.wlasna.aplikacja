package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.trello.config.CompanyDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyDetails companyDetails;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend/");     //http://localhost:8888/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message","Z poważaniem \nAutor aplikacji");
        context.setVariable("company_details", companyDetails.getCompanyDetails());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        context.setVariable("show_functionality", true);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
    public String buildInformationEmail(){
        long size = taskRepository.count();
        String message;
        if (size == 1){
            message = "Currently in database you got only one task";
        } else {
            message =  "Currently in database you got: " + size + " tasks";
        }
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("admin_name", "Mariusz");
        context.setVariable("goodbye_message","Pozdrowienia, miłego dnia");
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("company_details", companyDetails.getCompanyDetails());
        context.setVariable("admin_config", adminConfig);
        context.setVariable("show_functionality", false);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
