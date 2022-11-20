package com.carlosblinf.todoapp.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
public class TaskInDto {
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    private LocalDateTime eta;
}
