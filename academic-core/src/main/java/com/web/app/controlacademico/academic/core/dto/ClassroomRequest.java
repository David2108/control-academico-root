package com.web.app.controlacademico.academic.core.dto;

import com.web.app.controlacademico.academic.core.enums.StatusClassroomEnum;
import com.web.app.controlacademico.academic.core.enums.TypeClassroomEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class ClassroomRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotEmpty
    private String code;

    @NotEmpty
    private String name;

    @NotNull
    @Min(value = 1)
    private Integer capacity;

    @NotEmpty
    private String location;

    @NotNull
    private TypeClassroomEnum type;

    private StatusClassroomEnum status;
}
