package com.carlosblinf.todoapp.controllers;

import com.carlosblinf.todoapp.ApiResponse;
import com.carlosblinf.todoapp.dto.TaskInDto;
import com.carlosblinf.todoapp.entities.Task;
import com.carlosblinf.todoapp.entities.TaskStatus;
import com.carlosblinf.todoapp.services.TaskService;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final ApiResponse response;

    public TaskController(TaskService taskService, ApiResponse response) {
        this.taskService = taskService;
        this.response = response;
    }

    @GetMapping
    public ResponseEntity<List<Task>> index(){
        return (ResponseEntity<List<Task>>) this.response
                .okResponse("Tasks list",this.taskService.index());
    }

    @PostMapping
    public ResponseEntity<Task> store(@Valid @RequestBody TaskInDto taskInDTO, BindingResult result){
        if (result.hasErrors()){
            return (ResponseEntity<Task>) this.response
                    .errorResponse(result, HttpStatus.BAD_REQUEST);
        }

        return (ResponseEntity<Task>) this.response
                .createResponse("Task created successfully",this.taskService.save(taskInDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable("id") Long id, @RequestBody TaskInDto taskInDTO){
        return (ResponseEntity<Task>) this.response
                .okResponse("Task updated successfully",this.taskService.update(id, taskInDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> show(@PathVariable("id") Long id){
        return (ResponseEntity<Task>) this.response
                .okResponse("Task resource",this.taskService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> delete(@PathVariable("id") Long id){
        return (ResponseEntity<Task>) this.response
                .okResponse("Task deleted successfully",this.taskService.delete(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Task>> getAllByStatus(@PathVariable("status") TaskStatus status){
        return (ResponseEntity<List<Task>>) this.response
                .okResponse("Status tasks list",this.taskService.findAllByStatus(status));
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity<Task> changeStatus(@PathVariable("id") Long id, @RequestParam("status") TaskStatus status){
        return (ResponseEntity<Task>) this.response
                .okResponse("Status task updated successfully",this.taskService.changeStatus(id, status));
    }

}
