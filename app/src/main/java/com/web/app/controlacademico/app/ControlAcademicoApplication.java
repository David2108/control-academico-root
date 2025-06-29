package com.web.app.controlacademico.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.web.app.controlacademico")
@EnableJpaRepositories(basePackages = {
        "com.web.app.controlacademico.academic.core.repository",
        "com.web.app.controlacademico.personnel.repository",
        "com.web.app.controlacademico.attendance.repository",
        "com.web.app.controlacademico.enrollment.repository",
        "com.web.app.controlacademico.student.repository"
})
@EntityScan(basePackages = {
        "com.web.app.controlacademico.academic.core.entity",
        "com.web.app.controlacademico.personnel.entity",
        "com.web.app.controlacademico.attendance.entity",
        "com.web.app.controlacademico.enrollment.entity",
        "com.web.app.controlacademico.student.entity"
})
public class ControlAcademicoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControlAcademicoApplication.class, args);
    }

}
