package com.web.app.controlacademico.academic.core.service;

import com.web.app.controlacademico.academic.core.dto.CourseEndPointResponse;
import com.web.app.controlacademico.academic.core.dto.CourseRequest;
import com.web.app.controlacademico.academic.core.dto.CourseResumeResponse;
import com.web.app.controlacademico.academic.core.dto.CourseUpdateRequest;
import com.web.app.controlacademico.academic.core.entity.CourseEntity;

import java.util.List;

public interface ICourseService {
    List<CourseResumeResponse> getList();
    CourseEntity getById(Long id);
    CourseEntity save(CourseRequest dto);
    CourseEntity update(CourseUpdateRequest dto, Long id);
    void deleteById(Long id);
}
