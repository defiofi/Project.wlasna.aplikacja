package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TrelloServiceTestSuite {
    @Mock
    private JavaMailSender javaMailSender;
    @Autowired
    private TrelloClient trelloClient;
    @Autowired
    private TrelloConfig trelloConfig;
    @Test
    public void TrelloServiceFetchTrelloBoards(){
        //GIVEN
        SimpleEmailService simpleEmailService = new SimpleEmailService(javaMailSender);
        TrelloService trelloService = new TrelloService(trelloClient, simpleEmailService, new AdminConfig());

        //WHEN
        List<TrelloBoardDto> trelloBoardDtoList =  trelloService.fetchTrelloBoards();

        //THEN
        assertTrue(trelloBoardDtoList.size()>0);

    }
    @Test
    public void TrelloServiceCreateTrelloCart(){
        //GIVEN
        SimpleEmailService simpleEmailService = new SimpleEmailService(javaMailSender);
        TrelloService trelloService = new TrelloService(trelloClient, simpleEmailService, new AdminConfig());
        TrelloCardDto trelloCardDto = new TrelloCardDto("Nowa karta testowa", "Test", "","5fda393236bd820aade1a871");
        //WHEN
        CreatedTrelloCardDto createdTrelloCardDto =  trelloService.createTrelloCard(trelloCardDto);
        //THEN
        assertEquals("Nowa karta testowa", createdTrelloCardDto.getName());

    }
}
