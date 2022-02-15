import edu.iastate.hw1.Empty;
import edu.iastate.hw1.Plain;
import edu.iastate.hw1.State;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EmptyTest {
    Empty e;

    @Before
    public void setUp() {
        Plain p = new Plain(3);
        e = new Empty(p, 0, 0);
    }

    @Test
    public void whoTest() {
        assertEquals(e.who(), State.EMPTY);
    }

    @Test
    public void censusTest() {

    }
}
