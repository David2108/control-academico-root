package com.web.app.controlacademico.personnel.service;

import com.web.app.controlacademico.personnel.dto.InstructorRequest;
import com.web.app.controlacademico.personnel.dto.InstructorResponse;
import com.web.app.controlacademico.personnel.entity.InstructorEntity;
import com.web.app.controlacademico.personnel.exception.InstructorExistsException;
import com.web.app.controlacademico.personnel.mapper.IInstructorMapper;
import com.web.app.controlacademico.personnel.repository.IInstructorRepository;
import com.web.app.controlacademico.shared.exception.InvalidMappingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements IInstructorService {

    private final IInstructorRepository instructorRepository;
    private final IInstructorMapper instructorMapper;

    @Override
    public List<InstructorResponse> getList() {
        return this.instructorRepository.findAll()
                .stream()
                .map(instructorMapper::toDtoResponse)
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public InstructorResponse getById(Long id) {
        return null;
    }

    @Transactional
    @Override
    public InstructorResponse save(InstructorRequest dto) {
        try{
            if(instructorRepository.existsByName(dto.getName())){
                throw new InstructorExistsException("Ya existe un curso con ese nombre: " + dto.getName());
            }
            InstructorEntity entity = instructorMapper.toEntity(dto);
            InstructorEntity responseEntity = instructorRepository.save(entity);
            return instructorMapper.toDtoResponse(responseEntity);
        } catch (IllegalArgumentException ex){
            throw new InvalidMappingException("No se pudo mapear el objeto");
        }
    }

    @Override
    public InstructorResponse update(InstructorRequest dto) {
        return null;
    }

}
