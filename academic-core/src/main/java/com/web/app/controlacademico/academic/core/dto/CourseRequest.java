package com.web.app.controlacademico.academic.core.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRequest implements Serializable {
    @NotBlank
    @Size(min = 3, max = 25, message = "El codigo del curso debe tener entre 3 y 25 caracteres")
    @Pattern(regexp = "^[A-Z0-9]+$", message = "El código debe contener solo letras mayúsculas y números sin espacios")
    private String code;
    @NotBlank
    @Size(min = 3, max = 100, message = "El nombre del curso debe tener entre 3 y 100 caracteres")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúñÑüÜ\\s\\-\\.]{3,100}$", message = "El nombre contiene caracteres inválidos")
    private String name;
}
