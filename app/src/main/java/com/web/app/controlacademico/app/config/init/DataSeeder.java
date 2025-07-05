package com.web.app.controlacademico.app.config.init;

import com.web.app.controlacademico.academic.core.entity.CourseEntity;
import com.web.app.controlacademico.academic.core.repository.ICourseRepository;
import com.web.app.controlacademico.personnel.repository.IInstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final ICourseRepository courseRepository;
    private final IInstructorRepository instructorRepository;

    @Override
    public void run(String... args) {

        List<CourseEntity> courseEntityLst = List.of(
                CourseEntity.builder().id(1L).code("C1").name("Fundamentos de Redes y Comunicaciones").build(),
                CourseEntity.builder().id(2L).code("C2").name("Introducción a la Ciberseguridad").build(),
                CourseEntity.builder().id(3L).code("C3").name("Programación Orientada a Objetos con Java").build(),
                CourseEntity.builder().id(4L).code("C4").name("Desarrollo Web Full Stack con Spring Boot y Angular").build(),
                CourseEntity.builder().id(5L).code("C5").name("Administración de Bases de Datos con PostgreSQL y MySQL").build(),
                CourseEntity.builder().id(6L).code("C6").name("DevOps y Automatización con Docker y Jenkins").build(),
                CourseEntity.builder().id(7L).code("C7").name("Arquitectura de Microservicios con Spring Cloud").build(),
                CourseEntity.builder().id(8L).code("C8").name("Inteligencia Artificial y Machine Learning con Python").build(),
                CourseEntity.builder().id(9L).code("C9").name("Gestión de Proyectos de TI con Metodologías Ágiles (Scrum y Kanban)").build(),
                CourseEntity.builder().id(10L).code("C10").name("Análisis de Datos y Visualización con Power BI").build()
        );

        //courseRepository.saveAll(courseEntityLst);

    }

}
