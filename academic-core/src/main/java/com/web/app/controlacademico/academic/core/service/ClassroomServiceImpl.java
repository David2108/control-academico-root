package com.web.app.controlacademico.academic.core.service;

import com.web.app.controlacademico.academic.core.dto.ClassroomRequest;
import com.web.app.controlacademico.academic.core.dto.ClassroomResumeResponse;
import com.web.app.controlacademico.academic.core.entity.ClassroomEntity;
import com.web.app.controlacademico.academic.core.enums.StatusClassroomEnum;
import com.web.app.controlacademico.academic.core.mapper.IClassroomMapper;
import com.web.app.controlacademico.academic.core.repository.IClassroomRepository;
import com.web.app.controlacademico.shared.exception.NotFoundException;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ClassroomServiceImpl implements IClassroomService {

    private final IClassroomRepository repository;
    private final IClassroomMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public boolean existsByCode(String code) {
        return false;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ClassroomResumeResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDtoResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public ClassroomEntity getById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontro la aula con el id:" + id + " en la base de datos al momento de consultarla"));
    }

    @Transactional
    @Override
    public ClassroomEntity save(ClassroomRequest request) {
        ClassroomEntity entity = mapper.toEntity(request);
        entity.setStatus(StatusClassroomEnum.AVAILABLE);
        return this.repository.save(entity);
    }

    @Transactional
    @Override
    public ClassroomEntity update(ClassroomRequest request, Long id) {
        if(Objects.isNull(id) || id.compareTo(0L) <= 0) {
            throw new IllegalArgumentException("El id de la aula no puede ser null o cero");
        }

        ClassroomEntity entityDB = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontro la aula con el id:" + id + " en la base de datos al momento de actualizarla"));

        entityDB.setName(request.getName());
        entityDB.setCapacity(request.getCapacity());
        entityDB.setLocation(request.getLocation());
        entityDB.setType(request.getType());
        entityDB.setStatus(request.getStatus());

        return repository.save(entityDB);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        if(Objects.isNull(id) || id.compareTo(0L) <= 0) {
            throw new IllegalArgumentException("El id de la aula no puede ser null o cero");
        }
        repository.deleteById(id);
    }

    @Transactional
    @Validated
    @Override
    public void saveAll(@NotEmpty List<ClassroomEntity> entityLst) {
        repository.saveAll(entityLst);
    }
}
