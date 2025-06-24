package com.web.app.controlacademico.attendance.reactive.router;

import com.web.app.controlacademico.attendance.reactive.handler.AttendanceHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class AttendanceRouterFunction {

    @Bean
    public RouterFunction<ServerResponse> attendanceRoutes(AttendanceHandler handler) {
        return RouterFunctions.route()
                .GET("/api/v2/attendances", handler::list)
                .POST("/api/v2/attendances", handler::register)
                .build();
    }

}
