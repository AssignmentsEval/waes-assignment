package org.miya.waes.service;

import org.junit.Before;
import org.junit.Test;
import org.miya.waes.dto.DiffResponseDTO;
import org.miya.waes.exception.SideNotFoundException;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.miya.waes.util.StaticMessages.*;

/**
 * Unit Tests, for methods in {@link org.miya.waes.service.Base64DiffServiceImpl} class
 *
 * @author Yasin Kızılkaya
 */

public class Base64DiffServiceImplTest {

    private Base64DiffServiceImpl base64DiffService;

    @Before
    public void Base64DiffServiceImplTest() {
        base64DiffService = new Base64DiffServiceImpl();
    }

    @Test(expected = SideNotFoundException.class)
    public void testCompareWithNullLeftSide() {
        base64DiffService.getDiff(null, "YmFzaWM=");
    }

    @Test(expected = SideNotFoundException.class)
    public void testCompareWithNullRightSide() {
        base64DiffService.getDiff("YmFzaWM=", null);
    }

    @Test
    public void testCompareWithMismatchSizes() {
        DiffResponseDTO diff = base64DiffService.getDiff("YmFzaWM=", "YmFzaW=");

        assertNotNull(diff);
        assertFalse(diff.isEqual());
        assertEquals(MISMATCH_SIZES, diff.getMessage());
        assertNull(diff.getMismatchOffsets());
    }

    @Test
    public void testCompareWithMatch() {
        DiffResponseDTO diff = base64DiffService.getDiff("YmFzaWM=", "YmFzaWM=");

        assertNotNull(diff);
        assertTrue(diff.isEqual());
        assertEquals(MATCHED, diff.getMessage());
        assertNull(diff.getMismatchOffsets());
    }

    @Test
    public void testCompareWithMisMatchSidesExpectIndex2AsMissedOffset() {
        DiffResponseDTO diff = base64DiffService.getDiff("YmFzaWM=", "YmKzaWM=");

        assertNotNull(diff);
        assertFalse(diff.isEqual());
        assertEquals(MISMATCHED, diff.getMessage());
        assertNotNull(diff.getMismatchOffsets());
        assertEquals(1, diff.getMismatchOffsets().size());
        assertEquals(2, diff.getMismatchOffsets().get(0));
    }

    @Test
    public void testCompareWithMisMatchSidesExpect3Offset() {
        // missed offset: 2,4,5
        DiffResponseDTO diff = base64DiffService.getDiff("YmFzbCM=", "YmKzaWM=");

        assertNotNull(diff);
        assertFalse(diff.isEqual());
        assertEquals(MISMATCHED, diff.getMessage());
        assertNotNull(diff.getMismatchOffsets());
        assertEquals(3, diff.getMismatchOffsets().size());
        assertTrue(diff.getMismatchOffsets().containsAll(asList(2, 4, 5)));
    }

}
