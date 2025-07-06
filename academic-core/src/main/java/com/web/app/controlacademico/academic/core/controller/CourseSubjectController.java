package com.web.app.controlacademico.academic.core.controller;

import com.web.app.controlacademico.academic.core.mapper.ICourseSubjectMapper;
import com.web.app.controlacademico.academic.core.service.ICourseSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course-subject")
@RequiredArgsConstructor
public class CourseSubjectController {

    private final ICourseSubjectService service;

    @PostMapping("/{course-id}/{subject-id}")
    public void assignSubjectToCourse(@PathVariable("course-id") Long courseId, @PathVariable("subject-id") Long subjectId){
        service.assignSubjectToCourse(courseId, subjectId);
    }

    @DeleteMapping("/{course-id}/{subject-id}")
    public void removeSubjectFromCourse(@PathVariable("course-id") Long courseId, @PathVariable("subject-id") Long subjectId){
        service.removeSubjectFromCourse(courseId, subjectId);
    }

}
