package com.crud.tasks.trello.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public List<String> getCompanyDetails(){
        List<String> stringList = new ArrayList<>();
        stringList.add("Company Name: "+companyName);
        stringList.add("Company goal: "+companyGoal);
        stringList.add("Company Phone: "+companyPhone);
        stringList.add("Company email: "+companyEmail);
        return stringList;
    }
}
