package com.work.vladimirs.rocketscloud.repositories.jdbc;

import com.work.vladimirs.rocketscloud.inventory.jdbc.ComponentJdbc;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * 'extends Repository<>' либо 'extends CrudRepository<>' нужен для совместного использования с Spring Data Jdbc
 * 'CrudRepository<>' уже определяет нужные методы и можно их не явно не определеять
 */
public interface ComponentRepositoryJDBC {

    Iterable<ComponentJdbc> findAll();

    Optional<ComponentJdbc> findById(String id);

    ComponentJdbc save(ComponentJdbc component);
}
