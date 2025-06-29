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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = IntegrationTestConfig.class)
@ActiveProfiles("test")
@Transactional
public class CourseServiceTest {


    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    @DisplayName("Debe crear un curso y luego poder buscarlo por código")
    void shouldCreateAndFindCourse() {
        // Arrange
        CourseRequest request = new CourseRequest("PHY101", "Física General");
        Long id = courseService.createCourse(request);

        // Act
        CourseResponse response = courseService.findByCode("PHY101");

        // Assert
        assertNotNull(response);
        assertEquals("Física General", response.getName());
        assertEquals(id, response.getId());
    }

}
