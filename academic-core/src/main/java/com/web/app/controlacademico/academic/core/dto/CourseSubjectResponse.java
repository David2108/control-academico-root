package com.web.app.controlacademico.academic.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseSubjectResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
