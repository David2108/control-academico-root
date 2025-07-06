package com.web.app.controlacademico.academic.core.service;

import com.web.app.controlacademico.academic.core.dto.SubjectRequest;
import com.web.app.controlacademico.academic.core.dto.SubjectUpdateRequest;
import com.web.app.controlacademico.academic.core.entity.SubjectEntity;

import java.util.List;

public interface ISubjectService {
    List<SubjectEntity> findAll();
    SubjectEntity findById(Long id);
    SubjectEntity save(SubjectRequest request);
    SubjectEntity update(SubjectUpdateRequest request, Long id);
    void deleteById(Long id);
    void saveAll(List<SubjectEntity> entityLst);
}
