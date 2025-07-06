package com.web.app.controlacademico.academic.core.controller;

import com.web.app.controlacademico.academic.core.entity.SubjectEntity;
import com.web.app.controlacademico.academic.core.entity.SubjectEntity;
import com.web.app.controlacademico.academic.core.enums.StatusCourseEnum;
import com.web.app.controlacademico.academic.core.mapper.ISubjectMapper;
import com.web.app.controlacademico.academic.core.repository.ICourseRepository;
import com.web.app.controlacademico.academic.core.repository.ISubjectRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ControlAcademicoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class SubjectControllerIT {

    @Autowired
    private ISubjectRepository repository;
    @Autowired
    private ISubjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup(){
        repository.deleteAll();
    }

    @Test
    void shouldGetAllCoursesWithData() throws Exception {

        SubjectEntity entity = SubjectEntity.builder()
                .code("BIO101")
                .name("Aula 101")
                .credits(5)
                .build();

        repository.save(entity);

        mockMvc.perform(get("/api/subjects")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].code").value("BIO101"));

    }

    @Test
    void shouldGetEmptyListCourses() throws Exception {

        mockMvc.perform(get("/api/subjects")
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
                    "name":"Aula 101",
                    "credits":5
                }
                """;

        mockMvc.perform(post("/api/subjects")
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

        mockMvc.perform(post("/api/subjects")
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

        mockMvc.perform(post("/api/subjects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldGetCourseById() throws Exception {

        SubjectEntity entity = SubjectEntity.builder()
                .code("BIO101")
                .name("Aula 101")
                .credits(5)
                .build();

        SubjectEntity response = repository.save(entity);

        mockMvc.perform(get("/api/subjects/{id}", response.getId())
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
                    "credits":5
                }
                """;

        SubjectEntity entity = SubjectEntity.builder()
                .code("BIO101")
                .name("Aula 101")
                .credits(10)
                .build();

        SubjectEntity response = repository.save(entity);

        mockMvc.perform(put("/api/subjects/{id}", response.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.credits").value("5"));

    }

    @Test
    void shouldDeleteCourseById() throws Exception {

        SubjectEntity entity = SubjectEntity.builder()
                .code("BIO101")
                .name("Aula 101")
                .credits(5)
                .build();

        SubjectEntity response = repository.save(entity);

        mockMvc.perform(delete("/api/subjects/{id}", response.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        assertFalse(repository.existsById(response.getId()));

    }

    @Test
    void shouldCreateCoursesWhenFieldsAreNull() throws Exception {

        String request = """
                {
                    "code":"",
                    "name":null,
                    "credits":5
                }
                """;

        mockMvc.perform(post("/api/subjects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest());

    }

    @Test
    void shouldUpdateCourseWhenFieldsAreNull() throws Exception {

        String request = """
                {
                    "code":"",
                    "name":null,
                    "credits":5
                }
                """;

        mockMvc.perform(put("/api/subjects/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest());

    }

    @Test
    void shouldUpdateCourseWhenSubjectDoesNotExist() throws Exception {

        String request = """
                {
                    "code":"BIO101",
                    "name":"Aula 101",
                    "credits":5
                }
                """;

        mockMvc.perform(put("/api/subjects/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isNotFound());

    }

    @Test
    void shouldDeleteCourseByIdWhenSubjectNotExists() throws Exception {

        mockMvc.perform(delete("/api/subjects/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    void shouldGetCourseByIdWhenSubjectNotExists() throws Exception {

        mockMvc.perform(get("/api/subjects/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

}
