package com.work.vladimirs.shawermacloud.repositories.JPA.repositories;

import com.work.vladimirs.shawermacloud.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByUsername(String username);
}
