package org.miya.waes.service;

import org.miya.waes.dto.DiffResponseDTO;

/**
 * Service interface for comparison
 *
 * @author Yasin Kızılkaya
 */

public interface DiffService {
    DiffResponseDTO getDiff(String left, String right);
}
