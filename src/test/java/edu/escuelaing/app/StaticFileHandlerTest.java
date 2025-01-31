package edu.escuelaing.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StaticFileHandlerTest {

    private static final String STATIC_PATH = "./src/main/resources/static/";
    private static final String CSS_FILE = "style.css";
    private static final String PNG_FILE = "image.png";
    private static final String JS_FILE = "script.js";
    private static final String JS_CONTENT = "console.log('Hello, world!');";
    private static final String CSS_CONTENT = "body { background-color: #f0f0f0; }";

    @Mock
    private PrintWriter printWriter;

    private StringWriter stringWriter;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        outputStream = new ByteArrayOutputStream();

        // Crear archivos de prueba si no existen
        createTestFile(CSS_FILE, CSS_CONTENT);
        createTestFile(JS_FILE, JS_CONTENT);
        createTestImage(PNG_FILE);
    }

    private void createTestFile(String filename, String content) throws IOException {
        File file = new File(STATIC_PATH + filename);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(content);
            }
        }
    }

    private void createTestImage(String filename) throws IOException {
        File file = new File(STATIC_PATH + filename);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(new byte[]{(byte) 0x89, 'P', 'N', 'G', '\r', '\n', 0x1A, '\n'}); // Mínimo encabezado PNG
            }
        }
    }

    @Test
    void testServeCssFile() {
        StaticFileHandler.serve("/" + CSS_FILE, outputStream, printWriter);

        printWriter.flush();
        String output = outputStream.toString();

        assertTrue(output.startsWith("HTTP/1.1 200 OK"), "La respuesta no contiene el código 200 OK.");
        assertTrue(output.contains("Content-Type: text/css"), "El Content-Type no es text/css.");
    }

    @Test
    void testServeJsFile() {
        StaticFileHandler.serve("/" + JS_FILE, outputStream, printWriter);

        printWriter.flush();
        String output = outputStream.toString();

        assertTrue(output.startsWith("HTTP/1.1 200 OK"), "La respuesta no contiene el código 200 OK.");
        assertTrue(output.contains("Content-Type: application/javascript"), "El Content-Type no es application/javascript.");
    }

    @Test
    void testServePngFile() {
        StaticFileHandler.serve("/" + PNG_FILE, outputStream, printWriter);

        printWriter.flush();
        String output = outputStream.toString();

        assertTrue(output.startsWith("HTTP/1.1 200 OK"), "La respuesta no contiene el código 200 OK.");
        assertTrue(output.contains("Content-Type: image/png"), "El Content-Type no es image/png.");
    }
}