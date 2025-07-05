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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {


    @Mock
    ICourseRepository courseRepository;
    @Mock
    ICourseMapper courseMapper;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    void shouldGetListWithDataWhenGetListAllCoursesAndExistData(){
        List<CourseEntity> listEntity = List.of(
                CourseEntity.builder()
                        .id(1L)
                        .code("C1")
                        .name("Curso 1")
                        .build(),
                CourseEntity.builder()
                        .id(2L)
                        .code("C2")
                        .name("Curso 2")
                        .build()
        );
        when(courseRepository.findAll()).thenReturn(listEntity);
        when(courseMapper.toDtoResponse(any(CourseEntity.class)))
                .thenAnswer(invocation -> {
                    CourseEntity entity = invocation.getArgument(0);
                    return CourseResumeResponse.builder()
                            .code(entity.getCode())
                            .name(entity.getName())
                            .classroomLst(null)
                            .build();
                });
        List<CourseResumeResponse> responseLst = this.courseService.getList();

        assertNotNull(responseLst);
        assertEquals(2, responseLst.size());
    }

    @Test
    void shouldGetListEmptyWhenGetListAllCoursesAndNotExistData(){

        when(courseRepository.findAll()).thenReturn(List.of());
        List<CourseResumeResponse> responseLst = this.courseService.getList();

        assertEquals(0, responseLst.size());

    }

    @Test
    void shouldGetDataWhenGetCourseByIdIfExistData(){

        Long id = 1L;
        when(courseRepository.findById(id)).thenReturn(Optional.of(new CourseEntity()));
        when(courseMapper.toDtoResponse(any(CourseEntity.class)))
                .thenReturn(CourseResumeResponse.builder()
                        .code("C1")
                        .name("Curso 1")
                        .build());

        CourseResumeResponse response = courseService.getById(id);

        assertNotNull(response);
        assertNotNull(response.getName());

    }

    @Test
    void shouldThrownNotFoundExceptionWhenGetCourseByIdIfNotExistData(){
        Long id = 1L;
        when(courseRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            courseService.getById(id);
        });

        verify(courseRepository).findById(id);
    }

    @Test
    void shouldThrownInvalidExceptionWhenGetCourseByIdIfIdIsInvalid(){
        Long id = 0L;

        assertThrows(NotFoundException.class, () -> {
            courseService.getById(id);
        });
    }

    @Test
    void shouldSaveCourseSuccessfully(){

        CourseRequest request = new CourseRequest();
        request.setCode("123456");
        request.setName("Curso de prueba");

        CourseEntity entityConvert = new CourseEntity();
        entityConvert.setCode(request.getCode());
        entityConvert.setName(request.getName());

        CourseEntity newEntityDB = new CourseEntity();
        newEntityDB.setId(1L);
        newEntityDB.setCode(request.getCode());
        newEntityDB.setName(request.getName());

        CourseResumeResponse response = CourseResumeResponse.builder()
                .code("123456")
                .name("Curso de prueba")
                .build();

        when(courseRepository.existsByCode(request.getCode()))
                .thenReturn(false);
        when(courseMapper.toEntity(request))
                .thenReturn(entityConvert);
        when(courseRepository.save(entityConvert))
                .thenReturn(newEntityDB);

        CourseEntity courseResponse = courseService.save(request);
        assertNotNull(courseResponse);
        assertEquals(response.getCode(), courseResponse.getCode());
        verify(courseRepository, times(1)).save(entityConvert);

    }

    @Test
    void shouldThrownExceptionWhenSaveCourseWithSameCode(){

        CourseRequest request = new CourseRequest();
        request.setCode("123456");
        request.setName("Curso de prueba");

        when(courseRepository.existsByCode(request.getCode()))
                .thenReturn(true);

        assertThrows(CourseExistsException.class, () -> {
            courseService.save(request);
        });

        verify(courseRepository).existsByCode(request.getCode());

    }

    @Test
    void shouldThrownExceptionWhenSaveCourseAndThereIsErrorInCastWithMapper(){

        CourseRequest request = new CourseRequest();
        request.setCode("123456");
        request.setName("Curso de prueba");

        when(courseRepository.existsByCode(request.getCode()))
                .thenReturn(false);
        when(courseMapper.toEntity(request))
                .thenThrow(new InvalidMappingException("No se pudo mapear el objeto"));

        assertThrows(InvalidMappingException.class, () -> {
            courseService.save(request);
        });

        verify(courseRepository).existsByCode(request.getCode());
        verify(courseMapper).toEntity(request);

    }


}
