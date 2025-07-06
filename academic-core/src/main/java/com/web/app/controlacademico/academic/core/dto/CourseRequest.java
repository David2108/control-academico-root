package com.web.app.controlacademico.academic.core.dto;

import jakarta.validation.constraints.*;
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
public class CourseRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 3, max = 10, message = "El codigo del curso debe tener entre 3 y 10 caracteres")
    @Pattern(regexp = "^[A-Z]{3}[0-9]{3}+$", message = "El código debe contener solo letras mayúsculas y números sin espacios")
    private String code;

    @NotBlank
    @Size(min = 3, max = 100, message = "El nombre del curso debe tener entre 3 y 100 caracteres")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúñÑüÜ0-9\\s\\-\\.]{3,100}$", message = "El nombre contiene caracteres inválidos")
    private String name;
    private Long classroomId;
}
