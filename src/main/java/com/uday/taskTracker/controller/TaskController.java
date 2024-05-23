package com.uday.taskTracker.controller;

import com.uday.taskTracker.dto.ApiResponseMessage;
import com.uday.taskTracker.dto.TaskDto;
import com.uday.taskTracker.services.TaskServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskServices taskService;

    // create
    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        TaskDto taskDto1 = taskService.createTask(taskDto);
        return new ResponseEntity<>(taskDto1,HttpStatus.CREATED);
    }

    // update
    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDto> updateTask(
            @PathVariable("taskId") String taskId,
            @RequestBody TaskDto taskDto)
    {
        TaskDto updatedTaskDto = taskService.updateTask(taskDto, taskId);
        return new ResponseEntity<>(updatedTaskDto, HttpStatus.OK);
    }

    // delete
    @DeleteMapping("/{taskId}")
    public ResponseEntity<ApiResponseMessage> deleteTask(@PathVariable String taskId) {
        taskService.deleteTask(taskId);
        ApiResponseMessage message = ApiResponseMessage.
                builder().
                message("User is deleted successfully").
                success(true).
                status(HttpStatus.OK).
                build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // get all task
    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTask() {
        List<TaskDto> allTasksDto = taskService.getAllTasks();
        return new ResponseEntity<>(allTasksDto, HttpStatus.OK);
    }

    // get single task
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> getSingleTask(@PathVariable String taskId) {
        TaskDto singleTaskDto = taskService.getSingleTask(taskId);
        return new ResponseEntity<>(singleTaskDto, HttpStatus.OK);
    }

//    // search user
//    @GetMapping("/search/{keywords}")
//    public ResponseEntity<List<TaskDto>> searchUser(@PathVariable String keyword) {
//        return new ResponseEntity<>(taskService.searchUser(keyword), HttpStatus.OK);
//    }

}
