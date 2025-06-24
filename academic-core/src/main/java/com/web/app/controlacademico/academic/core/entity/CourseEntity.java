package com.web.app.controlacademico.academic.core.entity;

import com.web.app.controlacademico.shared.core.Auditable;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class CourseEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public CourseEntity(String name) {
        this.name = name;
    }
}
