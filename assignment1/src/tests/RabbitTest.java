import edu.iastate.coms228.hw1.Plain;
import edu.iastate.coms228.hw1.Rabbit;
import edu.iastate.coms228.hw1.State;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RabbitTest {
    Rabbit r;

    @Before
    public void setUp() {
        Plain p = new Plain(3);
        r = new Rabbit(p, 0, 0, 0);
    }

    @Test
    public void whoTest() {
        assertEquals(r.who(), State.RABBIT);
    }
}
