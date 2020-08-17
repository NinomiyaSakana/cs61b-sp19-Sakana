package def;

import org.junit.Test; //因为我实在eclipes中做的，所以需要先导入junit
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    public void testOffByOne() {
    	assertTrue(offByOne.equalChars('1','2'));
    	assertTrue(offByOne.equalChars('A','B'));
    	assertTrue(offByOne.equalChars('@','A'));
    	
    	assertFalse(offByOne.equalChars('A','C'));
    	assertFalse(offByOne.equalChars('3','8'));
    	assertFalse(offByOne.equalChars('?','!'));
    	assertFalse(offByOne.equalChars('0',';'));
        
    	//对的就是assertTrue，不对就是 assertFalse
    }
} 