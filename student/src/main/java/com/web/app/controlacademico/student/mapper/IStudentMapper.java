package com.web.app.controlacademico.student.mapper;

import com.web.app.controlacademico.student.dto.StudentRequest;
import com.web.app.controlacademico.student.dto.StudentResponse;
import com.web.app.controlacademico.student.entity.StudentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IStudentMapper {
    StudentRequest toDtoRequest(StudentEntity entity);
    StudentResponse toDtoResponse(StudentEntity entity);
    StudentEntity toEntity(StudentRequest dto);
}
