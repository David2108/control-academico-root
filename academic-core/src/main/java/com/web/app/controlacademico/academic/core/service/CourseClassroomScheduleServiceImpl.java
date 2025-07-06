package com.web.app.controlacademico.academic.core.service;

import com.web.app.controlacademico.academic.core.entity.ClassroomEntity;
import com.web.app.controlacademico.academic.core.entity.CourseClassroomScheduleEntity;
import com.web.app.controlacademico.academic.core.entity.CourseEntity;
import com.web.app.controlacademico.academic.core.repository.ICourseClassroomScheduleRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseClassroomScheduleServiceImpl implements ICourseClassroomScheduleService{

    private final ICourseService courseService;
    private final IClassroomService classroomService;
    private final ICourseClassroomScheduleRepository repository;

    @Transactional
    @Validated
    @Override
    public void addClassroomToCourse(@NotNull CourseEntity course,
                                     @NotNull ClassroomEntity classroom) {
        CourseClassroomScheduleEntity entity = new CourseClassroomScheduleEntity();
        entity.setCourse(course);
        entity.setClassroom(classroom);
        repository.save(entity);
    }

    @Transactional
    @Validated
    @Override
    public void addClassroomsToCourse(@NotNull CourseEntity course,
                                      @NotEmpty List<ClassroomEntity> classroom) {
        List<CourseClassroomScheduleEntity> entityLst = classroom.stream()
                .filter(classroomData -> !repository.existsByCourseIdAndClassroomId(course.getId(), classroomData.getId()))
                .map(classroomData -> CourseClassroomScheduleEntity.builder()
                        .course(course)
                        .classroom(classroomData)
                        .build())
                .toList();
        repository.saveAll(entityLst);
    }

    @Transactional
    @Validated
    @Override
    public void addClassroomToCourse(@NotNull Long courseId,
                                     @NotNull Long classroomId) {
        CourseEntity course = courseService.getById(courseId);
        ClassroomEntity classroom = classroomService.getById(classroomId);
        CourseClassroomScheduleEntity entity = new CourseClassroomScheduleEntity();
        entity.setCourse(course);
        entity.setClassroom(classroom);
        repository.save(entity);
    }

    @Transactional
    @Validated
    @Override
    public void removeClassroomFromCourse(@NotNull Long courseId,
                                          @NotNull Long classroomId) {
        repository.deleteByCourseIdAndClassroomId(courseId, classroomId);
    }

}
