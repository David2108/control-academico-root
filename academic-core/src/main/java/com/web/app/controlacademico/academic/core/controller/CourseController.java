package com.web.app.controlacademico.academic.core.controller;

import com.web.app.controlacademico.academic.core.dto.CourseRequest;
import com.web.app.controlacademico.academic.core.dto.CourseResumeResponse;
import com.web.app.controlacademico.academic.core.dto.CourseUpdateRequest;
import com.web.app.controlacademico.academic.core.entity.CourseEntity;
import com.web.app.controlacademico.academic.core.mapper.ICourseMapper;
import com.web.app.controlacademico.academic.core.service.ICourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final ICourseService courseService;
    private final ICourseMapper courseMapper;

    @GetMapping
    public ResponseEntity<List<CourseResumeResponse>> getAll(){
        return ResponseEntity.ok(courseService.getList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResumeResponse> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok()
                .body(courseMapper.toDtoResponse(this.courseService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<CourseResumeResponse> create(@Valid @RequestBody CourseRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(courseMapper.toDtoResponse(this.courseService.save(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResumeResponse> update(@Valid @RequestBody CourseUpdateRequest request, @PathVariable("id") Long id){
        return ResponseEntity.ok()
                .body(courseMapper.toDtoResponse(this.courseService.update(request, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.courseService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
