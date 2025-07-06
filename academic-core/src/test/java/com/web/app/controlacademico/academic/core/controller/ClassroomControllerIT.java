package com.web.app.controlacademico.academic.core.controller;

import com.web.app.controlacademico.academic.core.entity.ClassroomEntity;
import com.web.app.controlacademico.academic.core.enums.StatusClassroomEnum;
import com.web.app.controlacademico.academic.core.enums.TypeClassroomEnum;
import com.web.app.controlacademico.academic.core.repository.IClassroomRepository;
import com.web.app.controlacademico.app.ControlAcademicoApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ControlAcademicoApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class ClassroomControllerIT {

    @Autowired
    private IClassroomRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup(){
        repository.deleteAll();
    }

    @Test
    void shouldGetAllClassroomsWithData() throws Exception {

        ClassroomEntity entity = ClassroomEntity.builder()
                .code("BIO101")
                .name("Aula 101")
                .capacity(50)
                .location("Edificion A")
                .type(TypeClassroomEnum.PHYSICAL)
                .status(StatusClassroomEnum.AVAILABLE)
                .build();

        repository.save(entity);

        mockMvc.perform(get("/api/classrooms")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].code").value("BIO101"));

    }

    @Test
    void shouldGetEmptyList() throws Exception {

        mockMvc.perform(get("/api/classrooms")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());

    }

    @Test
    void shouldCreateClassroomSuccessfully() throws Exception {

        String request = """
                {
                    "code":"BIO101",
                    "name":"Aula 101",
                    "capacity":50,
                    "location":"Edificion A",
                    "type":"PHYSICAL"
                }
                """;

        mockMvc.perform(post("/api/classrooms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value("BIO101"));

    }

    @Test
    void shouldCreateClassroomWithInvalidData() throws Exception {

        String request = """
                {
                    "code":"BIO101",
                    "name":"Aula 101",
                    "location":"Edificion A",
                    "type":"PHYSICAL"
                }
                """;

        mockMvc.perform(post("/api/classrooms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldCreateClassroomWithInvalidDataInDto() throws Exception {

        String request = """
                {
                    "code":"BIO101",
                    "name":"Aula 101",
                    "capacity":0,
                    "location":"Edificion A",
                    "type":"PHYSICAL"
                }
                """;

        mockMvc.perform(post("/api/classrooms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldGetClassroomById() throws Exception {

        ClassroomEntity entity = ClassroomEntity.builder()
                .code("BIO101")
                .name("Aula 101")
                .capacity(50)
                .location("Edificion A")
                .type(TypeClassroomEnum.PHYSICAL)
                .status(StatusClassroomEnum.AVAILABLE)
                .build();

        ClassroomEntity response = repository.save(entity);

        mockMvc.perform(get("/api/classrooms/{id}", response.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("BIO101"));

    }

    @Test
    void shouldUpdateClassroomSuccessfully() throws Exception {

        String request = """
                {
                    "code":"BIO101",
                    "name":"Aula 101",
                    "capacity":10,
                    "location":"Edificion A",
                    "type":"PHYSICAL"
                }
                """;

        ClassroomEntity entity = ClassroomEntity.builder()
                .code("BIO101")
                .name("Aula 101")
                .capacity(50)
                .location("Edificion B")
                .type(TypeClassroomEnum.VIRTUAL)
                .status(StatusClassroomEnum.AVAILABLE)
                .build();

        ClassroomEntity response = repository.save(entity);

        mockMvc.perform(put("/api/classrooms/{id}", response.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.location").value("Edificion A"));

    }

    @Test
    void shouldDeleteClassroomById() throws Exception {

        ClassroomEntity entity = ClassroomEntity.builder()
                .code("BIO101")
                .name("Aula 101")
                .capacity(50)
                .location("Edificion B")
                .type(TypeClassroomEnum.VIRTUAL)
                .status(StatusClassroomEnum.AVAILABLE)
                .build();

        ClassroomEntity response = repository.save(entity);

        mockMvc.perform(delete("/api/classrooms/{id}", response.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

    @Test
    void shouldCreateClassroomWhenFieldsAreNull() throws Exception {

        String request = """
                {
                    "code":"",
                    "name":null,
                    "capacity":50,
                    "location":"Edificion A",
                    "type":"PHYSICAL"
                }
                """;

        mockMvc.perform(post("/api/classrooms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists());

    }

    @Test
    void shouldUpdateClassroomWhenFieldsAreNull() throws Exception {

        String request = """
                {
                    "code":"",
                    "name":null,
                    "capacity":10,
                    "location":"Edificion A",
                    "type":"PHYSICAL"
                }
                """;

        mockMvc.perform(put("/api/classrooms/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists());

    }

    @Test
    void shouldUpdateClassroomWhenCourseNotExists() throws Exception {

        String request = """
                {
                    "code":"BIO101",
                    "name":"Aula 101",
                    "capacity":10,
                    "location":"Edificion A",
                    "type":"PHYSICAL"
                }
                """;

        mockMvc.perform(put("/api/classrooms/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isNotFound());

    }

    @Test
    void shouldDeleteClassroomByIdWhenClassroomNotExists() throws Exception {

        mockMvc.perform(delete("/api/classrooms/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    void shouldGetClassroomByIdWhenClassroomNotExists() throws Exception {

        mockMvc.perform(get("/api/classrooms/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }
}