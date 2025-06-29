package com.web.app.controlacademico.academic.core.service;

import com.web.app.controlacademico.academic.core.dto.CourseRequest;
import com.web.app.controlacademico.academic.core.dto.CourseResumeResponse;
import com.web.app.controlacademico.academic.core.entity.CourseEntity;
import com.web.app.controlacademico.academic.core.exception.CourseExistsException;
import com.web.app.controlacademico.academic.core.mapper.ICourseMapper;
import com.web.app.controlacademico.academic.core.repository.ICourseRepository;
import com.web.app.controlacademico.shared.exception.InvalidIdException;
import com.web.app.controlacademico.shared.exception.InvalidMappingException;
import com.web.app.controlacademico.shared.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    @Override
    public CourseResumeResponse getById(Long id) {
        if(Objects.isNull(id) ||  id.compareTo(0L) <= 0){
            throw new InvalidIdException();
        }
        return this.courseRepository.findById(id)
                .map(courseMapper::toDtoResponse)
                .orElseThrow(() -> new NotFoundException("No se encontro el curso con el id:" + id + " en la base de datos al momento de consultarlo"));
    }

    @Transactional
    @Override
    public CourseResumeResponse save(CourseRequest dto) {
        try{
            if(courseRepository.existsByCode(dto.getCode())){
                throw new CourseExistsException("Ya existe un curso con ese codigo: " + dto.getCode());
            }
            //todo falta la validacion cuando se quiere crear el curso y asignar a una aula o grupo de aulas
            CourseEntity entity = courseMapper.toEntity(dto);
            CourseEntity responseEntity = courseRepository.save(entity);
            return courseMapper.toDtoResponse(responseEntity);
        } catch (IllegalArgumentException ex){
            throw new InvalidMappingException("No se pudo mapear el objeto");
        }
    }

    @Override
    public CourseResumeResponse update(CourseRequest dto, Long id) {
        try{
            if(Objects.isNull(id) || id.compareTo(0L) <= 0){
                throw new InvalidIdException();
            }
            Optional<CourseEntity> courseDB = courseRepository.findById(id);
            if(courseDB.isEmpty()){
                throw new NotFoundException("No se encontro el curso con el id:" + id + " en la base de datos al momento de actualizar el curso");
            }else{
                courseDB.get().setName(dto.getName());
                //todo verificar horarios y actualizar si existen
               CourseEntity responseEntity = courseRepository.save(courseDB.get());
               return courseMapper.toDtoResponse(responseEntity);
            }
        }catch (IllegalArgumentException ex){
            throw new InvalidMappingException("No se pudo mapear el objeto");
        }
    }

    @Override
    public void deleteById(Long id) {
        if(Objects.isNull(id) || id.compareTo(0L) <= 0){
            throw new InvalidIdException();
        }
        if(courseRepository.existsById(id)){
            this.courseRepository.deleteById(id);
        }else{
            throw new NotFoundException("No se encontro el curso con el id:" + id + " en la base de datos al momento de eliminar el curso");
        }
    }

}
