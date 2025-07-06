package com.web.app.controlacademico.academic.core.service;

import com.web.app.controlacademico.academic.core.dto.CourseRequest;
import com.web.app.controlacademico.academic.core.dto.CourseResumeResponse;
import com.web.app.controlacademico.academic.core.entity.CourseEntity;
import com.web.app.controlacademico.academic.core.repository.ICourseRepository;
import com.web.app.controlacademico.app.ControlAcademicoApplication;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = com.web.app.controlacademico.app.ControlAcademicoApplication.class)
@ActiveProfiles("test")
@Transactional
public class CourseServiceIT {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private ICourseRepository courseRepository;

    @Test
    void shouldCreateCourseAndFindIt(){
        CourseRequest request = CourseRequest.builder()
                .code("BIO101")
                .name("Biologia Basica")
                .build();

        CourseEntity response = courseService.save(request);

        assertNotNull(response);
        assertEquals(request.getName(), response.getName());
    }
}
