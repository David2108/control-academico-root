# Guía CI/CD

## Workflow en GitHub Actions
Un **workflow** es un conjunto de acciones automatizadas que se ejecutan en GitHub cada vez que ocurre un evento (por ejemplo: push, pull request).
Los workflows se definen en archivos YAML dentro de la carpeta `.github/workflows` del repositorio.

## Pasos clave para configurar el workflow
### Disparador (Trigger):
Define cuando se ejecuta el workflow, por ejemplo:
- `on: push`
- `on: pull_request`

### Checkout del código:
El runner descarga el código fuente
```yaml 
- uses: actions/checkout@v4
```

### Configuración del JDK, Node.js o Docker:
Instala el entorno necesario para construir el proyecto
```yaml
- uses: actions/setup-java@v4
  with:
    distribution: 'temurin'
    java-version: 21
```

### Instalación de dependencias:
Descargar todas las librerias necesarias, como:
- `./gradlew dependencies`
- `npm install`

### Ejecución de tests:
Corre los tests automáticos.
```yaml
- name: Run tests
  run: ./gradlew test
```

### Generación de artefactos:
Empaqueta el `.jar/.war` u otros artefactos para publicarlos o almacenarlos.

### Publicación de artefactos:
Sube el resultado a GitHub o a un servidor externo.

### Despliegue (opcional):
Si está configurado hace el deploy automático.

---

## Revisar los logs en GitHub Actions
Cada vez que se ejecuta un workflow, se puede ver los logs directamente en la interfaz web de GitHub:
- Ve al repositorio en GitHub
- Haz clic en la pestaña de Actions
- Verás un listado de workflows recientes
- Haz clic sobre la ejecución que quieras revisar
- Se abrirá el resumen de la ejecución:
  - A la izquierda verás los `jobs`.
  - Haz clic en un `job` para verlos `steps`.
  - Haz clic en cada `step` para ver el log detallado de ese paso
- Si hubo errores, estarán marcados en rojo, y podrás copiar el texto completo para analizarlo o pedir ayuda.

### Consejos prácticos
- Si un paso falla, revisa la traza (`stacktrace`) directamente en el log de ese paso.
- Puedes agregar los logs personalizados en tus scripts con `echo` para `debugging`.
- Puedes descargar los artefactos generados (si el workflow los publica) desde la misma interfaz.

---

[⬅️Regresar](index.md)