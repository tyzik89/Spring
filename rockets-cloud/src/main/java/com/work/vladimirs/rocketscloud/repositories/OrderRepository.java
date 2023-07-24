package com.work.vladimirs.rocketscloud.repositories;

import com.work.vladimirs.rocketscloud.inventory.RocketOrder;
import org.springframework.data.repository.Repository;

/**
 * 'extends Repository<>' либо 'extends CrudRepository<>' нужен для совместного использования с Spring Data Jdbc
 * 'CrudRepository<>' уже определяет нужные методы и можно их не явно не определеять
 */
public interface OrderRepository extends Repository<RocketOrder, Long> {

    RocketOrder save(RocketOrder rocketOrder);
}
