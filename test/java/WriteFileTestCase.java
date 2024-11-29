import static org.junit.Assert.assertEquals;

import io.github.JaVaLand.FileHandler;
import io.github.JaVaLand.GameState;
import org.junit.*;
import java.util.ArrayList;

public class WriteFileTestCase {
    private FileHandler<GameState> file;
    private GameState context;

    @Before
    public void setUp() throws Exception{
        file = new FileHandler<>("assets/files/saved_games.ser"); //does not exist
        context = file.get_content().get(0);
    }

    @Test
    public void testWrite(){
        System.out.println("\n--> Test Case: Writing to .ser file");
        int flag = 0;

        try{
            file.add_content(new ArrayList<>());
        }

        catch(Exception e){
            flag = 1;
        }

        assertEquals(0, flag);
    }

    @After
    public void tearDown(){
        ArrayList<GameState> arr = new ArrayList<>();

        arr.add(context);

        try {
            file.add_content(arr);
        }

        catch (Exception e){
            ;
        }
    }
}
