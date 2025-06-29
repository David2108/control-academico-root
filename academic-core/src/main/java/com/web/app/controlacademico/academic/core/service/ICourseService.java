package com.web.app.controlacademico.academic.core.service;

import com.web.app.controlacademico.academic.core.dto.CourseEndPointResponse;
import com.web.app.controlacademico.academic.core.dto.CourseRequest;
import com.web.app.controlacademico.academic.core.dto.CourseResumeResponse;

import java.util.List;

public interface ICourseService {
    List<CourseResumeResponse> getList();
    CourseResumeResponse getById(Long id);
    CourseResumeResponse save(CourseRequest dto);
    CourseResumeResponse update(CourseRequest dto, Long id);
    void deleteById(Long id);
}
