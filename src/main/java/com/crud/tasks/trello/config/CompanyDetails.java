package com.crud.tasks.trello.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CompanyDetails {
    @Value("${info.company.name}")
    private String companyName;
    @Value("${info.company.email}")
    private String companyEmail;
    @Value("${info.company.phone}")
    private String companyPhone;
    @Value("${info.company.goal}")
    private String companyGoal;

    public String getCompanyDetails(){
        return "Company Name: "+companyName+"    "+"Company goal: "+companyGoal+"    "+"Company Phone: "+companyPhone+"    "+
        "Company email: "+companyEmail;
    }
}
