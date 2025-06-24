package com.web.app.controlacademico.attendance.repository;

import com.web.app.controlacademico.attendance.entity.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAttendanceRepository extends JpaRepository<AttendanceEntity, Long> {
}
