package org.miya.waes.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entity for a comparison in base64 format
 *
 * @author Yasin Kızılkaya
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Base64Comparison {

    @Id
    private Long id;
    private String leftSide;
    private String rightSide;

    public Base64Comparison(Long id) {
        this.id = id;
    }
}

