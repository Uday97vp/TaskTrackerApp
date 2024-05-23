package com.uday.taskTracker.services;

import com.uday.taskTracker.dto.TaskDto;

import java.util.List;

public interface TaskServices {

    // create
    TaskDto createTask(TaskDto taskDto);

    // update
    TaskDto updateTask(TaskDto taskDto, String taskId);

    // delete
    void deleteTask(String taskId);

    // get all tasks
    List<TaskDto> getAllTasks();

    // get single tasks by taskId
    TaskDto getSingleTask(String taskId);

    // search tasks
//    List<TaskDto> searchUser(String keyword);

    // other tasks related features

}
