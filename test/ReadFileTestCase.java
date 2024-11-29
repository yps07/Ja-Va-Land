import static org.junit.Assert.assertEquals;

import io.github.JaVaLand.FileHandler;
import io.github.JaVaLand.GameState;
import org.junit.*;
import java.util.ArrayList;

public class ReadFileTestCase {
    private FileHandler<GameState> file;

    @Before
    public void setUp() throws Exception{
        file = new FileHandler<>("assets/files/saved_games_1.ser"); //does not exist
    }

    @Test
    public void testRead(){
        System.out.println("\n--> Test Case: Reading .ser file");
        int flag = 0;

        try{
            ArrayList<GameState> arr = file.get_content();
        }

        catch(Exception e){
           flag = 1;
        }

        assertEquals(1, flag);
    }
}
