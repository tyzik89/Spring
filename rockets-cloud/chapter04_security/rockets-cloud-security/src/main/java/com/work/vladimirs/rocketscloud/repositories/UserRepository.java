package com.work.vladimirs.rocketscloud.repositories;

import com.work.vladimirs.rocketscloud.security.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
