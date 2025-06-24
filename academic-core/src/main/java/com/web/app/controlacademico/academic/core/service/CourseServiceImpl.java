package com.web.app.controlacademico.academic.core.service;

import com.web.app.controlacademico.course.dto.CourseRequest;
import com.web.app.controlacademico.course.dto.CourseResponse;
import com.web.app.controlacademico.course.entity.CourseEntity;
import com.web.app.controlacademico.course.exception.CourseExistsException;
import com.web.app.controlacademico.shared.exception.InvalidMappingException;
import com.web.app.controlacademico.course.mapper.ICourseMapper;
import com.web.app.controlacademico.course.repository.ICourseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements ICourseService {

    private final ICourseRepository courseRepository;
    private final ICourseMapper courseMapper;

    @Override
    public List<CourseResponse> getList() {
        return this.courseRepository.findAll()
                .stream()
                .map(courseMapper::toDtoResponse)
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public CourseResponse getById(Long id) {
        return null;
    }

    @Transactional
    @Override
    public CourseResponse save(CourseRequest dto) {
        try{
            if(courseRepository.existsByName(dto.getName())){
                throw new CourseExistsException("Ya existe un curso con ese nombre: " + dto.getName());
            }
            CourseEntity entity = courseMapper.toEntity(dto);
            CourseEntity responseEntity = courseRepository.save(entity);
            return courseMapper.toDtoResponse(responseEntity);
        } catch (IllegalArgumentException ex){
            throw new InvalidMappingException("No se pudo mapear el objeto");
        }
    }

    @Override
    public CourseResponse update(CourseRequest dto) {
        return null;
    }

}
