package com.web.app.controlacademico.personnel.entity;

import com.web.app.controlacademico.shared.core.Auditable;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "instructors")
public class InstructorEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public InstructorEntity(String name) {
        this.name = name;
    }
}
