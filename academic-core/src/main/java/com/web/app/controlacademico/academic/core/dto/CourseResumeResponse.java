package com.web.app.controlacademico.academic.core.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CourseResumeResponse {
    private String code;
    private String name;
    private String period;
    private List<ClassroomResumeResponse> classroomLst;
}
