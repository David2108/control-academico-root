package com.web.app.controlacademico.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditingConfig {

    //todo agregar configuracion para obtener el usuario para los campos de auditoria, antes se debe configurar la seguridad

}
