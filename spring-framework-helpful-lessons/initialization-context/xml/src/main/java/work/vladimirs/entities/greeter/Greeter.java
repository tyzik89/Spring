package work.vladimirs.entities.greeter;

/**
 * Greeter service
 */
public class Greeter {

    /**
     * Кого мы приветсвуем?
     */
    private GreeterTarget greeterTarget;

    public Greeter(GreeterTarget newTarget) {
        this.greeterTarget = newTarget;
    }

    public final String greet() {
        return "Hello " + greeterTarget.get();
    }
}
