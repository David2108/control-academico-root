package com.web.app.controlacademico.academic.core.mapper;

import com.web.app.controlacademico.academic.core.dto.CourseRequest;
import com.web.app.controlacademico.academic.core.dto.CourseEndPointResponse;
import com.web.app.controlacademico.academic.core.dto.CourseResumeResponse;
import com.web.app.controlacademico.academic.core.entity.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ICourseMapper {
    @Mapping(target = "classroomId", ignore = true)
    CourseRequest toDtoRequest(CourseEntity entity);
    @Mapping(target = "classroomLst", ignore = true)
    CourseResumeResponse toDtoResponse(CourseEntity entity);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "period", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "seats", ignore = true)
    CourseEntity toEntity(CourseRequest dto);
}
