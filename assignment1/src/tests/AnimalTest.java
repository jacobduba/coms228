import edu.iastate.coms228.hw1.Badger;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AnimalTest {
    @Test
    public void ageTest() {
        Badger b = new Badger(null, 0, 0, 4);
        assertEquals(4, b.myAge());
    }
}
