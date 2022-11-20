package com.carlosblinf.todoapp.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    private LocalDateTime created_at;
    private LocalDateTime completed_at;
    private LocalDateTime eta; // estimated terminate date
    private boolean late;
    private TaskStatus taskStatus;
}
