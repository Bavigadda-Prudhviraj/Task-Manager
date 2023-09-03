package com.prudhvi.taskmanager.dto;

import com.prudhvi.taskmanager.entity.NoteEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class TaskResponseDTO {
    private  int id;
    private  String title;
    private  String description;
    private Date deadline;
    private  boolean completed;
    private List<NoteEntity> notes;

}
