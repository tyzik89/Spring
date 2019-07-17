package work.vladimirs.entities.coin;

import java.util.Random;

public class StubRandom extends Random {

    private boolean constantResult;

    public final void setConstantResult(final boolean newResult) {
        this.constantResult = newResult;
    }

    @Override
    public boolean nextBoolean() {
        return constantResult;
    }
}
