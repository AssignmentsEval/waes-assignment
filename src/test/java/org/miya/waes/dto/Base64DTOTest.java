package org.miya.waes.dto;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests, for methods in {@link org.miya.waes.dto.Base64DTO} class
 *
 * @author Yasin Kızılkaya
 */

class Base64DTOTest {

    @Test
    void testModelHasDefaultConstructorExpectNotNullObject() {
        Base64DTO base64DTO = new Base64DTO();

        assertNotNull(base64DTO);
        assertNull(base64DTO.getData());
    }

    @Test
    void testModelHasGetterAndSetterForDataField() {
        Base64DTO base64DTO = new Base64DTO();
        base64DTO.setData("YmFzaWM=");

        assertNotNull(base64DTO);
        assertNotNull(base64DTO.getData());
        assertEquals("YmFzaWM=", base64DTO.getData());
    }

    @Test
    void testModelValidateGivenEmptyDataExpectConstraintViolationException() {

        Base64DTO base64DTO = new Base64DTO();
        base64DTO.setData("");

        assertNotNull(base64DTO);
        assertEquals("", base64DTO.getData());

        Set<ConstraintViolation<Base64DTO>> constraintViolationSet =
                Validation.buildDefaultValidatorFactory().getValidator().validate(base64DTO);

        assertNotNull(constraintViolationSet);
        assertEquals(1, constraintViolationSet.size());
        assertEquals("must not be empty", constraintViolationSet.iterator().next().getMessage());
    }

}
