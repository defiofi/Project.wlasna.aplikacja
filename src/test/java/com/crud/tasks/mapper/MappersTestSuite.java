package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MappersTestSuite {

    @Test
     public void TestTrelloMapperMapToBoards(){
        //GIVEN
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        TrelloListDto trelloListDto1 = new TrelloListDto("123456","mała", false);
        trelloListDtos.add(trelloListDto1);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("Jedynka","567890",trelloListDtos);
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDto);
        TrelloMapper trelloMapper = new TrelloMapper();

        //WHEN
        List<TrelloBoard> trelloBoardLists = trelloMapper.mapToBoards(trelloBoardDtoList);

        //THEN
        assertEquals(1,trelloBoardLists.size());
        assertEquals("567890",trelloBoardLists.get(0).getId());
        assertEquals("Jedynka",trelloBoardLists.get(0).getName());
        assertEquals(1,trelloBoardLists.get(0).getLists().size());

    }
    @Test
    public void TestTrelloMapperMapToBoardsDto(){
        //GIVEN
        List<TrelloList> trelloLists = new ArrayList<>();
        TrelloList trelloList1 = new TrelloList("12345","bardzo mała", false);
        trelloLists.add(trelloList1);
        TrelloBoard trelloBoard = new TrelloBoard("23456", "Dwójka",trelloLists );
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(trelloBoard);
        TrelloMapper trelloMapper = new TrelloMapper();

        //WHEN
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);

        //THEN
        assertEquals(1,trelloBoardDtoList.size());
        assertEquals("23456",trelloBoardDtoList.get(0).getId());
        assertEquals("Dwójka",trelloBoardDtoList.get(0).getName());
        assertEquals(1,trelloBoardDtoList.get(0).getLists().size());
    }
    @Test
    public void TestTrelloMapperMapToList(){
        //GIVEN
        TrelloListDto trelloListDto1 = new TrelloListDto("34567","średnia", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("45678","inna średnia", false);
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        trelloListDtoList.add(trelloListDto1);
        trelloListDtoList.add(trelloListDto2);
        TrelloMapper trelloMapper = new TrelloMapper();

        //WHEN
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtoList);

        //THEN
        assertEquals(2,trelloLists.size());
        assertEquals("34567",trelloLists.get(0).getId());
        assertEquals("średnia",trelloLists.get(0).getName());
        assertEquals(false,trelloLists.get(0).isClosed());
        assertEquals("45678",trelloLists.get(1).getId());
        assertEquals("inna średnia",trelloLists.get(1).getName());
        assertEquals(false,trelloLists.get(1).isClosed());
    }
    @Test
    public void TestTrelloMapperMapToListDto(){
        //GIVEN
        TrelloList trelloList1 = new TrelloList("567890", "Taka większa", false);
        TrelloList trelloList2 = new TrelloList("678901", "Taka jeszcze większa", false);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);
        TrelloMapper trelloMapper = new TrelloMapper();

        //WHEN
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);

        //THEN
        assertEquals(2,trelloListDtos.size());
        assertEquals("567890",trelloListDtos.get(0).getId());
        assertEquals("Taka większa",trelloListDtos.get(0).getName());
        assertEquals(false,trelloListDtos.get(0).isClosed());
        assertEquals("678901",trelloListDtos.get(1).getId());
        assertEquals("Taka jeszcze większa",trelloListDtos.get(1).getName());
        assertEquals(false,trelloListDtos.get(1).isClosed());
    }

    @Test
    public void TestTrelloMapperMapToCardDto(){
        //GIVEN
        TrelloCard trelloCard = new TrelloCard("Pierwsza Karta", "To jest pierwsza karta...", "test", "123456");
        TrelloMapper trelloMapper = new TrelloMapper();

        //WHEN
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //THEN
        assertEquals("Pierwsza Karta",trelloCardDto.getName());
        assertEquals("To jest pierwsza karta...",trelloCardDto.getDescription());
        assertEquals("test",trelloCardDto.getPos());
        assertEquals("123456",trelloCardDto.getListId());
    }
    @Test
    public void TestTrelloMapperMapToCard(){
        //GIVEN
        TrelloCardDto trelloCardDto = new TrelloCardDto("Druga karta", "To jest druga karta...", "Test", "234567");
        TrelloMapper trelloMapper = new TrelloMapper();

        //WHEN
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //THEN
        assertEquals("Druga karta",trelloCard.getName());
        assertEquals("To jest druga karta...",trelloCard.getDescription());
        assertEquals("Test",trelloCard.getPos());
        assertEquals("234567",trelloCard.getListId());
    }
    @Test
    public void TestTaskMapperMapToTask(){
        //GIVEN
        TaskDTO taskDto = new TaskDTO(123456L,"Pierwsze zadanie", "Bardzo poważny tekst pierwszego zadania");
        TaskMapper taskMapper = new TaskMapper();

        //WHEN
        Task task = taskMapper.mapToTask(taskDto);

        //THEN
        assertEquals(123456L,task.getId());
        assertEquals("Pierwsze zadanie",task.getTitle());
        assertEquals("Bardzo poważny tekst pierwszego zadania",task.getContent());
    }
    @Test
    public void TestTaskMapperMapToTaskDto(){
        //GIVEN
        Task task = new Task(23456L, "Drugie zadanie", "Jeszcze bardziej poważny tekst");
        TaskMapper taskMapper = new TaskMapper();

        //WHEN
        TaskDTO taskDTO = taskMapper.mapToTaskDto(task);

        //THEN
        assertEquals(23456L,taskDTO.getId());
        assertEquals("Drugie zadanie",taskDTO.getTitle());
        assertEquals("Jeszcze bardziej poważny tekst",taskDTO.getContent());
    }
    @Test
    public void TestTaskMapperMapToTaskDtoList(){
        //GIVEN
        List<Task> taskList = new ArrayList<>();
        Task task1 = new Task(34567L, "Trzecie zadanie", "Mało poważny tekst");
        Task task2 = new Task(45678L, "Czwarte zadanie", "Jeszcze mniej poważny tekst");
        taskList.add(task1);
        taskList.add(task2);
        TaskMapper taskMapper = new TaskMapper();

        //WHEN
        List<TaskDTO> taskDTOList = taskMapper.mapToTaskDtoList(taskList);

        //THEN
        assertEquals(2,taskDTOList.size());
        assertEquals(34567L,taskDTOList.get(0).getId());
        assertEquals("Trzecie zadanie",taskDTOList.get(0).getTitle());
        assertEquals("Mało poważny tekst",taskDTOList.get(0).getContent());
        assertEquals(45678L,taskDTOList.get(1).getId());
        assertEquals("Czwarte zadanie",taskDTOList.get(1).getTitle());
        assertEquals("Jeszcze mniej poważny tekst",taskDTOList.get(1).getContent());
    }
}
