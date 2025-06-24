package com.web.app.controlacademico.enrollment.api;

import com.web.app.controlacademico.student.dto.StudentResponse;
import com.web.app.controlacademico.student.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentQueryServiceImpl implements IStudentQueryService {

    private final IStudentService studentService;

    @Override
    public StudentResponse getStudentByDni(String dni) {
        return studentService.getByDni(dni);
    }
}
