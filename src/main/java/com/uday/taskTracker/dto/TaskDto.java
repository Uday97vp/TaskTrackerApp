package com.uday.taskTracker.dto;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class    TaskDto {
    private String taskId;

    private String title;

    private String content;

    private Date addedDate;

    private Date toDoDate;

    private String status;
}
