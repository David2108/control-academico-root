package com.web.app.controlacademico.academic.core.mapper;

import com.web.app.controlacademico.academic.core.dto.SubjectRequest;
import com.web.app.controlacademico.academic.core.dto.SubjectResumeResponse;
import com.web.app.controlacademico.academic.core.entity.SubjectEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISubjectMapper {
    SubjectEntity toEntity(SubjectRequest request);
    SubjectResumeResponse toSubjectResumeResponse(SubjectEntity entity);
}
