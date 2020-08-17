package def;

public class OffByOne implements CharacterComparator{

	
	@Override
	public boolean equalChars(char x, char y) {
		int diff=x-y;
		return diff==1||diff==-1; //这个是判断offBy1 所以是相差1 但是两个char相减是int
	}
	
	
}
