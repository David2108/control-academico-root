package com.web.app.controlacademico.academic.core.mapper;

import com.web.app.controlacademico.academic.core.dto.CourseRequest;
import com.web.app.controlacademico.academic.core.dto.CourseEndPointResponse;
import com.web.app.controlacademico.academic.core.dto.CourseResumeResponse;
import com.web.app.controlacademico.academic.core.entity.CourseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICourseMapper {
    CourseRequest toDtoRequest(CourseEntity entity);
    CourseResumeResponse toDtoResponse(CourseEntity entity);
    CourseEntity toEntity(CourseRequest dto);
}
