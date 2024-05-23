package com.uday.taskTracker.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "task_table")
public class Task {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private String taskId;

    @Column(name = "TaskTitle")
    private String title;

    @Column(name = "content")
    private String content;

    private Date addedDate;

    private Date toDoDate;

    private String status;

    }
