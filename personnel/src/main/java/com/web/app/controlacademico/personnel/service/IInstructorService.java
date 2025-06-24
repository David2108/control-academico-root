package com.web.app.controlacademico.personnel.service;

import com.web.app.controlacademico.personnel.dto.InstructorRequest;
import com.web.app.controlacademico.personnel.dto.InstructorResponse;

import java.util.List;

public interface IInstructorService {
    List<InstructorResponse> getList();
    InstructorResponse getById(Long id);
    InstructorResponse save(InstructorRequest dto);
    InstructorResponse update(InstructorRequest dto);
}
