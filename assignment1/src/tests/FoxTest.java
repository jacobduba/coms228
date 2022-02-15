import edu.iastate.hw1.Fox;
import edu.iastate.hw1.Plain;
import edu.iastate.hw1.State;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FoxTest {
    Fox f;

    @Before
    public void setUp() {
        Plain p = new Plain(3);
        f = new Fox(p, 0, 0, 0);
    }

    @Test
    public void whoTest() {
        assertEquals(f.who(), State.FOX);
    }
}
