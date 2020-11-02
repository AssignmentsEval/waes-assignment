package org.miya.waes.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests, for methods in {@link org.miya.waes.dto.Base64ComparisonDTO} class
 *
 * @author Yasin Kızılkaya
 */

class Base64ComparisonDTOTest {

    @Test
    void testModelHasDefaultConstructorExpectNotNullObject() {
        Base64ComparisonDTO base64ComparisonDTO = new Base64ComparisonDTO();

        assertNotNull(base64ComparisonDTO);
        assertNull(base64ComparisonDTO.getId());
        assertNull(base64ComparisonDTO.getLeftSide());
        assertNull(base64ComparisonDTO.getRightSide());
    }

    @Test
    void testModelGetterAndSetterMethods() {
        Base64ComparisonDTO base64ComparisonDTO = new Base64ComparisonDTO();
        base64ComparisonDTO.setId(123L);
        base64ComparisonDTO.setLeftSide("YmFzaWM=");
        base64ComparisonDTO.setRightSide("YmFzaWM=");

        assertNotNull(base64ComparisonDTO);
        assertEquals(123L, base64ComparisonDTO.getId());
        assertEquals("YmFzaWM=", base64ComparisonDTO.getLeftSide());
        assertEquals("YmFzaWM=", base64ComparisonDTO.getRightSide());
    }
}