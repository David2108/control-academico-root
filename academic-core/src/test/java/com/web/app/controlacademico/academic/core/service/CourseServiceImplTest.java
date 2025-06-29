package com.web.app.controlacademico.academic.core.service;

import com.web.app.controlacademico.academic.core.dto.CourseRequest;
import com.web.app.controlacademico.academic.core.dto.CourseResumeResponse;
import com.web.app.controlacademico.academic.core.entity.CourseEntity;
import com.web.app.controlacademico.academic.core.mapper.ICourseMapper;
import com.web.app.controlacademico.academic.core.repository.ICourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseServiceImplTest {


    @Mock
    ICourseRepository courseRepository;
    @Mock
    ICourseMapper courseMapper;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    void validateIfSaveCourse(){

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
        when(courseMapper.toDtoResponse(newEntityDB))
                .thenReturn(response);

        CourseResumeResponse courseResponse = courseService.save(request);
        assertNotNull(courseResponse);
        assertEquals(response, courseResponse);

    }


}
