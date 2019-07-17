package work.vladimirs.entities.coin;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoinTest {

    @Test
    public void testToss() {
        /*
          Prepare the mock
         */
        StubRandom stubRandom = new StubRandom();

        /*
          Prepare the object
         */
        Coin testedCoin = new CoinImpl(stubRandom);

        stubRandom.setConstantResult(true);
        assertTrue(testedCoin.toss());

        stubRandom.setConstantResult(false);
        assertFalse(testedCoin.toss());
    }
}