package com.web.app.controlacademico.attendance.reactive.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

@Component
public class AttendanceHandler {
    public ServerResponse list(ServerRequest serverRequest) {
        return ServerResponse.ok().build();
    }

    public ServerResponse register(ServerRequest serverRequest) {
        return ServerResponse.ok().build();
    }
}
