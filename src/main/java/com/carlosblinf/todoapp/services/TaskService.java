package com.carlosblinf.todoapp.services;

import com.carlosblinf.todoapp.dto.TaskInDto;
import com.carlosblinf.todoapp.entities.Task;
import com.carlosblinf.todoapp.entities.TaskStatus;
import com.carlosblinf.todoapp.exceptions.TodoExceptions;
import com.carlosblinf.todoapp.mappers.TaskInDTOToTask;
import com.carlosblinf.todoapp.repositories.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskInDTOToTask mapper;

    public TaskService(TaskRepository taskRepository, TaskInDTOToTask mapper) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    public List<Task> index(){
        return this.taskRepository.findAll();
    }

    public Task save(TaskInDto taskInDto){
        return this.taskRepository.save(mapper.map(taskInDto));
    }

    public Task findById(Long id){
        Optional<Task> optionalTask = this.taskRepository.findById(id);
        if(optionalTask.isEmpty()) {
            throw new TodoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }

        return optionalTask.get();
    }

    public Task update(Long id, TaskInDto taskInDto){
        Task task = this.findById(id);
        task.setTitle(taskInDto.getTitle());
        task.setDescription(taskInDto.getDescription());

        return this.taskRepository.save(task);
    }

    public Task delete(Long id){
        Task task = this.findById(id);
        this.taskRepository.delete(task);

        return task;
    }

    public List<Task> findAllByStatus(TaskStatus status){
        return this.taskRepository.findAllByTaskStatus(status);
    }

    public Task changeStatus(Long id, TaskStatus status){
        Task task = this.findById(id);
        task.setTaskStatus(status);

        if (status.equals(TaskStatus.COMPLETED))
            task.setCompleted_at(LocalDateTime.now());
        else
            task.setCompleted_at(null);

        return this.taskRepository.save(task);
    }

}
