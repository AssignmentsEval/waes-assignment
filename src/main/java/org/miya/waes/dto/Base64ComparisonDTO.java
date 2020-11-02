package org.miya.waes.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO for a comparision
 *
 * @author Yasin Kızılkaya
 */

@Getter
@Setter
public class Base64ComparisonDTO {
    private Long id;
    private String leftSide;
    private String rightSide;
}
