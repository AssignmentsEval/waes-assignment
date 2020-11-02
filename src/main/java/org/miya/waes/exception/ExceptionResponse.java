package org.miya.waes.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Response body in error situation
 *
 * @author Yasin Kızılkaya
 */

@Getter
@AllArgsConstructor
class ExceptionResponse {

    private String errorMessage;

}
