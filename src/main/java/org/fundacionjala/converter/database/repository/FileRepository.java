/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.database.repository;

import org.fundacionjala.converter.database.entity.File;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Laura Montaño
 * @version 0.3
 */
public interface FileRepository extends CrudRepository<File, Long> {
    File findByMd5(String md5);
}
