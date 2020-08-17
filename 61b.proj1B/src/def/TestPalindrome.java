package def;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
    
    
    /*Add at least one test to TestPalindrome that tests the isPalindrome method.*/
    @Test
    public void testIsPalindrome() {
    	assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("AAAaAAA"));
        assertTrue(palindrome.isPalindrome("ABBA"));
        assertFalse(palindrome.isPalindrome("Aa"));
        assertFalse(palindrome.isPalindrome("ACDC"));
        assertFalse(palindrome.isPalindrome("yang"));
    	assertFalse(palindrome.isPalindrome("cat"));
    	assertFalse(palindrome.isPalindrome("caa"));
    	//对的就是assertTrue，不对就是 assertFalse
    }

    @Test
    public void testIsPalindromeCC() {
    	//因为offByOne是继承了CharacterComparator 所以可以新建 但是其实并没有定义class
    	//没有构造函数
    	 OffByOne offByOne = new OffByOne();
         assertTrue(palindrome.isPalindrome("", offByOne));
         assertTrue(palindrome.isPalindrome("a", offByOne));
         assertTrue(palindrome.isPalindrome("flake", offByOne));
         assertTrue(palindrome.isPalindrome("zyzy", offByOne));
         assertTrue(palindrome.isPalindrome("yyxz", offByOne));
         assertTrue(palindrome.isPalindrome("yyyxz", offByOne));
         assertFalse(palindrome.isPalindrome("aa", offByOne));
         assertFalse(palindrome.isPalindrome("xyz", offByOne));
         assertFalse(palindrome.isPalindrome("aa", offByOne));
         assertFalse(palindrome.isPalindrome("zxzx", offByOne));
    }

	
    
}     