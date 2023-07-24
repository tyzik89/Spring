package com.work.vladimirs.rocketscloud.configuration;

import com.work.vladimirs.rocketscloud.repositories.ComponentRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import com.work.vladimirs.rocketscloud.inventory.Component;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * После загрузки контекста можно выполнить некоторые шаги через реализацию интерфейса CommandLineRunner или ApplicationRunner
 */
@Configuration
public class PreLoadConfig {
    
   /* @Bean
    public CommandLineRunner dataLoader(ComponentRepository componentRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                componentRepository.save(new Component("PMK1C", "PODS MK1 Cockpit", Component.Type.PODS));
                componentRepository.save(new Component("PMK2C", "PODS MK2 Cockpit", Component.Type.PODS));
                componentRepository.save(new Component("PMK1CP", "PODS MK1 Command Pod", Component.Type.PODS));
                componentRepository.save(new Component("PMK2CP", "PODS MK2 Command Pod", Component.Type.PODS));

                componentRepository.save(new Component("RWI", "RW Inline", Component.Type.REACTION_WHEELS));
                componentRepository.save(new Component("RWA", "RW Advanced", Component.Type.REACTION_WHEELS));

                componentRepository.save(new Component("ELVT30", "LV-T30", Component.Type.ENGINES));
                componentRepository.save(new Component("ELVT45", "LVT-45", Component.Type.ENGINES));
                componentRepository.save(new Component("EREL10", "RE-L10", Component.Type.ENGINES));
                componentRepository.save(new Component("EREI5", "RE-I5", Component.Type.ENGINES));

                componentRepository.save(new Component("FFLT100", "FL-T100", Component.Type.FUEL_TANKS));
                componentRepository.save(new Component("FFLT200", "FL-T200", Component.Type.FUEL_TANKS));
                componentRepository.save(new Component("FFLT800", "FL-T400", Component.Type.FUEL_TANKS));
                componentRepository.save(new Component("FR12", "FR-12", Component.Type.FUEL_TANKS));
            }
        };
    }*/

/*    @Bean
    public ApplicationRunner dataLoader(ComponentRepository componentRepository) {
        return new ApplicationRunner() {
            @Override
            public void run(ApplicationArguments args) throws Exception {
                componentRepository.save(new Component("PMK1C", "PODS MK1 Cockpit", Component.Type.PODS));
                componentRepository.save(new Component("PMK2C", "PODS MK2 Cockpit", Component.Type.PODS));
                componentRepository.save(new Component("PMK1CP", "PODS MK1 Command Pod", Component.Type.PODS));
                componentRepository.save(new Component("PMK2CP", "PODS MK2 Command Pod", Component.Type.PODS));

                componentRepository.save(new Component("RWI", "RW Inline", Component.Type.REACTION_WHEELS));
                componentRepository.save(new Component("RWA", "RW Advanced", Component.Type.REACTION_WHEELS));

                componentRepository.save(new Component("ELVT30", "LV-T30", Component.Type.ENGINES));
                componentRepository.save(new Component("ELVT45", "LVT-45", Component.Type.ENGINES));
                componentRepository.save(new Component("EREL10", "RE-L10", Component.Type.ENGINES));
                componentRepository.save(new Component("EREI5", "RE-I5", Component.Type.ENGINES));

                componentRepository.save(new Component("FFLT100", "FL-T100", Component.Type.FUEL_TANKS));
                componentRepository.save(new Component("FFLT200", "FL-T200", Component.Type.FUEL_TANKS));
                componentRepository.save(new Component("FFLT800", "FL-T400", Component.Type.FUEL_TANKS));
                componentRepository.save(new Component("FR12", "FR-12", Component.Type.FUEL_TANKS));
            }
        };
    }*/
}
