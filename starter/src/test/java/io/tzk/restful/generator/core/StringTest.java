package io.tzk.restful.generator.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringTest {

    @Test
    public void formatted(){
        String formatted = "123%d".formatted(4);
        Assertions.assertEquals(formatted, "1234");
    }
}
