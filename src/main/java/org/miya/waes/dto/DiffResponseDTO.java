package org.miya.waes.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

/**
 * DTO for transferring comparision result
 *
 * @author Yasin Kızılkaya
 */

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiffResponseDTO {

    @JsonProperty(value = "isEqual")
    private boolean equal;

    private String message;

    List<Integer> mismatchOffsets;

    public DiffResponseDTO(String message) {
        this.message = message;
    }

    public DiffResponseDTO(boolean equal, String message) {
        this.equal = equal;
        this.message = message;
    }

    public DiffResponseDTO(String message, List<Integer> mismatchOffsets) {
        this.message = message;
        this.mismatchOffsets = mismatchOffsets;
    }
}
