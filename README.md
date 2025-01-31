# TallerWebServer

Este proyecto es un servidor web básico escrito en Java que soporta múltiples solicitudes seguidas no concurrentes. El servidor es capaz de servir archivos estáticos como páginas HTML, archivos JavaScript, CSS e imágenes. Además, incluye un endpoint para la comunicación asíncrona con servicios REST en el backend.

## Instalación

Para instalar y ejecutar este proyecto, sigue los siguientes pasos:

1. **Clona el repositorio:**:
   ```bash
   git clone https://github.com/Samuelfdm/TallerWebServer.git
   cd TallerWebServer

2. **Compila y empaqueta el proyecto:**
Asegúrate de tener Maven instalado y ejecuta:
    ```bash
    mvn clean package

3. **Ejecuta el servidor:**
Después de compililar y empaquetar el proyecto, ejecuta el servidor con:
    ```bash
    java -cp target/classes edu.escuelaing.app.HttpServer
   
    o tambien:
   
    java -cp target/TallerWebServer-1.0-SNAPSHOT.jar edu.escuelaing.app.HttpServer

Ejecución
---------

Una vez que el servidor esté en funcionamiento, puedes acceder a los recursos estáticos a través de tu navegador web. Por ejemplo:

*   **Página principal**: http://localhost:35000/prueba.html

*   **Archivo JavaScript**: http://localhost:35000/javascript.js

*   **Archivo CSS**: http://localhost:35000/style.css

*   **Imágenes PNG**: http://localhost:35000/images/imagen1.png

*   **Imágenes JPG**: http://localhost:35000/images/imagen2.jpg

El servidor escucha en el puerto 35000 por defecto.

Arquitectura
------------

El diseño del servidor sigue una arquitectura modular con las siguientes clases principales:

*   **HttpServer**: Maneja el servidor y acepta conexiones entrantes.

*   **RequestHandler**: Procesa las solicitudes HTTP y delega la respuesta adecuada.

*   **StaticFileHandler**: Se encarga de servir archivos estáticos desde el disco local.

*   **ResponseHelper**: Maneja la construcción de respuestas HTTP.

*   **HelloService**: Implementa la lógica para el endpoint /app/hello.


### Diagrama de Flujo

1.  El cliente realiza una solicitud HTTP al servidor.

2.  HttpServer acepta la conexión y pasa la solicitud a RequestHandler.

3.  RequestHandler determina si la solicitud es para un archivo estático o para el endpoint /app/hello.

4.  Si es un archivo estático, StaticFileHandler lee el archivo del disco y ResponseHelper construye la respuesta.

5.  Si es una solicitud al endpoint /app/hello, HelloService procesa la solicitud y genera una respuesta.


Pruebas
-------

Se han realizado pruebas unitarias para asegurar el correcto funcionamiento de cada componente. Las pruebas incluyen:

*   **Pruebas de HttpServer**: Verifican que el servidor pueda iniciar y aceptar conexiones.

*   **Pruebas de RequestHandler**: Aseguran que las solicitudes HTTP sean procesadas correctamente.

*   **Pruebas de StaticFileHandler**: Comprueban que los archivos estáticos sean servidos adecuadamente.

*   **Pruebas de ResponseHelper**: Validan que las respuestas HTTP sean construidas correctamente.

*   **Pruebas de HelloService**: Verifican que el endpoint /app/hello funcione como se espera.

Para ejecutar las pruebas, utiliza el siguiente comando:

    mvn test

Contribuciones
--------------

Si deseas contribuir a este proyecto, por favor sigue los siguientes pasos:

1.  Haz un fork del repositorio.

2.  Crea una nueva rama (git checkout -b feature/nueva-funcionalidad).

3.  Realiza tus cambios y haz commit (git commit -am 'Añade nueva funcionalidad').

4.  Haz push a la rama (git push origin feature/nueva-funcionalidad).

5.  Abre un Pull Request.

---

## Construido con

- **Java**: El lenguaje de programación principal utilizado para implementar el servidor web.
- **Maven**: Herramienta de gestión y construcción de proyectos para manejar las dependencias y compilar el código.
- **Java Networking**: Librerías estándar de Java para manejar conexiones de red y protocolos HTTP.
- **Git**: Sistema de control de versiones para gestionar el código fuente.
- **HTML/CSS/JavaScript**: Tecnologías front-end utilizadas para crear la aplicación web de prueba.
- **JUnit**: Framework para realizar pruebas unitarias y asegurar la calidad del código.

---

## Autor

* **Samuel Felipe Díaz Mamanche**

See also the list of [contributors](https://github.com/Samuelfdm/TallerWebServer/contributors) who participated in this project.

## Licencia

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Agradecimientos

* [Escuela Colombiana de Ingeniería: Julio Garavito](https://www.escuelaing.edu.co/es/)