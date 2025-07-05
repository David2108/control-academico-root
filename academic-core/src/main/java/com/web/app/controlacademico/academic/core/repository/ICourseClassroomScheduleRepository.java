package com.web.app.controlacademico.academic.core.repository;

import com.web.app.controlacademico.academic.core.entity.CourseClassroomScheduleEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseClassroomScheduleRepository extends JpaRepository<CourseClassroomScheduleEntity, Long> {
    void deleteByCourseIdAndClassroomId(Long courseId, Long classroomId);
}
