package work.vladimirs.entities.coin;

import java.util.Random;

public class CoinImpl implements Coin {

    private Random random;

    public CoinImpl(final Random random) {
        this.random = random;
    }

    public final boolean toss() {
        return random.nextBoolean();
    }
}
