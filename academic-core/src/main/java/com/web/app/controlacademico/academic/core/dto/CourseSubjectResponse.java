package com.web.app.controlacademico.academic.core.dto;

import lombok.Builder;
import lombok.Data;
import java.io.Serial;

import java.io.Serializable;

@Data
@Builder
public class CourseSubjectResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
