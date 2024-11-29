import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner{
    public static void main(String[] args){
        Result result = JUnitCore.runClasses(ReadFileTestCase.class);
        Result result1 = JUnitCore.runClasses(WriteFileTestCase.class);
        Result result2 = JUnitCore.runClasses(PigTestCase.class);
        Result result3 = JUnitCore.runClasses(BlockTestCase.class);

        System.out.println("\n---------------------------Summary---------------------------\n");

        for(Failure failure: result.getFailures()){
            System.out.println(failure.toString()+"\n");
        }

        if(result.wasSuccessful()){
            System.out.println("testRead() --> Test was successful\n");
        }

        else{
            System.out.println("testRead() --> Test was unsuccessful\n");
        }

        //

        for(Failure failure: result1.getFailures()){
            System.out.println(failure.toString()+"\n");
        }

        if(result1.wasSuccessful()){
            System.out.println("testWrite() --> Test was successful\n");
        }

        else{
            System.out.println("testWrite() --> Test was unsuccessful\n");
        }

        //

        for(Failure failure: result2.getFailures()){
            System.out.println(failure.toString()+"\n");
        }

        if(result2.wasSuccessful()){
            System.out.println("testPigDamage() --> Test was successful\n");
        }

        else{
            System.out.println("testPigDamage() --> Test was unsuccessful\n");
        }

        for(Failure failure: result3.getFailures()){
            System.out.println(failure.toString()+"\n");
        }

        if(result3.wasSuccessful()){
            System.out.println("testBlockDamage() --> Test was successful\n");
        }

        else{
            System.out.println("testBlockDamage() --> Test was unsuccessful\n");
        }
    }
}
