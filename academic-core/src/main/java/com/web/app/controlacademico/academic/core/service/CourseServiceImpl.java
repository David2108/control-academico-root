package com.web.app.controlacademico.academic.core.service;

import com.web.app.controlacademico.academic.core.dto.CourseRequest;
import com.web.app.controlacademico.academic.core.dto.CourseResumeResponse;
import com.web.app.controlacademico.academic.core.dto.CourseUpdateRequest;
import com.web.app.controlacademico.academic.core.entity.CourseEntity;
import com.web.app.controlacademico.academic.core.enums.StatusCourseEnum;
import com.web.app.controlacademico.academic.core.exception.CourseExistsException;
import com.web.app.controlacademico.academic.core.mapper.ICourseMapper;
import com.web.app.controlacademico.academic.core.repository.ICourseRepository;
import com.web.app.controlacademico.shared.exception.InvalidIdException;
import com.web.app.controlacademico.shared.exception.InvalidMappingException;
import com.web.app.controlacademico.shared.exception.NotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements ICourseService {

    private final ICourseRepository courseRepository;
    private final ICourseMapper courseMapper;

    @Override
    public List<CourseResumeResponse> getList() {
        return this.courseRepository.findAll()
                .stream()
                .map(courseMapper::toDtoResponse)
                .filter(Objects::nonNull)
                .toList();
    }

    @Transactional(readOnly = true)
    @Validated
    @Override
    public CourseEntity getById(@NotNull @Positive Long id) {
        return this.courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontro el curso con el id:" + id + " en la base de datos al momento de consultarlo"));
    }

    @Transactional(readOnly = true)
    @Validated
    @Override
    public CourseEntity save(@Valid CourseRequest dto) {
        try {
            if (courseRepository.existsByCode(dto.getCode())) {
                throw new CourseExistsException("Ya existe un curso con el codigo:" + dto.getCode() + " en la base de datos");
            }
            CourseEntity entity = courseMapper.toEntity(dto);
            entity.setStatus(StatusCourseEnum.ACTIVE);
            return courseRepository.save(entity);
        } catch (IllegalArgumentException ex) {
            throw new InvalidMappingException("No se pudo mapear el objeto");
        }
    }

    @Transactional
    @Validated
    @Override
    public CourseEntity update(@Valid CourseUpdateRequest dto,
                               @NotNull @Positive Long id) {
        Optional<CourseEntity> courseDB = courseRepository.findById(id);
        if (courseDB.isEmpty()) {
            throw new NotFoundException("No se encontro el curso con el id:" + id + " en la base de datos al momento de actualizar el curso");
        } else {
            courseDB.get().setName(dto.getName());
            courseDB.get().setPeriod(dto.getPeriod());
            courseDB.get().setStatus(dto.getStatus());
            courseDB.get().setSeats(dto.getSeats());
            return courseRepository.save(courseDB.get());
        }
    }

    @Transactional
    @Validated
    @Override
    public void deleteById(@NotNull @Positive Long id) {
        if (!this.courseRepository.existsById(id)) {
            throw new NotFoundException("No se encontró el curso con el id:" + id + " en la base de datos al momento de eliminarlo");
        }
        this.courseRepository.deleteById(id);
    }

}
