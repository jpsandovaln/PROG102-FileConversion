package org.fundacionjala.converter.database.repository;

import org.fundacionjala.converter.database.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Elizabeth Bravo
 * @version 0.2
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserById(long id);
    User getUserById(long id);
    User findUserByUsername(String username);
}
