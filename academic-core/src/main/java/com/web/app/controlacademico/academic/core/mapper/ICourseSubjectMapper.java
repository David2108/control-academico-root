package com.web.app.controlacademico.academic.core.mapper;

import com.web.app.controlacademico.academic.core.dto.CourseSubjectResponse;
import com.web.app.controlacademico.academic.core.entity.CourseSubjectEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICourseSubjectMapper {
    CourseSubjectResponse toDto(CourseSubjectEntity entity);
}
