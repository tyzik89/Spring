package com.work.vladimirs.rocketscloud.repositories;

import com.work.vladimirs.rocketscloud.inventory.RocketOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 'extends Repository<>' либо 'extends CrudRepository<>' нужен для совместного использования с Spring Data Jdbc
 * 'CrudRepository<>' уже определяет нужные методы и можно их не явно не определеять
 */
public interface OrderRepository extends CrudRepository<RocketOrder, Long> {

    // Часть методов CRUD можно явно не определять
//    RocketOrder save(RocketOrder rocketOrder);

    // Новый уникальный метод по поиску ордеров по заданному району
    // используется DSL - предметно - ориентированный язык, по имени метода определяется его функционал.
    List<RocketOrder> findByDeliveryZip(String deliveryZip);

    // Новый уникальный метод по поиску ордеров по заданному району и диапазону дат
    // используется DSL - предметно - ориентированный язык, по имени метода определяется его функционал.
//    List<RocketOrder> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDAte);

    // В @query можно определеить любой специализированный запрос, который трудно представить в виде имени метода
//    @Query("Order o where o.deliveryCity='Seattle'")
//    List<RocketOrder> readOrdersDeliveredInSeattle();
}
