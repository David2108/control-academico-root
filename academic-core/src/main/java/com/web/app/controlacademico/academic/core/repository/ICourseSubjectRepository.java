package com.web.app.controlacademico.academic.core.repository;

import com.web.app.controlacademico.academic.core.entity.CourseSubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICourseSubjectRepository extends JpaRepository<CourseSubjectEntity, Long> {
    List<CourseSubjectEntity> findByCourseId(Long courseId);
    void deleteByCourseIdAndSubjectId(Long courseId, Long subjectId);
    boolean existsByCourseIdAndSubjectId(Long id, Long id1);
}
