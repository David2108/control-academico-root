package com.web.app.controlacademico.academic.core.repository;

import com.web.app.controlacademico.course.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseRepository extends JpaRepository<CourseEntity, Long> {
    boolean existsByName(String name);
}
