import edu.iastate.coms228.hw1.Badger;
import edu.iastate.coms228.hw1.Plain;
import edu.iastate.coms228.hw1.State;
import edu.iastate.coms228.hw1.Wildlife;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class BadgerTest {
    @Test
    public void badgerNextTest() throws FileNotFoundException {
        Plain oldP = new Plain("badger-test.txt");
        Plain newP = new Plain(oldP.getWidth());
        Wildlife.updatePlain(oldP, newP);
        assertEquals(newP.grid[1][1].who(), State.BADGER);
        assertEquals(((Badger) newP.grid[1][1]).myAge(), 3);
    }
}
