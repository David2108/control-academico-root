package com.web.app.controlacademico.academic.core.service;

import com.web.app.controlacademico.course.dto.CourseRequest;
import com.web.app.controlacademico.course.dto.CourseResponse;

import java.util.List;

public interface ICourseService {
    List<CourseResponse> getList();
    CourseResponse getById(Long id);
    CourseResponse save(CourseRequest dto);
    CourseResponse update(CourseRequest dto);
}
