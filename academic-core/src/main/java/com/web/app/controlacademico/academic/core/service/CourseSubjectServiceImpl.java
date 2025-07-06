package com.web.app.controlacademico.academic.core.service;

import com.web.app.controlacademico.academic.core.entity.CourseClassroomScheduleEntity;
import com.web.app.controlacademico.academic.core.entity.CourseEntity;
import com.web.app.controlacademico.academic.core.entity.CourseSubjectEntity;
import com.web.app.controlacademico.academic.core.entity.SubjectEntity;
import com.web.app.controlacademico.academic.core.repository.ICourseSubjectRepository;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseSubjectServiceImpl implements ICourseSubjectService {

    private final ICourseService courseService;
    private final ISubjectService subjectService;
    private final ICourseSubjectRepository repository;

    @Transactional(readOnly = true)
    @Validated
    @Override
    public List<CourseSubjectEntity> getSubjectsByCourse(@NotNull @Positive Long courseId) {
        return repository.findByCourseId(courseId);
    }

    @Transactional
    @Validated
    @Override
    public void assignSubjectToCourse(@NotNull @Positive Long courseId,
                                      @NotNull @Positive Long subjectId) {
        CourseEntity courseDB = courseService.getById(courseId);
        SubjectEntity subjectDB = subjectService.findById(subjectId);
        CourseSubjectEntity entity = CourseSubjectEntity.builder()
                .course(courseDB)
                .subject(subjectDB)
                .build();
        repository.save(entity);
    }

    @Transactional
    @Validated
    @Override
    public void assignSubjectToCourse(@NotNull CourseEntity course,
                                      @NotNull SubjectEntity subject) {
        CourseSubjectEntity entity = CourseSubjectEntity.builder()
                .course(course)
                .subject(subject)
                .build();
        repository.save(entity);
    }

    @Transactional
    @Validated
    @Override
    public void removeSubjectFromCourse(@NotNull @Positive Long courseId,
                                        @NotNull @Positive Long subjectId) {
        repository.deleteByCourseIdAndSubjectId(courseId, subjectId);
    }

    @Transactional
    @Validated
    @Override
    public void addSubjectsToCourse(@NotNull CourseEntity newCourse,
                                    @NotEmpty List<SubjectEntity> subjectLst) {
        List<CourseSubjectEntity> entityLst = subjectLst.stream()
                .filter(subjectData -> !repository.existsByCourseIdAndSubjectId(newCourse.getId(), subjectData.getId()))
                .map(subjectData -> CourseSubjectEntity.builder()
                        .course(newCourse)
                        .subject(subjectData)
                        .build())
                .toList();
        repository.saveAll(entityLst);
    }

}
