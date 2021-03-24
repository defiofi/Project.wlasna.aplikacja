package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDTO;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControlerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TaskMapper taskMapper;
    @MockBean
    private DbService dbService;

    @Test
    public void TaskControllerGetTasks() throws Exception {
        //GIVEN
        List<Task> taskList = new ArrayList<>();
        when(dbService.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDto(any())).thenReturn(new TaskDTO(1L,"test title", "test content"));

        //WHEN and THEN
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

   @Test
    public void TaskControllerGetTask() throws Exception {
        //GIVEN
        Optional<Task> returnedTask= Optional.ofNullable(new Task("test title","test content"));
        when(dbService.getTask(any())).thenReturn(returnedTask);
        when(taskMapper.mapToTaskDto(any())).thenReturn(new TaskDTO(1L,"test title", "test content"));

        //WHEN and THEN
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/tasks/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("test title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("test content")));
    }

    @Test
    public void TaskControllerDeleteTask() throws Exception{
        //GIVEN
        //WHEN and THEN
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/v1/tasks/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
    @Test
    public void TaskControllerCreateTask() throws Exception{
        //GIVEN
        when(taskMapper.mapToTask(any())).thenReturn(new Task("test title", "test content"));
        TaskDTO taskDto = new TaskDTO(1L,"test title","test content");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //WHEN and THEN
        mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }
    @Test
    public void TaskControllerUpdateTask() throws Exception{
        //GIVEN
        when(taskMapper.mapToTask(any())).thenReturn(new Task("zmieniony", "zmieniony"));
        when(taskMapper.mapToTaskDto(any())).thenReturn(new TaskDTO(2L,"zmieniony", "zmieniony"));
        Task task = new Task(2L,"zmieniony", "zmieniony");
        when(dbService.saveTask(any())).thenReturn(task);
        Gson gson = new Gson();
        TaskDTO taskDto = new TaskDTO(2L,"zmieniony","zmieniony");
        String jsonContent = gson.toJson(taskDto);
        //WHEN and THEN
        mockMvc.perform(MockMvcRequestBuilders
                .put("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("zmieniony")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("zmieniony")));

    }
}
