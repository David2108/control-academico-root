package com.web.app.controlacademico.academic.core.mapper;

import com.web.app.controlacademico.academic.core.dto.SubjectRequest;
import com.web.app.controlacademico.academic.core.dto.SubjectResumeResponse;
import com.web.app.controlacademico.academic.core.entity.SubjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ISubjectMapper {
    @Mapping(target = "id", ignore = true)
    SubjectEntity toEntity(SubjectRequest request);
    SubjectResumeResponse toSubjectResumeResponse(SubjectEntity entity);
}
