package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
//Kontroler to ta część aplikacji , która otrzymuje z zewnątrz żądania i daje odpowiedzi.
@RestController
@RequestMapping("/v1/task")
public class TaskController {
    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDTO> getTasks() {
        return new ArrayList<>();
    }
    @RequestMapping(method = RequestMethod.GET, value = "getTask")
    public TaskDTO getTask(Long taskId) {
        return new TaskDTO(1L, "test title", "test_content");
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")
    public void deleteTask(Long taskId) {

    }
    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")
    public TaskDTO updateTask(TaskDTO taskDto) {
        return new TaskDTO(1L, "Edited test title", "Test content");
    }
    @RequestMapping(method = RequestMethod.POST, value = "createTask")
    public void createTask(TaskDTO taskDto) {

    }
}
