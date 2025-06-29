package com.web.app.controlacademico;

import com.web.app.controlacademico.app.ControlAcademicoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = ControlAcademicoApplication.class)
@ActiveProfiles("test")
class ControlAcademicoApplicationTests {

    @Test
    void contextLoads() {
    }

}
