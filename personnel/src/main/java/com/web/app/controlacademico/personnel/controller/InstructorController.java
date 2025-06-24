package com.web.app.controlacademico.personnel.controller;

import com.web.app.controlacademico.personnel.dto.InstructorRequest;
import com.web.app.controlacademico.personnel.dto.InstructorResponse;
import com.web.app.controlacademico.personnel.service.IInstructorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/instructor")
@RequiredArgsConstructor
public class InstructorController {

    private final IInstructorService instructorService;

    @GetMapping()
    public ResponseEntity<List<InstructorResponse>> getAll(){
        return ResponseEntity.ok(instructorService.getList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok().build();
    }

    @PostMapping()
    public ResponseEntity<InstructorResponse> create(@Valid @RequestBody InstructorRequest request){
        return Optional.ofNullable(instructorService.save(request))
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody InstructorRequest request, Long id){
        return ResponseEntity.ok().build();
    }

}
