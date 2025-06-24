package com.web.app.controlacademico.personnel.repository;

import com.web.app.controlacademico.personnel.entity.InstructorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInstructorRepository extends JpaRepository<InstructorEntity, Long> {
    boolean existsByName(String name);
}
