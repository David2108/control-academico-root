package com.web.app.controlacademico.academic.core.controller;

import com.web.app.controlacademico.academic.core.dto.CourseManagementRequest;
import com.web.app.controlacademico.academic.core.dto.CourseManagementResponse;
import com.web.app.controlacademico.academic.core.dto.CourseRequest;
import com.web.app.controlacademico.academic.core.dto.CourseResumeResponse;
import com.web.app.controlacademico.academic.core.service.CourseManagementServiceImpl;
import com.web.app.controlacademico.academic.core.service.ICourseManagementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management/courses")
@RequiredArgsConstructor
public class CourseManagementController {

    private final ICourseManagementService courseManagementService;

    @PostMapping
    public ResponseEntity<CourseManagementResponse> create(@Valid @RequestBody CourseRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(courseManagementService.createCourseByClassroomExist(request));
    }

    @PostMapping("/full")
    public ResponseEntity<CourseResumeResponse> createFull(@Valid @RequestBody CourseManagementRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseManagementService.createCourseFull(request));
    }

}
