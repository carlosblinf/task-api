package com.carlosblinf.todoapp.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    private LocalDateTime created_at;
    private LocalDateTime completed_at;
    private LocalDateTime eta; // estimated terminate date
    private boolean late;
    private TaskStatus taskStatus;
}
