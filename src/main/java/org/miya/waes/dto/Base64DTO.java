package org.miya.waes.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * DTO for data of a side in a comparision
 *
 * @author Yasin Kızılkaya
 */

@Getter
@Setter
public class Base64DTO {

    @NotEmpty
    private String data;
}
