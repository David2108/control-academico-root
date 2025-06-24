package com.web.app.controlacademico.attendance.controller;

import com.web.app.controlacademico.attendance.dto.AttendanceRequest;
import com.web.app.controlacademico.attendance.service.AttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @GetMapping("/student/{id}")
    public ResponseEntity<?> getHistorialByStudentId(Long id){
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<?> getAttendanceListByDate(@Param("date") LocalDate date){
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAttendanceStatus(@Valid @RequestBody AttendanceRequest attendanceRequest, Long id){
        return ResponseEntity.ok().build();
    }

    @PostMapping()
    public void createAttendance(@Valid @RequestBody AttendanceRequest attendanceRequest){
        attendanceService.registerAttendance(attendanceRequest);
    }

}
