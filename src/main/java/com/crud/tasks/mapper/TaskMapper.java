package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDTO;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskMapper {
    public Task mapToTask(final TaskDTO taskDto) {
        return new Task(
                taskDto.getId(),
                taskDto.getTitle(),
                taskDto.getContent()
        );
    }
    public Task mapToTaskDto(final Task task) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getContent()
        );
    }
    public List<TaskDTO> mapToTaskDtoList(final List<Task> taskList) {
        return taskList.stream()
                .map(this::mapToTaskDto)
                .collect(Collectors.toList());
    }
}
