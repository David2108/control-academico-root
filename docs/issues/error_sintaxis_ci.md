# Error al escribir un paso en una tarea de GitHub Actions

## Descripción del problema
Al digitar el paso para descargar el código en la tarea **build**, se muestra el siguiente error:
```yaml
    - name: Checkout code
      uses: actions/checkout@v41
```

El siguiente error se muestra en la consola de GitHub Actions:
``` shell
Error: Unable to resolve action actions/checkout@v41, package version not found
```

## Solución
El proble ocurrio al escribir `v41` en vez de `v4`. En la sección de la tarea **build** se debe escribir `v4` en vez de `v41`.

[⬅️Regresar](../errors.md)