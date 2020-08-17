package def;

public class Palindrome {
	
	public Deque<Character> wordToDeque(String word){
		//先创建一个deque 用linkedlisted的方法 但是其中的元素是character
		LinkedListDeque<Character> deque=new LinkedListDeque<>();
		//将string中的每个元素添加到deque中去
		for(int i=0;i<word.length();i++) {
			deque.addLast(word.charAt(i));
		}
		return deque;
}
	
	
	/*The isPalindrome method should return true if the given word is a palindrome, and false otherwise.*/
	public boolean isPalindrome(String word) {
		Deque<Character> deque= wordToDeque(word);
		while(deque.size()>1) { //因为是list采用size array用length
			if(deque.removeFirst()!=deque.removeLast()) { //就是while长度大于1的时候不断地移走前后，直到只剩一个数
				return false;
			}
		}
		return true;
	}
	
	
	/*
	 * The method will return true 
	 * if the word is a palindrome according to the character comparison test 
	 * provided by the CharacterComparator passed in as argument cc.
	 */
	public boolean isPalindrome(String word, CharacterComparator cc) {
		Deque<Character> deque = wordToDeque(word);
        while (deque.size() > 1) {
            Character first = deque.removeFirst();
            Character last = deque.removeLast();
            if (!(cc.equalChars(first, last))) {
                return false;
            }
        }
        return true;
    }


}
	
	
	
