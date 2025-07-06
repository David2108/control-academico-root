package com.web.app.controlacademico.academic.core.repository;

import com.web.app.controlacademico.academic.core.entity.ClassroomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClassroomRepository extends JpaRepository<ClassroomEntity, Long> {
}
