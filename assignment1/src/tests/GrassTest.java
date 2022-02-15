import edu.iastate.hw1.Grass;
import edu.iastate.hw1.Plain;
import edu.iastate.hw1.State;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GrassTest {
    Grass g;

    @Before
    public void setUp() {
        Plain p = new Plain(3);
        g = new Grass(p, 0, 0);
    }

    @Test
    public void whoTest() {
        assertEquals(g.who(), State.GRASS);
    }
}
