package com.web.app.controlacademico.academic.core.service;

import com.web.app.controlacademico.academic.core.entity.CourseEntity;
import com.web.app.controlacademico.academic.core.entity.CourseSubjectEntity;
import com.web.app.controlacademico.academic.core.entity.SubjectEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

public interface ICourseSubjectService {
    List<CourseSubjectEntity> getSubjectsByCourse(Long courseId);
    void assignSubjectToCourse(Long courseId, Long subjectId);
    void assignSubjectToCourse(CourseEntity course, SubjectEntity subject);
    void removeSubjectFromCourse(Long courseId, Long subjectId);
    void addSubjectsToCourse(CourseEntity newCourse, List<SubjectEntity> subjectLst);
}
