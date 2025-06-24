package com.web.app.controlacademico.student.service;

import com.web.app.controlacademico.student.dto.StudentRequest;
import com.web.app.controlacademico.student.dto.StudentResponse;
import com.web.app.controlacademico.student.entity.StudentEntity;
import com.web.app.controlacademico.student.mapper.IStudentMapper;
import com.web.app.controlacademico.student.repository.IStudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements IStudentService {

    private final IStudentRepository studentRepository;
    private final IStudentMapper IStudentMapper;

    @Override
    public List<StudentResponse> getList() {
        return this.studentRepository.findAll()
                .stream()
                .map(IStudentMapper::toDtoResponse)
                .toList();
    }

    @Override
    public StudentResponse getById(Long id) {
        return null;
    }

    @Override
    public void save(StudentRequest dto) {
        StudentEntity entity = IStudentMapper.toEntity(dto);
        studentRepository.save(entity);
    }

    @Override
    public void update(StudentRequest studentRequest) {

    }

    @Override
    public StudentResponse getByDni(String dni) {
        return this.studentRepository.findByDni(dni)
                .map(IStudentMapper::toDtoResponse)
                .orElse(StudentResponse.builder().build());
    }
}
