package work.vladimirs.entities.greeter;

/**
 * Определяем, кого мы будем приветсвовать, на основе броска монеты
 */
public interface GreeterTarget {

    /**
     * Selects greeting target tossing a coin.
     * @return "World" or "Spring".
     */
    String get();
}
