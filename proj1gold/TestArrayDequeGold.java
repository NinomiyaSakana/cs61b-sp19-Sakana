import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold{

    @Test
    public void randomArrayDequeTest(){
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> sad1 = new ArrayDequeSolution<>();
        String message = "\n";


        //int i =1；Integer i= new Integer(1);(要把integer 当做一个类看)
        while(true){
            int rand=StdRandom.uniform(10000);
            if(rand<2500){
                sad.addFirst(rand);
                sad1.addFirst(rand);
                message=message+"addFirst("+rand+")\n";
            }else if(rand<5000){
                sad.addLast(rand);
                sad1.addLast(rand);
                message=message+"addLast("+rand+")\n";
            }else if(rand<7500){
                Integer actual=sad.removeFirst();
                Integer expected=sad1.removeFirst();
                message=message+"removeFirst()\n";
                assertEquals(message, expected, actual);
            }else if(!sad.isEmpty()){
                Integer actual=sad.removeLast();
                Integer expected=sad1.removeLast();
                message=message+"removeLast()\n";
                assertEquals(message, expected, actual);
            }
        }

        /*
        assertEquals数原型：
        assertEquals([String message],expected,actual)
        参数说明： message是个可选的消息，假如提供，将会在发生错误时报告这个消息。
        expected是期望值，通常都是用户指定的内容。
        actual是被测试的代码返回的实际值。
         */







    }




}
