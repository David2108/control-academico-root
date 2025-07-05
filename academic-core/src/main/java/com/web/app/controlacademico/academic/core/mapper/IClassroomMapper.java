package com.web.app.controlacademico.academic.core.mapper;

import com.web.app.controlacademico.academic.core.dto.ClassroomRequest;
import com.web.app.controlacademico.academic.core.dto.ClassroomResponse;
import com.web.app.controlacademico.academic.core.dto.ClassroomResumeResponse;
import com.web.app.controlacademico.academic.core.entity.ClassroomEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IClassroomMapper {
    ClassroomEntity toEntity(ClassroomRequest request);
    ClassroomEntity toEntityResponse(ClassroomResponse response);
    ClassroomResumeResponse toDtoResponse(ClassroomEntity entity);
    ClassroomResponse toDtoService(ClassroomEntity entity);
    ClassroomResumeResponse toDtoResume(ClassroomResponse response);
    ClassroomRequest clone(ClassroomRequest request);
}
