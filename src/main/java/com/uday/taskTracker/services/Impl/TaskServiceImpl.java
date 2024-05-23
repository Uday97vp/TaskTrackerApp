package com.uday.taskTracker.services.Impl;

import com.uday.taskTracker.dto.TaskDto;
import com.uday.taskTracker.entities.Task;
import com.uday.taskTracker.repositories.TaskRepository;
import com.uday.taskTracker.services.TaskServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskServices {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public TaskDto createTask(TaskDto taskDto) {

        // generate unique id in string format 
        String taskId = UUID.randomUUID().toString();
        taskDto.setTaskId(taskId);
        
        // dto to entity
        Task task=dtoToEntity(taskDto);
        Task savedTask = taskRepository.save(task);
        // entity to dto
        TaskDto taskDto1 = entityToDto(savedTask);
        return taskDto1;
        
    }

    @Override
    public TaskDto updateTask(TaskDto taskDto, String taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found with this given id"));
        task.setTitle(taskDto.getTitle());
        task.setContent(taskDto.getContent());
        task.setStatus(taskDto.getStatus());
        task.setAddedDate(taskDto.getAddedDate());
        task.setToDoDate(taskDto.getToDoDate());
        Task updatedTask = taskRepository.save(task);
        TaskDto updatedDto = entityToDto(updatedTask);

        return updatedDto;
    }

    @Override
    public void deleteTask(String taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found with given id"));
        taskRepository.delete(task);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskDto> dtoList = tasks.stream().map(task -> entityToDto(task)).collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public TaskDto getSingleTask(String taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("task not found with given id."));
        return entityToDto(task);
    }

//    @Override
//    public List<TaskDto> searchUser(String keyword) {
//        List<Task> tasks = taskRepository.findByNameContaining(keyword);
//        List<TaskDto> dtoList = tasks.stream().map(task -> entityToDto(task)).collect(Collectors.toList());
//        return dtoList;
//    }

    private TaskDto entityToDto(Task savedTask) {
//        TaskDto taskDto = TaskDto.builder()
//                .taskId(savedTask.getTaskId())
//                .title(savedTask.getTitle())
//                .content(savedTask.getContent())
//                .status(savedTask.getStatus())
//                .addedDate(savedTask.getAddedDate())
//                .toDoDate(savedTask.getToDoDate())
//                .build();
        return mapper.map(savedTask, TaskDto.class);
    }

    private Task dtoToEntity(TaskDto taskDto) {
//        Task task = Task.builder()
//                .taskId(taskDto.getTaskId())
//                .title(taskDto.getTitle())
//                .content(taskDto.getContent())
//                .status(taskDto.getStatus())
//                .addedDate(taskDto.getAddedDate())
//                .toDoDate(taskDto.getToDoDate()).build();
        return mapper.map(taskDto, Task.class);
    }
}
