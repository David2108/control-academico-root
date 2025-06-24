package com.web.app.controlacademico.enrollment.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdmissionDto {
    private Long id;
    private String name;
    private String email;
}
