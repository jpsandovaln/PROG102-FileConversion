package org.fundacionjala.converter.model.repository;

import org.fundacionjala.converter.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Elizabeth Bravo
 * @version 0.2
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserById(long id);
}
