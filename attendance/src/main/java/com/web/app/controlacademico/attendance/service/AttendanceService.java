package com.web.app.controlacademico.attendance.service;

import com.web.app.controlacademico.attendance.dto.AttendanceRequest;
import com.web.app.controlacademico.attendance.entity.AttendanceEntity;
import com.web.app.controlacademico.attendance.mapper.IAttendanceMapper;
import com.web.app.controlacademico.attendance.repository.IAttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendanceService implements IAttendanceService{

    private final IAttendanceRepository attendanceRepository;
    private final IAttendanceMapper IAttendanceMapper;

    @Override
    public void registerAttendance(AttendanceRequest attendanceRequest) {

        AttendanceEntity entity = IAttendanceMapper.toAttendanceEntity(attendanceRequest);
        this.attendanceRepository.save(entity);

    }

}
