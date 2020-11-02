package org.miya.waes.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit Tests, for methods in {@link org.miya.waes.exception.ExceptionResponse} class
 *
 * @author Yasin Kızılkaya
 */

class ExceptionResponseTest {

    @Test
    void testModelAllArgsConstructorExpectNotNullObject() {
        String errorMessage = " test error message";
        ExceptionResponse exceptionResponse = new ExceptionResponse(errorMessage);

        assertNotNull(exceptionResponse);
        assertEquals(errorMessage, exceptionResponse.getErrorMessage());
    }
}