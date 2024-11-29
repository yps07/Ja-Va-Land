import static org.junit.Assert.assertEquals;

import io.github.JaVaLand.*;
import org.junit.*;

public class BlockTestCase{
    private Wood wood;

    @Before
    public void setUp() throws Exception{
        wood = new Wood();            //durability = 30
    }

    @Test
    public void testBlockDamage(){
        System.out.println("\n--> Test Case: Testing block damage");

        System.out.println("Wood durability before damage: "+wood.getHealth());

        wood.takeDamage(5F);

        System.out.println("Wood durability after damage: "+wood.getHealth());

        assertEquals(25F, wood.getHealth(), 0.00001);
    }
}
