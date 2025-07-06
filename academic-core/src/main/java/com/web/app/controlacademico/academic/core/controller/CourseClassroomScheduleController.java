package com.web.app.controlacademico.academic.core.controller;

import com.web.app.controlacademico.academic.core.service.ICourseClassroomScheduleService;
import com.web.app.controlacademico.academic.core.service.ICourseSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course-classroom-schedule")
@RequiredArgsConstructor
public class CourseClassroomScheduleController {

    private final ICourseClassroomScheduleService service;

    @PostMapping("/{course-id}/{subject-id}")
    public void addClassroomToCourse(@PathVariable("course-id") Long courseId, @PathVariable("subject-id") Long subjectId){
        service.addClassroomToCourse(courseId, subjectId);
    }

    @DeleteMapping("/{course-id}/{subject-id}")
    public void removeClassroomFromCourse(@PathVariable("course-id") Long courseId, @PathVariable("subject-id") Long subjectId){
        service.removeClassroomFromCourse(courseId, subjectId);
    }

}
