import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static org.junit.Assert.assertNotEquals;

public class HorribleSteve {
    public static void main(String [] args) throws Exception {
        int i = 0;
        for (int j = 0; i < 500; ++i, ++j) {
            if (!Flik.isSameNumber(i, j)) {
                throw new Exception(
                        String.format("i:%d not same as j:%d ??", i, j));
            }
        }
        System.out.println("i is " + i);
    }


    /**
     * Write JUnit tests for the Flik library.
     */
    @Test
    public void testFlik(){
        int a=100;
        int b=2;
        //String skr="Arashi";

        assertEquals(false, Flik.isSameNumber(a,b));
        //assertNotEquals(false, Flik.isSameNumber(a,skr));

    }
}
