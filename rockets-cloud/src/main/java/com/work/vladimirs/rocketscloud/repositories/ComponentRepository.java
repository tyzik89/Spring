package com.work.vladimirs.rocketscloud.repositories;

import com.work.vladimirs.rocketscloud.inventory.Component;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * 'extends Repository<>' либо 'extends CrudRepository<>' нужен для совместного использования с Spring Data Jdbc
 * 'CrudRepository<>' уже определяет нужные методы и можно их не явно не определеять
 */
public interface ComponentRepository extends Repository<Component, String> {

    Iterable<Component> findAll();

    Optional<Component> findById(String id);

    Component save(Component component);
}
