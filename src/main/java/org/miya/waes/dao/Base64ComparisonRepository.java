package org.miya.waes.dao;

import org.miya.waes.entity.Base64Comparison;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for providing CRUD operations on base64 formatted data
 *
 * @author Yasin Kızılkaya
 */

@Repository
public interface Base64ComparisonRepository extends CrudRepository<Base64Comparison, Long> {
}
