package com.web.app.controlacademico.academic.core.service;

import com.web.app.controlacademico.academic.core.dto.SubjectRequest;
import com.web.app.controlacademico.academic.core.dto.SubjectUpdateRequest;
import com.web.app.controlacademico.academic.core.entity.SubjectEntity;
import com.web.app.controlacademico.academic.core.exception.SubjectExistsException;
import com.web.app.controlacademico.academic.core.repository.ISubjectRepository;
import com.web.app.controlacademico.academic.core.mapper.ISubjectMapper;
import com.web.app.controlacademico.shared.exception.NotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements ISubjectService {

    private final ISubjectRepository repository;
    private final ISubjectMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public List<SubjectEntity> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Validated
    @Override
    public SubjectEntity findById(@NotNull @Positive Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontro la materia con el id:" + id + " en la base de datos al momento de consultarla"));
    }

    @Transactional
    @Validated
    @Override
    public SubjectEntity save(@Valid SubjectRequest request) {
        if (repository.existsByCode(request.getCode())) {
            throw new SubjectExistsException("Ya existe una materia con el codigo:" + request.getCode() + " en la base de datos");
        }
        SubjectEntity entity = mapper.toEntity(request);
        return repository.save(entity);
    }

    @Transactional
    @Validated
    @Override
    public SubjectEntity update(@Valid SubjectUpdateRequest request,
                                @NotNull @Positive Long id) {
        SubjectEntity subjectEntity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontró la materia con el id:" + id + " en la base de datos al momento de actualizarla"));
        subjectEntity.setName(request.getName());
        subjectEntity.setCredits(request.getCredits());
        if(Objects.nonNull(request.getDescription())){
            subjectEntity.setDescription(request.getDescription());
        }
        return repository.save(subjectEntity);
    }

    @Transactional
    @Validated
    @Override
    public void deleteById(@NotNull @Positive Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("No se encontró la materia con el id:" + id + " en la base de datos al momento de eliminarla");
        }
        repository.deleteById(id);
    }

    @Transactional
    @Validated
    @Override
    public void saveAll(@NotEmpty List<SubjectEntity> entityLst) {
        repository.saveAll(entityLst);
    }

}
