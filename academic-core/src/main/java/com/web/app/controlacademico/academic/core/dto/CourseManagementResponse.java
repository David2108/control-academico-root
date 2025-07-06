package com.web.app.controlacademico.academic.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseManagementResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private CourseResumeResponse course;
    private List<ClassroomResumeResponse> classroomLst;
    private List<SubjectResumeResponse> subjectLst;
}
