package com.web.app.controlacademico.attendance.service;

import com.web.app.controlacademico.attendance.dto.AttendanceRequest;
import jakarta.validation.Valid;

public interface IAttendanceService {
    void registerAttendance(AttendanceRequest attendanceRequest);
}
