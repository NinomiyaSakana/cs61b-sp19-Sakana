package def;

public class OffByN implements CharacterComparator{
	int a;
	public OffByN(int N){
		a=N;
	}
	
	@Override
	public boolean equalChars(char x, char y) {
		int diff=x-y;
		return diff==a||diff==-a; //这个是判断offBy1 所以是相差1 但是两个char相减是int
	}

}
