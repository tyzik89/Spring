package com.work.vladimirs.rocketscloud.repositories.jdbc;

import com.work.vladimirs.rocketscloud.inventory.jdbc.RocketOrderJdbc;
import org.springframework.data.repository.Repository;

/**
 * 'extends Repository<>' либо 'extends CrudRepository<>' нужен для совместного использования с Spring Data Jdbc
 * 'CrudRepository<>' уже определяет нужные методы и можно их не явно не определеять
 */
public interface OrderRepositoryJDBC {

    RocketOrderJdbc save(RocketOrderJdbc rocketOrder);
}
