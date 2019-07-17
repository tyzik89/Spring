package work.vladimirs.entities.greeter;

import org.junit.Test;

import static org.junit.Assert.*;

public class GreeterTargetTest {

    @Test
    public void testGet() {
        /*
          Prepare the mock
         */
        StubCoin stubCoin = new StubCoin();

        /*
          Prepare the object
         */
        GreeterTarget greeterTarget = new GreeterTargetImpl(stubCoin);

        stubCoin.setConstantResult(true);
        assertEquals(GreeterTargetImpl.WORLD, greeterTarget.get());

        stubCoin.setConstantResult(false);
        assertEquals(GreeterTargetImpl.SPRING, greeterTarget.get());
    }
}