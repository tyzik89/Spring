package work.vladimirs.entities.greeter;

import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Greeter service
 */
@Service
public class Greeter {

    /**
     * Кого мы приветсвуем?
     */
    private GreeterTarget greeterTarget;

    @Inject
    public Greeter(GreeterTarget greeterTarget) {
        this.greeterTarget = greeterTarget;
    }

    public final String greet() {
        return "Hello " + greeterTarget.get();
    }
}
