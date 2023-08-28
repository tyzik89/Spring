package com.work.vladimirs.rocketscloud.repositories;

import com.work.vladimirs.rocketscloud.inventory.RocketOrder;

/**
 * 'extends Repository<>' либо 'extends CrudRepository<>' нужен для совместного использования с Spring Data Jdbc
 * 'CrudRepository<>' уже определяет нужные методы и можно их не явно не определеять
 */
public interface OrderRepository {

    RocketOrder save(RocketOrder rocketOrder);
}
