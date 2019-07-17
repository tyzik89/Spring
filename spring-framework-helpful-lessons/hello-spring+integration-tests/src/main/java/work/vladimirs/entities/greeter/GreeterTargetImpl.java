package work.vladimirs.entities.greeter;

import org.springframework.stereotype.Service;
import work.vladimirs.entities.coin.Coin;

import javax.inject.Inject;

@Service
public class GreeterTargetImpl implements GreeterTarget{

    public static final String SPRING = "SPRING";
    public static final String WORLD = "WORLD";

    private Coin coin;

    @Inject
    public GreeterTargetImpl(final Coin newCoin) {
        this.coin = newCoin;
    }

    public final String get() {
        return coin.toss() ? WORLD : SPRING;
    }
}
