package com.web.app.controlacademico.attendance.entity;

import com.web.app.controlacademico.shared.core.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "classrooms")
public class ClassroomEntity extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate date;

    @Column(name = "course_id")
    private Long courseId;

}
