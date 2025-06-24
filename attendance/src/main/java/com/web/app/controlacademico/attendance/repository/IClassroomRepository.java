package com.web.app.controlacademico.attendance.repository;

import com.web.app.controlacademico.attendance.entity.ClassroomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClassroomRepository extends JpaRepository<ClassroomEntity, Long> {
}
