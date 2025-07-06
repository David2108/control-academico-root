package com.web.app.controlacademico.academic.core.repository;

import com.web.app.controlacademico.academic.core.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseRepository extends JpaRepository<CourseEntity, Long> {
    boolean existsByCode(String name);
}
