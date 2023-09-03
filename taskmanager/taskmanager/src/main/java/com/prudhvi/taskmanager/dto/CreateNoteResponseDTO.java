package com.prudhvi.taskmanager.dto;

import com.prudhvi.taskmanager.entity.NoteEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CreateNoteResponseDTO {
    private Integer taskId;
    private NoteEntity note;
}
