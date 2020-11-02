package org.miya.waes.dto;

import org.miya.waes.entity.Base64Comparison;

/**
 * Mapper, converts Base64Comparison Entity object to Base64ComparisonDTO object
 *
 * @author Yasin Kızılkaya
 */

public class Base64ComparisonDTOMapper {

    private Base64ComparisonDTOMapper() {
    }

    public static Base64ComparisonDTO convertToDTO(Base64Comparison comparision) {
        Base64ComparisonDTO base64ComparisonDTO = new Base64ComparisonDTO();
        base64ComparisonDTO.setId(comparision.getId());
        base64ComparisonDTO.setLeftSide(comparision.getLeftSide());
        base64ComparisonDTO.setRightSide(comparision.getRightSide());

        return base64ComparisonDTO;
    }
}
