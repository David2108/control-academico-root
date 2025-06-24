package com.web.app.controlacademico.student.service;

import com.web.app.controlacademico.student.dto.StudentRequest;
import com.web.app.controlacademico.student.dto.StudentResponse;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    List<StudentResponse> getList();
    StudentResponse getById(Long id);
    void save(StudentRequest studentRequest);
    void update(StudentRequest studentRequest);

    StudentResponse getByDni(String dni);
}
