import edu.iastate.coms228.hw1.*;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class RabbitTest {
    @Test
    public void rabbitNextTest() throws FileNotFoundException {
        Plain oldP = new Plain("rabbit-test.txt");
        Plain newP = new Plain(oldP.getWidth());
        Wildlife.updatePlain(oldP, newP);
        assertEquals(newP.grid[1][1].who(), State.EMPTY);
    }
}
