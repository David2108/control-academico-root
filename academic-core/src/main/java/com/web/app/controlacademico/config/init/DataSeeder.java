package com.web.app.controlacademico.config.init;

import com.web.app.controlacademico.course.entity.CourseEntity;
import com.web.app.controlacademico.course.repository.ICourseRepository;
import com.web.app.controlacademico.personnel.entity.InstructorEntity;
import com.web.app.controlacademico.personnel.repository.IInstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final ICourseRepository courseRepository;
    private final IInstructorRepository instructorRepository;

    @Profile("dev")
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

        List<InstructorEntity> instructorEntityLst = List.of(
                new InstructorEntity("Laura Mendoza"),
                new InstructorEntity("Carlos Ramírez"),
                new InstructorEntity("Ana Torres"),
                new InstructorEntity("Javier Herrera"),
                new InstructorEntity("Paola Muñoz"),
                new InstructorEntity("David Salazar"),
                new InstructorEntity("Lucía Paredes"),
                new InstructorEntity("Marco Castillo"),
                new InstructorEntity("Verónica León"),
                new InstructorEntity("Andrés Rivas")
        );

//        courseRepository.saveAll(courseEntityLst);
//        instructorRepository.saveAll(instructorEntityLst);

    }

}
