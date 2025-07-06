package com.web.app.controlacademico.academic.core.service;

import com.web.app.controlacademico.academic.core.dto.CourseManagementRequest;
import com.web.app.controlacademico.academic.core.dto.CourseManagementResponse;
import com.web.app.controlacademico.academic.core.dto.CourseRequest;
import org.springframework.transaction.annotation.Transactional;

public interface ICourseManagementService {
    @Transactional
    CourseManagementResponse createCourseByClassroomExist(CourseRequest request);

    @Transactional
    CourseManagementResponse createCourseFull(CourseManagementRequest request);
}
