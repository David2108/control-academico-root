package com.web.app.controlacademico.academic.core.repository;

import com.web.app.controlacademico.academic.core.entity.CourseEntity;
import com.web.app.controlacademico.app.ControlAcademicoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ContextConfiguration(classes = ControlAcademicoApplication.class)
@EnableJpaAuditing
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CourseRepositoryTest {

    @Autowired
    private ICourseRepository courseRepository;

    @Test
    void shouldCreateCourseSuccessfully() {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCode("MATH101");
        courseEntity.setName("Matematicas Basicas");

        CourseEntity response = courseRepository.save(courseEntity);

        assertNotNull(response);
        assertNotNull(response.getId());
    }

}
