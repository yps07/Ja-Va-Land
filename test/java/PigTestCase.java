import static org.junit.Assert.assertEquals;

import io.github.JaVaLand.*;
import org.junit.*;

public class PigTestCase{
    private SmallPig pig;

    @Before
    public void setUp() throws Exception{
        pig = new SmallPig(true);            //health_points = 50
    }

    @Test
    public void testPigDamage(){
        System.out.println("\n--> Test Case: Testing pig damage");

        System.out.println("SmallPig health before damage: "+pig.getHealth());

        pig.takeDamage(5F);

        System.out.println("SmallPig health after damage: "+pig.getHealth());

        assertEquals(45F, pig.getHealth(), 0.00001);
    }
}
