package com.web.app.controlacademico.personnel.mapper;

import com.web.app.controlacademico.personnel.dto.InstructorRequest;
import com.web.app.controlacademico.personnel.dto.InstructorResponse;
import com.web.app.controlacademico.personnel.entity.InstructorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IInstructorMapper {
    InstructorRequest toDtoRequest(InstructorEntity entity);
    InstructorResponse toDtoResponse(InstructorEntity entity);
    InstructorEntity toEntity(InstructorRequest dto);
}
