package org.miya.waes.controller;

import org.miya.waes.dto.Base64ComparisonDTO;
import org.miya.waes.dto.Base64DTO;
import org.miya.waes.dto.DiffResponseDTO;
import org.miya.waes.entity.Base64Comparison;
import org.miya.waes.service.ComparisonService;
import org.miya.waes.service.DiffService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.miya.waes.dto.Base64ComparisonDTOMapper.convertToDTO;
import static org.springframework.http.HttpStatus.CREATED;

/**
 * RESTful web service endpoints for a comparision that consist of two data in base64 format
 *
 * @author Yasin Kızılkaya
 */

@RestController
@RequestMapping("v1/diff")
public class Base64ComparisonController {

    private final ComparisonService comparisonService;
    private final DiffService diffService;

    public Base64ComparisonController(ComparisonService comparisonService, DiffService diffService) {
        this.comparisonService = comparisonService;
        this.diffService = diffService;
    }

    @PostMapping("{id}/left")
    @ResponseStatus(CREATED)
    public Base64ComparisonDTO updateLeftSide(@PathVariable Long id, @Valid @RequestBody Base64DTO base64DTO) {
        Base64Comparison comparision = comparisonService.updateLeftSide(id, base64DTO.getData());
        return convertToDTO(comparision);
    }

    @PostMapping("{id}/right")
    @ResponseStatus(CREATED)
    public Base64ComparisonDTO updateRightSide(@PathVariable Long id, @Valid @RequestBody Base64DTO base64DTO) {
        Base64Comparison comparision = comparisonService.updateRightSide(id, base64DTO.getData());
        return convertToDTO(comparision);
    }

    @GetMapping("{id}")
    public DiffResponseDTO getDiff(@PathVariable Long id) {
        Base64Comparison base64Comparison = comparisonService.getComparision(id);

        return diffService.getDiff(base64Comparison.getLeftSide(), base64Comparison.getRightSide());
    }
}
