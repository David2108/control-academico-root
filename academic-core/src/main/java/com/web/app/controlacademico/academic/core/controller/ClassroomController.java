package com.web.app.controlacademico.academic.core.controller;

import com.web.app.controlacademico.academic.core.dto.ClassroomRequest;
import com.web.app.controlacademico.academic.core.dto.ClassroomResumeResponse;
import com.web.app.controlacademico.academic.core.mapper.IClassroomMapper;
import com.web.app.controlacademico.academic.core.service.IClassroomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Classroom", description = "Endpoints de la entidad Classroom")
@RestController
@RequestMapping("/api/classrooms")
@RequiredArgsConstructor
public class ClassroomController {

    private final IClassroomService service;
    private final IClassroomMapper mapper;

    @GetMapping
    public ResponseEntity<List<ClassroomResumeResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomResumeResponse> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(mapper.toDtoResponse(service.getById(id)));
    }

    @Operation(
            summary = "Crear un curso (ESTABLE)",
            description = "Crea un nuevo curso. Contrato estable."
    )
    @PostMapping
    public ResponseEntity<ClassroomResumeResponse> create(@Valid @RequestBody ClassroomRequest request){
        ClassroomResumeResponse response = mapper.toDtoResponse(service.save(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassroomResumeResponse> update(@PathVariable("id") Long id,
                                                          @Valid @RequestBody ClassroomRequest request) {
        return ResponseEntity.ok(mapper.toDtoResponse(service.update(request, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
