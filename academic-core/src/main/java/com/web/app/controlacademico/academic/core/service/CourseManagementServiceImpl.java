package com.web.app.controlacademico.academic.core.service;

import com.web.app.controlacademico.academic.core.dto.*;
import com.web.app.controlacademico.academic.core.entity.ClassroomEntity;
import com.web.app.controlacademico.academic.core.entity.CourseEntity;
import com.web.app.controlacademico.academic.core.entity.SubjectEntity;
import com.web.app.controlacademico.academic.core.mapper.IClassroomMapper;
import com.web.app.controlacademico.academic.core.mapper.ICourseMapper;
import com.web.app.controlacademico.academic.core.mapper.ISubjectMapper;
import com.web.app.controlacademico.shared.exception.BusinessRuleException;
import com.web.app.controlacademico.shared.exception.InvalidIdException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CourseManagementServiceImpl implements ICourseManagementService {

    private final IClassroomService classroomService;
    private final ICourseService courseService;
    private final ISubjectService subjectService;
    private final ICourseClassroomScheduleService courseClassroomScheduleService;
    private final ICourseSubjectService courseSubjectService;

    private final IClassroomMapper classroomMapper;
    private final ICourseMapper courseMapper;
    private final ISubjectMapper subjectMapper;

    @Transactional
    @Override
    public CourseManagementResponse createCourseByClassroomExist(CourseRequest request) {
        if (Objects.isNull(request.getClassroomId())) {
            throw new InvalidIdException("Se debe enviar el id de la aula");
        }
        ClassroomEntity classroom = classroomService.getById(request.getClassroomId());
        CourseEntity newCourse = courseService.save(request);
        courseClassroomScheduleService.addClassroomToCourse(newCourse, classroom);
        return CourseManagementResponse.builder()
                .course(courseMapper.toDtoResponse(newCourse))
                .classroomLst(Collections.singletonList(classroomMapper.toDtoResponse(classroom)))
                .build();
    }

    @Transactional
    @Override
    public CourseManagementResponse createCourseFull(CourseManagementRequest request) {

        List<ClassroomEntity> classroomLst = new ArrayList<>();
        List<SubjectEntity> subjectLst = new ArrayList<>();

        CourseEntity newCourse = courseService.save(request.getCourse());

        if (CollectionUtils.isNotEmpty(request.getClassroomLst())) {
            classroomLst.addAll(createClassrooms(request.getClassroomLst()));
            courseClassroomScheduleService.addClassroomsToCourse(newCourse, classroomLst);
        }

        if (CollectionUtils.isNotEmpty(request.getSubjectLst())) {
            subjectLst.addAll(createSubjects(request.getSubjectLst()));
            courseSubjectService.addSubjectsToCourse(newCourse, subjectLst);
        }

        return CourseManagementResponse.builder()
                .course(courseMapper.toDtoResponse(newCourse))
                .classroomLst(classroomLst.stream().map(classroomMapper::toDtoResponse).toList())
                .subjectLst(subjectLst.stream().map(subjectMapper::toSubjectResumeResponse).toList())
                .build();
    }

    private List<SubjectEntity> createSubjects(List<SubjectRequest> subjectLst) {
        List<SubjectEntity> entityLst = subjectLst.stream().map(subjectMapper::toEntity).toList();
        subjectService.saveAll(entityLst);
        return entityLst;
    }

    private List<ClassroomEntity> createClassrooms(List<ClassroomRequest> classroomLst) {
        List<ClassroomEntity> entityLst = classroomLst.stream().map(classroomMapper::toEntity).toList();
        classroomService.saveAll(entityLst);
        return entityLst;
    }

}
