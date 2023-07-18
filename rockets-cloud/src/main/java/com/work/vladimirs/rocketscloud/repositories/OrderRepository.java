package com.work.vladimirs.rocketscloud.repositories;

import com.work.vladimirs.rocketscloud.inventory.RocketOrder;

public interface OrderRepository {

    RocketOrder save(RocketOrder rocketOrder);
}
