package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestDbService {
    @Autowired
    private TaskRepository taskRepository;
    @Test
    public void TestDbServiceGetAllTasks(){
        //GIVEN
        DbService dbService = new DbService(taskRepository);
        Task task = new Task("name test","tekst");
        //WHEN
        dbService.saveTask(task);
        long id = task.getId();
        List<Task> taskList=  dbService.getAllTasks();
        //THEN
        assertTrue(taskList.size()>0);
        //CLEAN
        dbService.deleteTask(id);
    }
    @Test
    public void TestDbServiceGetTask(){
        //GIVEN
        DbService dbService = new DbService(taskRepository);
        Task task = new Task("name test","tekst");
        //WHEN
        dbService.saveTask(task);
        long id = task.getId();
        Optional<Task> observedTask = dbService.getTask(id);
        //THEN
        assertTrue(observedTask.isPresent());
        assertEquals("name test",observedTask.get().getTitle());
        assertEquals("tekst",observedTask.get().getContent());
        //CLEAN
        dbService.deleteTask(id);
    }
    @Test
    public void TestDbServiceDeleteTask() {
        //GIVEN
        DbService dbService = new DbService(taskRepository);
        Task task = new Task("name test", "tekst");
        //WHEN
        dbService.saveTask(task);
        long id = task.getId();
        Optional<Task> observedTask = dbService.getTask(id);
        //THEN
        assertTrue(observedTask.isPresent());
        dbService.deleteTask(id);
        observedTask = dbService.getTask(id);
        assertFalse(observedTask.isPresent());
    }
}
