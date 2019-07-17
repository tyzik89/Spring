package work.vladimirs.entities.greeter;

import work.vladimirs.entities.coin.Coin;

public class GreeterTargetImpl implements GreeterTarget{

    public static final String SPRING = "SPRING";
    public static final String WORLD = "WORLD";

    private Coin coin;

    public GreeterTargetImpl(final Coin newCoin) {
        this.coin = newCoin;
    }

    public final String get() {
        return coin.toss() ? WORLD : SPRING;
    }
}
