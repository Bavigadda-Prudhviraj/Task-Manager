package com.prudhvi.taskmanager.controller;

import com.prudhvi.taskmanager.dto.CreateTaskDTO;
import com.prudhvi.taskmanager.entity.TaskEntity;
import com.prudhvi.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private  final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> getTasks(){
        var tasks=taskService.getTasks();
        return  ResponseEntity.ok(tasks);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable("id") int id){
        var task=taskService.getTaskById(id);
        if(task==null){
            return   ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(task);
    }
    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body){
        var task=taskService.addTask(body.getTitle(),body.getDescription(),body.getDeadline());
        return  ResponseEntity.ok(task);
    }
}
