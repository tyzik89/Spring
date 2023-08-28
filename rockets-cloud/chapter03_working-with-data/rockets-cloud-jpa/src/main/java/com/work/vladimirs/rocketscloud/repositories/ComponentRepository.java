package com.work.vladimirs.rocketscloud.repositories;

import com.work.vladimirs.rocketscloud.inventory.Component;
import org.springframework.data.repository.CrudRepository;

/**
 * 'extends CrudRepository<>' нужен для совместного использования с Spring Data Jdbc или JPA
 * эти базовые интерфейсы уже определяют нужные методы и можно их не явно не определеять
 */
public interface ComponentRepository extends CrudRepository<Component, String> {

    // Часть методов CRUD можно явно не определять
//    Iterable<Component> findAll();

    // Часть методов CRUD можно явно не определять
//    Optional<Component> findById(String id);

    // Часть методов CRUD можно явно не определять
//    Component save(Component component);
}
