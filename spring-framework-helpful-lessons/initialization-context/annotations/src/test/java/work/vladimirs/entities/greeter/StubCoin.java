package work.vladimirs.entities.greeter;

import work.vladimirs.entities.coin.Coin;

public class StubCoin implements Coin {

    private boolean constantResult;

    public final void setConstantResult(final boolean newResult) {
        this.constantResult = newResult;
    }

    public boolean toss() {
        return constantResult;
    }
}
