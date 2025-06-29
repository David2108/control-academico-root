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
                new CourseEntity("Fundamentos de Redes y Comunicaciones"),
                new CourseEntity("Introducción a la Ciberseguridad"),
                new CourseEntity("Programación Orientada a Objetos con Java"),
                new CourseEntity("Desarrollo Web Full Stack con Spring Boot y Angular"),
                new CourseEntity("Administración de Bases de Datos con PostgreSQL y MySQL"),
                new CourseEntity("DevOps y Automatización con Docker y Jenkins"),
                new CourseEntity("Arquitectura de Microservicios con Spring Cloud"),
                new CourseEntity("Inteligencia Artificial y Machine Learning con Python"),
                new CourseEntity("Gestión de Proyectos de TI con Metodologías Ágiles (Scrum y Kanban)"),
                new CourseEntity("Análisis de Datos y Visualización con Power BI")
        );

        //courseRepository.saveAll(courseEntityLst);

    }

}
