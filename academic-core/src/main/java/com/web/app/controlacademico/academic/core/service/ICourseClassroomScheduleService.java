package com.web.app.controlacademico.academic.core.service;

import com.web.app.controlacademico.academic.core.entity.ClassroomEntity;
import com.web.app.controlacademico.academic.core.entity.CourseEntity;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;

public interface ICourseClassroomScheduleService {
    void addClassroomToCourse(CourseEntity course, ClassroomEntity classroom);
    void addClassroomsToCourse(CourseEntity course, List<ClassroomEntity> classroom);
    void addClassroomToCourse(Long courseId, Long classroomId);
    void removeClassroomFromCourse(Long courseId, Long classroomId);
}
