package com.web.app.controlacademico.academic.core.mapper;

import com.web.app.controlacademico.course.dto.CourseRequest;
import com.web.app.controlacademico.course.dto.CourseResponse;
import com.web.app.controlacademico.course.entity.CourseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICourseMapper {
    CourseRequest toDtoRequest(CourseEntity entity);
    CourseResponse toDtoResponse(CourseEntity entity);
    CourseEntity toEntity(CourseRequest dto);
}
