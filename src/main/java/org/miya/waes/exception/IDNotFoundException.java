package org.miya.waes.exception;

/**
 * Custom RuntimeException, can be throw when given id not available in DB
 *
 * @author Yasin Kızılkaya
 */

public class IDNotFoundException extends RuntimeException {
    public IDNotFoundException(String message) {
        super(message);
    }
}
