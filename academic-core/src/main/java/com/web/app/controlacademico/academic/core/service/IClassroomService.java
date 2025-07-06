package com.web.app.controlacademico.academic.core.service;

import com.web.app.controlacademico.academic.core.dto.ClassroomRequest;
import com.web.app.controlacademico.academic.core.dto.ClassroomResumeResponse;
import com.web.app.controlacademico.academic.core.entity.ClassroomEntity;

import java.util.List;

public interface IClassroomService {
    boolean existsByCode(String code);
    ClassroomEntity getById(Long id);
    ClassroomEntity save(ClassroomRequest request);
    List<ClassroomResumeResponse> findAll();
    ClassroomEntity update(ClassroomRequest request, Long id);
    void deleteById(Long id);
    void saveAll(List<ClassroomEntity> entityLst);
}
