package com.web.app.controlacademico.student.repository;

import com.web.app.controlacademico.student.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IStudentRepository extends JpaRepository<StudentEntity, Long>, IStudentCustomRepository {
    Optional<StudentEntity> findByDni(String dni);
}
