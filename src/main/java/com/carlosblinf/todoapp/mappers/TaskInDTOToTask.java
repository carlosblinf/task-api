package com.carlosblinf.todoapp.mappers;

import com.carlosblinf.todoapp.dto.TaskInDto;
import com.carlosblinf.todoapp.entities.Task;
import com.carlosblinf.todoapp.entities.TaskStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskInDTOToTask implements IMapper<TaskInDto, Task>{
    @Override
    public Task map(TaskInDto in) {
        Task task = new Task();
        task.setTitle(in.getTitle());
        task.setDescription(in.getDescription());
        task.setEta(in.getEta());
        task.setLate(false);
        task.setCreated_at(LocalDateTime.now());
        task.setCompleted_at(null);
        task.setTaskStatus(TaskStatus.CREATED);
        return task;
    }
}
