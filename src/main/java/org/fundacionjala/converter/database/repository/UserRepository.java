/**
 * Copyright (c) 2020 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacionjala.converter.database.repository;

import org.fundacionjala.converter.database.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Elizabeth Bravo
 * @version 0.3
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserById(long id);
    User getUserById(long id);
    User findUserByUsername(String username);
}
