package org.miya.waes.service;

import org.miya.waes.dao.Base64ComparisonRepository;
import org.miya.waes.entity.Base64Comparison;
import org.miya.waes.exception.IDNotFoundException;
import org.springframework.stereotype.Service;

import static org.miya.waes.util.StaticMessages.ID_NOT_AVAILABLE;

/**
 * Service implementation for operations on a comparison which is base64 data format
 *
 * @author Yasin Kızılkaya
 */

@Service
public class Base64ComparisonServiceImpl implements ComparisonService {

    private final Base64ComparisonRepository sidesRepository;

    Base64ComparisonServiceImpl(Base64ComparisonRepository sidesRepository) {
        this.sidesRepository = sidesRepository;
    }

    @Override
    public Base64Comparison updateLeftSide(Long id, String text) {
        Base64Comparison base64Comparison = sidesRepository.findById(id).orElseGet(() -> new Base64Comparison(id));
        base64Comparison.setLeftSide(text);

        return sidesRepository.save(base64Comparison);
    }

    @Override
    public Base64Comparison updateRightSide(Long id, String text) {
        Base64Comparison base64Comparison = sidesRepository.findById(id).orElseGet(() -> new Base64Comparison(id));
        base64Comparison.setRightSide(text);

        return sidesRepository.save(base64Comparison);
    }

    @Override
    public Base64Comparison getComparision(Long id) {
        return sidesRepository.findById(id).orElseThrow(() -> new IDNotFoundException(ID_NOT_AVAILABLE));
    }
}
