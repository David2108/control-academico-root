package com.web.app.controlacademico.attendance.mapper;

import com.web.app.controlacademico.attendance.dto.AttendanceRequest;
import com.web.app.controlacademico.attendance.dto.AttendanceResponse;
import com.web.app.controlacademico.attendance.entity.AttendanceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAttendanceMapper {
    AttendanceResponse toAttendanceResponse(AttendanceEntity entity);
    AttendanceEntity toAttendanceEntity(AttendanceRequest request);
}
