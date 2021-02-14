package com.work.vladimirs.rocketscloud.data.repositories.jdbc;

import com.work.vladimirs.rocketscloud.models.inventory.Rocket;

public interface RocketRepository {

    Rocket save(Rocket rocket);
}
