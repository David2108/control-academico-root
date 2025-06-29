package com.web.app.controlacademico.academic.core.entity;

import com.web.app.controlacademico.academic.core.enums.WeekDayEnum;
import com.web.app.controlacademico.shared.core.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "course_classroom_schedule",
uniqueConstraints = @UniqueConstraint(columnNames = {"course_id", "classroom_id", "week_day", "start_time", "end_time"})
)
public class CourseClassroomScheduleEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "week_day", nullable = false)
    private WeekDayEnum weekDay;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;
    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity course;

    @ManyToOne
    @JoinColumn(name = "classroom_id", nullable = false)
    private ClassroomEntity classroom;

}
