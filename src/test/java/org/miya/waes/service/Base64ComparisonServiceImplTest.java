package org.miya.waes.service;

import org.junit.Before;
import org.junit.Test;
import org.miya.waes.dao.Base64ComparisonRepository;
import org.miya.waes.entity.Base64Comparison;
import org.miya.waes.exception.IDNotFoundException;
import org.mockito.Mockito;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

/**
 * Unit Tests, for methods in {@link org.miya.waes.service.Base64ComparisonServiceImpl} class
 *
 * @author Yasin Kızılkaya
 */

public class Base64ComparisonServiceImplTest {

    private ComparisonService comparisonService;
    private Base64ComparisonRepository base64ComparisonRepositoryMock;

    @Before
    public void setUp() {
        base64ComparisonRepositoryMock = Mockito.mock(Base64ComparisonRepository.class);
        comparisonService = new Base64ComparisonServiceImpl(base64ComparisonRepositoryMock);
    }

    @Test
    public void testSaveComparisionWithLeftSide() {

        Long comparisionId = 123L;
        String leftSide = "YmFzaWM=";

        Mockito.when(base64ComparisonRepositoryMock.findById(comparisionId)).thenReturn(empty());
        Mockito.when(base64ComparisonRepositoryMock.save(any(Base64Comparison.class))).thenAnswer(i -> i.getArguments()[0]);

        Base64Comparison updatedLeftSideComparision = comparisonService.updateLeftSide(comparisionId, leftSide);

        assertNotNull(updatedLeftSideComparision);
        assertEquals(comparisionId, updatedLeftSideComparision.getId());
        assertEquals(leftSide, updatedLeftSideComparision.getLeftSide());
        assertNull(updatedLeftSideComparision.getRightSide());
    }

    @Test
    public void testUpdateExistComparisionWithLeftSide() {

        Long comparisionId = 123L;
        String leftSide = "XXmFzaWM=";

        Base64Comparison existComparision = new Base64Comparison(comparisionId);
        existComparision.setLeftSide("YmFzaWM=");
        existComparision.setRightSide("YmFzaWM=");

        Mockito.when(base64ComparisonRepositoryMock.findById(comparisionId)).thenReturn(Optional.of(existComparision));
        Mockito.when(base64ComparisonRepositoryMock.save(any(Base64Comparison.class))).thenAnswer(i -> i.getArguments()[0]);

        Base64Comparison updatedLeftSideComparision = comparisonService.updateLeftSide(comparisionId, leftSide);

        assertNotNull(updatedLeftSideComparision);
        assertEquals(existComparision.getId(), updatedLeftSideComparision.getId());
        assertEquals(leftSide, updatedLeftSideComparision.getLeftSide());
        assertEquals(existComparision.getRightSide(), updatedLeftSideComparision.getRightSide());
    }

    @Test
    public void testSaveComparisionWithRightSide() {

        Long comparisionId = 123L;
        String rightSide = "YmFzaWM=";

        Mockito.when(base64ComparisonRepositoryMock.findById(comparisionId)).thenReturn(empty());
        Mockito.when(base64ComparisonRepositoryMock.save(any(Base64Comparison.class))).thenAnswer(i -> i.getArguments()[0]);

        Base64Comparison updatedLeftSideComparision = comparisonService.updateRightSide(comparisionId, rightSide);

        assertNotNull(updatedLeftSideComparision);
        assertEquals(comparisionId, updatedLeftSideComparision.getId());
        assertEquals(rightSide, updatedLeftSideComparision.getRightSide());
        assertNull(updatedLeftSideComparision.getLeftSide());
    }

    @Test
    public void testUpdateExistComparisionWithRightSide() {

        Long comparisionId = 123L;
        String rightSide = "YmFzaWM=";

        Base64Comparison existComparision = new Base64Comparison(comparisionId);
        existComparision.setLeftSide("YmFzaWM=");

        Mockito.when(base64ComparisonRepositoryMock.findById(comparisionId)).thenReturn(Optional.of(existComparision));
        Mockito.when(base64ComparisonRepositoryMock.save(any(Base64Comparison.class))).thenAnswer(i -> i.getArguments()[0]);

        Base64Comparison updatedLeftSideComparision = comparisonService.updateRightSide(comparisionId, rightSide);

        assertNotNull(updatedLeftSideComparision);
        assertEquals(existComparision.getId(), updatedLeftSideComparision.getId());
        assertEquals(existComparision.getLeftSide(), updatedLeftSideComparision.getLeftSide());
        assertEquals(rightSide, updatedLeftSideComparision.getRightSide());
    }

    @Test
    public void testGetExistComparision() {
        Long comparisionId = 123L;

        Base64Comparison existComparision = new Base64Comparison(comparisionId);
        existComparision.setLeftSide("YmFzaWM=");

        Mockito.when(base64ComparisonRepositoryMock.findById(comparisionId)).thenReturn(of(existComparision));

        Base64Comparison comparision = comparisonService.getComparision(comparisionId);

        assertNotNull(comparision);
        assertEquals(existComparision.getLeftSide(), comparision.getLeftSide());
        assertNull(comparision.getRightSide());
    }

    @Test(expected = IDNotFoundException.class)
    public void testGetUnAvailableComparision() {
        Long comparisionId = 123L;

        Mockito.when(base64ComparisonRepositoryMock.findById(comparisionId)).thenReturn(empty());

        comparisonService.getComparision(comparisionId);
    }

}
