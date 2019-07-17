package work.vladimirs.entities.coin;

import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Random;

@Service
public class CoinImpl implements Coin {

    private Random random;

    @Inject
    public CoinImpl(final Random random) {
        this.random = random;
    }

    public final boolean toss() {
        return random.nextBoolean();
    }
}
