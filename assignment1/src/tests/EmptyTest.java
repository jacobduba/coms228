import edu.iastate.coms228.hw1.Empty;
import edu.iastate.coms228.hw1.Plain;
import edu.iastate.coms228.hw1.State;
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
}
