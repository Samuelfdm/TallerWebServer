package edu.escuelaing.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.PrintWriter;
import java.io.StringWriter;
import static org.junit.jupiter.api.Assertions.*;

public class ResponseHelperTest {

    @Mock
    private PrintWriter printWriter;

    private StringWriter stringWriter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
    }

    @Test
    void testSendJsonResponse() {
        String jsonResponse = "{\"message\":\"Hello, World!\"}";

        ResponseHelper.sendJsonResponse(printWriter, jsonResponse);

        printWriter.flush(); // Asegurarse de que el contenido se escribe
        String output = stringWriter.toString();

        assertTrue(output.contains("Content-Type: application/json"), "El encabezado de tipo de contenido no está presente");
        assertTrue(output.contains(jsonResponse), "El cuerpo JSON no coincide con el esperado");
    }
}