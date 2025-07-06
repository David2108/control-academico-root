package com.web.app.controlacademico.academic.core.entity;

import com.web.app.controlacademico.academic.core.enums.StatusCourseEnum;
import com.web.app.controlacademico.app.ControlAcademicoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@EnableJpaAuditing
@ContextConfiguration(classes = ControlAcademicoApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CourseEntityTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldPersistCourseEntity(){

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCode("MATH101");
        courseEntity.setName("Matematicas Basicas");
        courseEntity.setStatus(StatusCourseEnum.ACTIVE);

        CourseEntity response = entityManager.persistFlushFind(courseEntity);
        assertNotNull(response.getId());

    }

}
