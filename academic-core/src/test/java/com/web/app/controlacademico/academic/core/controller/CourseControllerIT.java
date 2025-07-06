package com.web.app.controlacademico.academic.core.controller;

import com.web.app.controlacademico.academic.core.dto.CourseUpdateRequest;
import com.web.app.controlacademico.academic.core.entity.CourseEntity;
import com.web.app.controlacademico.academic.core.enums.StatusCourseEnum;
import com.web.app.controlacademico.academic.core.repository.ICourseRepository;
import com.web.app.controlacademico.app.ControlAcademicoApplication;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ControlAcademicoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class CourseControllerIT {

    @Autowired
    private ICourseRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup(){
        repository.deleteAll();
    }

    @Test
    void shouldGetAllCoursesWithData() throws Exception {

        CourseEntity entity = CourseEntity.builder()
                .code("BIO101")
                .name("Aula 101")
                .status(StatusCourseEnum.ACTIVE)
                .build();

        repository.save(entity);

        mockMvc.perform(get("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].code").value("BIO101"));

    }

    @Test
    void shouldGetEmptyListCourses() throws Exception {

        mockMvc.perform(get("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());

    }

    @Test
    void shouldCreateCoursesSuccessfully() throws Exception {

        String request = """
                {
                    "code":"BIO101",
                    "name":"Aula 101"
                }
                """;

        mockMvc.perform(post("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value("BIO101"));

    }

    @Test
    void shouldCreateCourseWithInvalidData() throws Exception {

        String request = """
                {
                    "code":"BIO101"
                }
                """;

        mockMvc.perform(post("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldCreateCourseWithInvalidDataInDto() throws Exception {

        String request = """
                {
                    "code":"BIO101",
                    "name":"Aula 101$"
                }
                """;

        mockMvc.perform(post("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldGetCourseById() throws Exception {

        CourseEntity entity = CourseEntity.builder()
                .code("BIO101")
                .name("Aula 101")
                .status(StatusCourseEnum.ACTIVE)
                .build();

        CourseEntity response = repository.save(entity);

        mockMvc.perform(get("/api/courses/{id}", response.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("BIO101"));

    }

    @Test
    void shouldUpdateCourseSuccessfully() throws Exception {

        String request = """
                {
                    "code":"BIO101",
                    "name":"Aula 101",
                    "period":"2025-2026",
                    "status":"ACTIVE",
                    "seats":5
                }
                """;

        CourseEntity entity = CourseEntity.builder()
                .code("BIO101")
                .name("Aula 101")
                .status(StatusCourseEnum.ACTIVE)
                .build();

        CourseEntity response = repository.save(entity);

        mockMvc.perform(put("/api/courses/{id}", response.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.period").value("2025-2026"));

    }

    @Test
    void shouldDeleteCourseById() throws Exception {

        CourseEntity entity = CourseEntity.builder()
                .code("BIO101")
                .name("Aula 101")
                .status(StatusCourseEnum.ACTIVE)
                .build();

        CourseEntity response = repository.save(entity);

        mockMvc.perform(delete("/api/courses/{id}", response.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        assertFalse(repository.existsById(response.getId()));

    }

    @Test
    void shouldValidateFieldNullWhenCreate() throws Exception {

        String request = """
                {
                    "code":"",
                    "name":null,
                    "period":"",
                    "status":"ACTIVE",
                    "seats":-5
                }
                """;

            mockMvc.perform(post("/api/courses")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(request))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.message").exists());

    }

    @Test
    void shouldValidateFieldNullWhenUpdate() throws Exception {

        String request = """
                {
                    "code":"",
                    "name":null,
                    "period":"",
                    "status":"ACTIVE",
                    "seats":-5
                }
                """;

        mockMvc.perform(put("/api/courses/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists());

    }

    @Test
    void shouldValidateCourseNotExistsWhenUpdate() throws Exception {

        String request = """
                {
                    "code":"MAT101",
                    "name":"Aula 101",
                    "period":"2025-2026",
                    "status":"ACTIVE",
                    "seats":50
                }
                """;

        mockMvc.perform(put("/api/courses/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isNotFound());

    }

    @Test
    void shouldDeleteCourseByIdWhenCourseNotExists() throws Exception {

        mockMvc.perform(delete("/api/courses/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    void shouldGetCourseByIdWhenCourseNotExists() throws Exception {

        mockMvc.perform(get("/api/courses/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

}
