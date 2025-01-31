package edu.escuelaing.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HelloServiceTest {

    @Test
    void testProcessWithName() {
        String result = HelloService.process("/app/hello?name=Felipe");
        assertEquals("{\"name\":\"Felipe\"}", result);
    }

    @Test
    void testProcessWithoutName() {
        String result = HelloService.process("/app/hello");
        assertEquals("{\"name\":\"Unknown\"}", result);
    }
}
