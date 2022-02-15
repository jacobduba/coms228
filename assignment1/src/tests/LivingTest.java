import edu.iastate.coms228.hw1.Badger;
import edu.iastate.coms228.hw1.Living;
import edu.iastate.coms228.hw1.State;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LivingTest {
    Living l;

    @Before
    public void setUp() {
        l = new Badger(null, 0, 0, 0);
    }

    @Test
    public void whoTest() {
        assertEquals(l.who(), State.BADGER);
    }

}
