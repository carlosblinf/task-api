package com.carlosblinf.todoapp.controllers;

import com.carlosblinf.todoapp.dto.TaskInDto;
import com.carlosblinf.todoapp.entities.Task;
import com.carlosblinf.todoapp.entities.TaskStatus;
import com.carlosblinf.todoapp.services.TaskService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> index(){
        return this.taskService.index();
    }

    @PostMapping
    public Task create(@RequestBody TaskInDto taskInDTO){
        return this.taskService.save(taskInDTO);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable("id") Long id, @RequestBody TaskInDto taskInDTO){
        return this.taskService.update(id, taskInDTO);
    }

    @GetMapping("/{id}")
    public Task get(@PathVariable("id") Long id){
        return this.taskService.findById(id);
    }

    @DeleteMapping("/{id}")
    public Task delete(@PathVariable("id") Long id){
        return this.taskService.delete(id);
    }

    @GetMapping("/status/{status}")
    public List<Task> getAllByStatus(@PathVariable("status") TaskStatus status){
        return this.taskService.findAllByStatus(status);
    }

    @PatchMapping("/status/{id}")
    public Task changeStatus(@PathVariable("id") Long id, @RequestParam("status") TaskStatus status){
        return this.taskService.changeStatus(id, status);
    }

}
