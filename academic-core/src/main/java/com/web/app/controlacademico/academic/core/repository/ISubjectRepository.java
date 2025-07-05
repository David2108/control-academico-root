package com.web.app.controlacademico.academic.core.repository;

import com.web.app.controlacademico.academic.core.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISubjectRepository extends JpaRepository<SubjectEntity, Long> {
    boolean existsByCode(String code);
}
