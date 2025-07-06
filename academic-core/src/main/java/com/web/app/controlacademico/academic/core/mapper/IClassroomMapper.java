package com.web.app.controlacademico.academic.core.mapper;

import com.web.app.controlacademico.academic.core.dto.ClassroomRequest;
import com.web.app.controlacademico.academic.core.dto.ClassroomResponse;
import com.web.app.controlacademico.academic.core.dto.ClassroomResumeResponse;
import com.web.app.controlacademico.academic.core.entity.ClassroomEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IClassroomMapper {
    @Mapping(target = "id", ignore = true)
    ClassroomEntity toEntity(ClassroomRequest request);
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "capacity", ignore = true)
    @Mapping(target = "location", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "status", ignore = true)
    ClassroomEntity toEntityResponse(ClassroomResponse response);
    ClassroomResumeResponse toDtoResponse(ClassroomEntity entity);
    ClassroomResponse toDtoService(ClassroomEntity entity);
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "capacity", ignore = true)
    @Mapping(target = "location", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "status", ignore = true)
    ClassroomResumeResponse toDtoResume(ClassroomResponse response);
    ClassroomRequest clone(ClassroomRequest request);
}
