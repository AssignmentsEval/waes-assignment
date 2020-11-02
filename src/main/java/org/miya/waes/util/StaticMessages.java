package org.miya.waes.util;

/**
 * StaticMessages provides static messages in final format
 *
 * @author Yasin Kızılkaya
 */

public abstract class StaticMessages {
    public static final String ID_NOT_AVAILABLE = "Given id does not have any side(left|right)";
    public static final String EMPTY_SIDE = "A side can not found";
    public static final String MISMATCH_SIZES = "Mismatch sizes";
    public static final String MATCHED = "Matched";
    public static final String MISMATCHED = "MisMatched";

    private StaticMessages() {
    }
}
