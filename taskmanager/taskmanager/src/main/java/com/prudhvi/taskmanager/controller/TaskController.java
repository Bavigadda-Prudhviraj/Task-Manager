package com.prudhvi.taskmanager.controller;

import com.prudhvi.taskmanager.dto.CreateTaskDTO;
import com.prudhvi.taskmanager.dto.ErrorResponseDTO;
import com.prudhvi.taskmanager.dto.TaskResponseDTO;
import com.prudhvi.taskmanager.dto.UpdateTaskDTO;
import com.prudhvi.taskmanager.entity.TaskEntity;
import com.prudhvi.taskmanager.service.NotesService;
import com.prudhvi.taskmanager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private  final TaskService taskService;
    private  final NotesService notesService;
    private ModelMapper modelMapper = new ModelMapper();

    public TaskController(TaskService taskService,NotesService notesService) {
        this.notesService=notesService;
        this.taskService = taskService;
    }

    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> getTasks(){
        var tasks=taskService.getTasks();
        return  ResponseEntity.ok(tasks);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable("id") int id){
        var task=taskService.getTaskById(id);
        var notes=notesService.getNotesForTask(id);
        if(task==null){
            return   ResponseEntity.notFound().build();
        }
        var taskresponse=modelMapper.map(task, TaskResponseDTO.class);
        taskresponse.setNotes(notes);
        return  ResponseEntity.ok(taskresponse);
    }
    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body) throws ParseException {
        var task=taskService.addTask(body.getTitle(),body.getDescription(),body.getDeadline());
        return  ResponseEntity.ok(task);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Integer id, @RequestBody UpdateTaskDTO body)throws ParseException{
        var task=taskService.updateTask(id,body.getDescription(),body.getDeadline(),body.getCompleted());
        if(task==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleErrors(Exception e){
        if(e instanceof ParseException){
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid date format"));
        }
        e.printStackTrace();
        return  ResponseEntity.internalServerError().body(new ErrorResponseDTO("Internal Server Error"));

    }
}
