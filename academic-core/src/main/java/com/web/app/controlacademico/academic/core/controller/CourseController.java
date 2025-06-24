package com.web.app.controlacademico.academic.core.controller;

import com.web.app.controlacademico.course.dto.CourseRequest;
import com.web.app.controlacademico.course.dto.CourseResponse;
import com.web.app.controlacademico.course.service.ICourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseController {

    private final ICourseService courseService;

    @GetMapping()
    public ResponseEntity<List<CourseResponse>> getAll(){
        return ResponseEntity.ok(courseService.getList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok().build();
    }

    @PostMapping()
    public ResponseEntity<CourseResponse> create(@Valid @RequestBody CourseRequest request){
        return Optional.ofNullable(courseService.save(request))
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody CourseRequest request, Long id){
        return ResponseEntity.ok().build();
    }

}
