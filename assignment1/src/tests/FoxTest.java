import edu.iastate.coms228.hw1.Fox;
import edu.iastate.coms228.hw1.Plain;
import edu.iastate.coms228.hw1.State;
import edu.iastate.coms228.hw1.Wildlife;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class FoxTest {
    @Test
    public void foxNextTest() throws FileNotFoundException {
        Plain oldP = new Plain("fox-test.txt");
        Plain newP = new Plain(oldP.getWidth());
        Wildlife.updatePlain(oldP, newP);
        assertEquals(newP.grid[1][1].who(), State.EMPTY);
    }
}
