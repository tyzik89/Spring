package com.work.vladimirs.rocketscloud.web.design;

import com.work.vladimirs.rocketscloud.data.repositories.jpa.ComponentRepository;
import com.work.vladimirs.rocketscloud.data.repositories.jpa.RocketRepository;
import com.work.vladimirs.rocketscloud.data.repositories.jpa.UserRepository;
import com.work.vladimirs.rocketscloud.models.inventory.Rocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/design", produces="application/json")
@CrossOrigin(origins="*")
public class DesignRocketControllerRest {
    private static final Logger LOG = LoggerFactory.getLogger(DesignRocketControllerRest.class);

    private final RocketRepository rocketRepository;
    private final ComponentRepository componentRepository;
    private UserRepository userRepository;

    @Autowired
    public DesignRocketControllerRest(ComponentRepository componentRepository, RocketRepository rocketRepository, UserRepository userRepository) {
        this.componentRepository = componentRepository;
        this.rocketRepository = rocketRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/recent")
    public Iterable<Rocket> recentTacos() {
        return rocketRepository.findAll();
    }

    @GetMapping("/{id}")
    public Rocket rocketById(@PathVariable("id") Long id) {
        Optional<Rocket> optRocket = rocketRepository.findById(id);
        if (optRocket.isPresent()) {
            return optRocket.get();
        }
        return null;
    }
}
