package org.miya.waes.exception;

/**
 * Custom RuntimeException, can be throw when a side not available(null) in comparison
 *
 * @author Yasin Kızılkaya
 */

public class SideNotFoundException extends RuntimeException {
    public SideNotFoundException(String message) {
        super(message);
    }
}
