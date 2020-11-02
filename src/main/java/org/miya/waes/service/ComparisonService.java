package org.miya.waes.service;

import org.miya.waes.entity.Base64Comparison;

/**
 * Service interface for operations on a comparison
 *
 * @author Yasin Kızılkaya
 */

public interface ComparisonService {

    Base64Comparison getComparision(Long id);

    Base64Comparison updateLeftSide(Long id, String text);

    Base64Comparison updateRightSide(Long id, String text);

}
