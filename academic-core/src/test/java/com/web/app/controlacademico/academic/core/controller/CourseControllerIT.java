package com.web.app.controlacademico.academic.core.controller;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ControlAcademicoApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class CourseControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ICourseRepository courseRepository;

    @BeforeEach
    void setup(){
        courseRepository.deleteAll();
    }

    @Test
    void shouldCreateCourseSuccessfully() throws Exception {
        String request = """
                {
                    "code":"MATH101",
                    "name":"Matematicas Basicas"
                }
                """;

        mockMvc.perform(post("/api/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value("MATH101"));
    }

}
