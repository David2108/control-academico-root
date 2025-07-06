package com.web.app.controlacademico.academic.core.controller;

import com.web.app.controlacademico.academic.core.dto.*;
import com.web.app.controlacademico.academic.core.mapper.ISubjectMapper;
import com.web.app.controlacademico.academic.core.service.ISubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final ISubjectService service;
    private final ISubjectMapper mapper;

    @GetMapping
    public ResponseEntity<List<SubjectResumeResponse>> getAll() {
        return ResponseEntity.ok(service.findAll().stream().map(mapper::toSubjectResumeResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectResumeResponse> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok()
                .body(mapper.toSubjectResumeResponse(service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<SubjectResumeResponse> create(@Valid @RequestBody SubjectRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toSubjectResumeResponse(this.service.save(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectResumeResponse> update(@Valid @RequestBody SubjectUpdateRequest request,
                                                        @PathVariable("id") Long id) {
        return ResponseEntity.ok()
                .body(mapper.toSubjectResumeResponse(this.service.update(request, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.service.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
