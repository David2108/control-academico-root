package com.web.app.controlacademico.academic.core.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class CourseManagementRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    CourseRequest course;
    @NotNull
    List<ClassroomRequest> classroomLst;
    @NotNull
    List<SubjectRequest> subjectLst;
}
