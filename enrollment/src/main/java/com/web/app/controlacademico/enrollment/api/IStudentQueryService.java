package com.web.app.controlacademico.enrollment.api;

import com.web.app.controlacademico.student.dto.StudentResponse;

public interface IStudentQueryService {
    StudentResponse getStudentByDni(String dni);
}
