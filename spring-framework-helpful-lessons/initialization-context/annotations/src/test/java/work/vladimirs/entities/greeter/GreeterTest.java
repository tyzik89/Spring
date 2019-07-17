package work.vladimirs.entities.greeter;

import org.junit.Test;

import static org.junit.Assert.*;

public class GreeterTest {

    @Test
    public void testGreet() {
        /*
          Prepate the mock
         */
        GreeterTarget stubGreeterTarget = new StubGreeterTarget();

        /* Prepare the object */
        Greeter greeter = new Greeter(stubGreeterTarget);

        assertEquals("Hello TEST", greeter.greet());
    }
}