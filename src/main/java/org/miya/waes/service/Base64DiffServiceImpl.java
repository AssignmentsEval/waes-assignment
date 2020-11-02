package org.miya.waes.service;

import org.miya.waes.dto.DiffResponseDTO;
import org.miya.waes.exception.SideNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.miya.waes.util.StaticMessages.*;

/**
 * Service implementation to compare two string that are base64 data format
 *
 * @author Yasin Kızılkaya
 */

@Service
public class Base64DiffServiceImpl implements DiffService {

    public DiffResponseDTO getDiff(String left, String right) {

        if (left == null || right == null)
            throw new SideNotFoundException(EMPTY_SIDE);

        if (left.length() != right.length()) {
            return new DiffResponseDTO(MISMATCH_SIZES);
        }
        if (left.equals(right)) {
            return new DiffResponseDTO(true, MATCHED);
        } else {

            List<Integer> mismatchOffsets = new ArrayList<>();

            for (int i = 0; i < left.length(); i++) {
                if (left.charAt(i) != right.charAt(i))
                    mismatchOffsets.add(i);
            }

            return new DiffResponseDTO(MISMATCHED, mismatchOffsets);
        }
    }
}
