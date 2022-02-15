import edu.iastate.coms228.hw1.Plain;
import edu.iastate.coms228.hw1.Wildlife;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class WildlifeTest {
    @Test
    public void updatePlainTest() throws FileNotFoundException {
        String solution = String.join("\n",
                "R1 R1 B1 ",
                "G  G  G  ",
                "G  G  G  ",
                "");
        Plain oldP = new Plain("given-example-1.txt");
        Plain newP = new Plain(3);
        Wildlife.updatePlain(oldP, newP);
        assertEquals(solution, newP.toString());
    }
}
