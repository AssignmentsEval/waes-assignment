package org.miya.waes.dto;

import org.junit.jupiter.api.Test;
import org.miya.waes.entity.Base64Comparison;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.miya.waes.dto.Base64ComparisonDTOMapper.convertToDTO;

/**
 * Unit Tests, for mapper method in {@link org.miya.waes.dto.Base64ComparisonDTOMapper}
 *
 * @author Yasin Kızılkaya
 */

class Base64ComparisonDTOMapperTest {

    @Test
    void testConvertToDTO() {
        Base64Comparison base64Comparison = new Base64Comparison(123L);
        base64Comparison.setLeftSide("YmFzaWM=");

        Base64ComparisonDTO dto = convertToDTO(base64Comparison);

        assertNotNull(dto);
        assertEquals(base64Comparison.getId(), dto.getId());
        assertEquals(base64Comparison.getLeftSide(), dto.getLeftSide());
        assertEquals(base64Comparison.getRightSide(), dto.getRightSide());
    }
}