import edu.iastate.coms228.hw1.Plain;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class PlainTest {
    @Test
    public void setWidthThroughConstructorTest() {
        Plain p = new Plain(6);
        assertEquals(p.getWidth(), 6);
    }

    @Test
    public void setUpThroughFileConstructorTest() throws FileNotFoundException {
        Plain p = new Plain("public1-3x3.txt");
        String expected = String.join("\n",
                "G  B0 F0 ",
                "F0 F0 R0 ",
                "F0 E  G  \n");
        assertEquals(expected, p.toString());
    }

    @Test
    public void writeTest() throws FileNotFoundException {
        Plain p = new Plain("public1-3x3.txt");
        p.write("testing-given-example-1.txt");
        Plain written = new Plain("testing-given-example-1.txt");
        assertEquals(p.toString(), written.toString());
    }
}
