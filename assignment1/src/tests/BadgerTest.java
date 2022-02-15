import edu.iastate.coms228.hw1.Badger;
import edu.iastate.coms228.hw1.Plain;
import edu.iastate.coms228.hw1.State;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BadgerTest {
    Badger b;

    @Before
    public void setUp() {
        Plain p = new Plain(3);
        b = new Badger(p, 0, 0, 0);
    }

    @Test
    public void whoTest() {
        assertEquals(b.who(), State.BADGER);
    }
}
