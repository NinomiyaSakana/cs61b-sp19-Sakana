package def;

public interface Deque<T> {
	
	void addFirst(T item);
	void addLast(T item) ;
	
	int size();
	void printDeque();
	T removeFirst();  //接口中所有都是public所以不需要写是吗
	T removeLast();
	T get(int index);
	
	default boolean isEmpty() {
		return size()==0;
	}
	
	
}
