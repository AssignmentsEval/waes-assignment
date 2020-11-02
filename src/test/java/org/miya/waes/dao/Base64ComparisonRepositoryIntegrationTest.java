package org.miya.waes.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.miya.waes.entity.Base64Comparison;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration Tests, for {@link org.miya.waes.dao.Base64ComparisonRepository} class in dao layer
 *
 * with embedded h2 configuration which provided by DataJpaTest
 *
 * @author Yasin Kızılkaya
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class Base64ComparisonRepositoryIntegrationTest {

    @Autowired
    private Base64ComparisonRepository base64ComparisonRepository;

    @Test
    public void testBase64ComparisionRepositoryNotNull() {
        assertNotNull(base64ComparisonRepository);
    }

    @Test
    public void testSaveDataToBase64ComparisionRepositoryAndRetrieveSuccessfully() {

        Long comparisionId = 123L;

        Base64Comparison base64Comparison = new Base64Comparison(comparisionId);
        base64Comparison.setLeftSide("YmFzaWM=");

        assertNotNull(base64Comparison);
        assertEquals("YmFzaWM=", base64Comparison.getLeftSide());

        base64ComparisonRepository.save(base64Comparison);

        Optional<Base64Comparison> comparisionRepositoryById = base64ComparisonRepository.findById(comparisionId);

        assertFalse(comparisionRepositoryById.isEmpty());
        Base64Comparison savedBase64Comparison = comparisionRepositoryById.get();

        assertEquals(base64Comparison.getId(), savedBase64Comparison.getId());
        assertEquals(base64Comparison.getLeftSide(), savedBase64Comparison.getLeftSide());
        assertEquals(base64Comparison.getRightSide(), savedBase64Comparison.getRightSide());
    }

    @Test
    public void testUpdateDataToBase64ComparisionRepositoryAndRetrieveSuccessfully() {

        Long comparisionId = 123L;

        Base64Comparison base64Comparison = new Base64Comparison(comparisionId);
        base64Comparison.setLeftSide("YmFzaWM=");

        assertNotNull(base64Comparison);
        assertEquals("YmFzaWM=", base64Comparison.getLeftSide());

        // save comparision
        base64ComparisonRepository.save(base64Comparison);

        Optional<Base64Comparison> comparisionRepositoryById = base64ComparisonRepository.findById(comparisionId);
        assertFalse(comparisionRepositoryById.isEmpty());
        Base64Comparison savedBase64Comparison = comparisionRepositoryById.get();

        assertEquals(base64Comparison.getId(), savedBase64Comparison.getId());
        assertEquals(base64Comparison.getLeftSide(), savedBase64Comparison.getLeftSide());
        assertEquals(base64Comparison.getRightSide(), savedBase64Comparison.getRightSide());

        // update comparision
        savedBase64Comparison.setLeftSide("XmFzaWM=");
        base64ComparisonRepository.save(savedBase64Comparison);

        Optional<Base64Comparison> updatedComparisionRepositoryById = base64ComparisonRepository.findById(comparisionId);
        assertFalse(updatedComparisionRepositoryById.isEmpty());
        Base64Comparison updatedBase64Comparison = comparisionRepositoryById.get();

        assertEquals(savedBase64Comparison, updatedBase64Comparison);
    }
}
