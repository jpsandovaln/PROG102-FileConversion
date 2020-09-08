package org.fundacionjala.converter.model.repository;

import org.fundacionjala.converter.model.entity.File;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Laura Montaño
 * @version 0.1
 */
public interface FileRepository extends CrudRepository<File, Long> {
    File findByMd5(String md5);
}
