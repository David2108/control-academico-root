# [PENDIENTE] Conflicto de versiones Spring Boot/Springdoc en monolito modular

## Descripción del problema

- **Error:**

  ```
  Handler dispatch failed: java.lang.NoSuchMethodError: 'void org.springframework.web.method.ControllerAdviceBean.<init>(java.lang.Object)'
  ```

  al intentar acceder a `/swagger-ui/index.html` o `/v3/api-docs`.

- **Contexto:**

  - Proyecto en modo monolito multi-módulo con varios submódulos (`app`, `core`, `shared`).
  - Se intenta exponer documentación OpenAPI usando Springdoc.
  - El objetivo a futuro es dividir en microservicios.

## Acciones realizadas

- Revisión y unificación de versiones de Spring Boot y BOM.
- Eliminación de dependencias directas a `spring-web`, uso de solo starters.
- Limpieza completa de caché y recompilación.
- Uso de `compileOnly` para dependencias en módulos utilitarios.
- Ejecución de árbol de dependencias (`./gradlew app:dependencies`) para buscar conflictos.
- Implementación de bloque `resolutionStrategy` para forzar versión 6.x.
- Pruebas en diferentes módulos y perfiles.

## Resultado

- El error persiste, lo que indica un conflicto de versiones (Spring 5.x mezclado con 6.x) o una dependencia transitiva incompatible.
- Decisión de **pausar el troubleshooting** en el monolito para no afectar el avance del aprendizaje.

## Próximos pasos

- Documentar el problema y dejarlo pendiente hasta después de la migración a microservicios.
- Avanzar en la migración y probar Springdoc en proyectos de microservicios independientes, donde la configuración será limpia y modular.
- Revisar si el problema persiste post-migración; en caso afirmativo, analizar dependencias de ese microservicio específico.

[⬅️Regresar](../errors.md)