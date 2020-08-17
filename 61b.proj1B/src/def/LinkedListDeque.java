package def;

public class LinkedListDeque<T> implements Deque<T> {
	private Node sentinel;
    private int size;
    
    private class Node{
    	Node prev;
    	T item;
    	Node next;
    	
    	Node(Node p, T i, Node n){
    		prev=p;
    		item=i;
    		next=n;
    	}
    }
    
    /** 创建了一一个空的deque */
	public LinkedListDeque() {
		sentinel=new Node(null,null,null);
		sentinel.prev=sentinel;
		sentinel.next=sentinel;
		size=0;
	}
	
	/** Creates a deep copy of other. */
	//深拷贝和浅拷贝
	public LinkedListDeque(LinkedListDeque<T> other) {
		sentinel=new Node(null,null,null);
		sentinel.prev=sentinel;
		sentinel.next=sentinel;
		size=0;
		
		for(int i=0;i<other.size();i++) {
			addLast(other.get(i));
		}
	}
	
	/** Adds an item of type T to the front of the deque. */
	@Override
	public void addFirst(T item) {
		Node node=new Node(sentinel,item,sentinel);
		sentinel.next.prev=node; //将null的前面一个变成node
		sentinel.next=node; //将sentinel的后面一个变成node 连起来
		size++;
	}
	
	@Override
	public void addLast(T item) {
		Node node=new Node(sentinel.prev,item,sentinel);
		sentinel.prev.next=node;
		sentinel.prev=node;
		size++;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	
	/** Prints the items in the deque from first to last, separated by a space.
     *  Once all the items have been printed, print out a new line.
     */
	//从头到尾打印双端队列中的项目，以空格隔开。一旦所有项目都已打印，请打印出新行。这个简单
	@Override
	public void printDeque() {
		Node current=sentinel.next;
		if(current!=sentinel) { //不等于最后一个sentinel
			System.out.println(current.item+"");
			current=current.next;
		}
		System.out.println();
	}
	
	
	/**
	 * remove第一个item并且return出来
	 * @return
	 */
	@Override
	public T removeFirst() {
		if(isEmpty()) { //边界条件 是否null
			return null;
		}
		T first=sentinel.next.item;
		//处理一些节点问题
		sentinel.next=sentinel.next.next;
		sentinel.next.prev=sentinel; //为什么两个都要啊 添加要两次的话可以理解。。。
		size--;
		return first;
	}
	
	//跟上面的操作类似
	@Override
	public T removeLast() {
		if(isEmpty()) { //边界条件 是否null
			return null;
		}
		T last=sentinel.prev.item;
		sentinel.prev=sentinel.prev.prev;
		sentinel.prev.next=sentinel;
		size--;
		return last;
	}
	
	
	//获取某一特定index下的item值 简单
	@Override
	public T get(int index) {
		
		if(index>size) {//边界条件
			return null;
		}
		Node curr=sentinel.next; //是第二个节点
		while(index>0) {
			curr=curr.next;
			index--;
		}
		return curr.item;
	}
	
	/** Same as get, but uses recursion. 递归*/
	public T getRecursive(int index) {
		if(index>size) {//边界条件
			return null;
		}
		Node curr=sentinel.next;
		if(index==0) {
			return curr.item;
		}else {
			return getRecursive(index-1);
		}
		
	}
	
	
	
	/**
	 * what to do
	 * (1)add and remove operations must not involve any looping or recursion. 
	 * A single such operation must take “constant time”
	 * (2)get must use iteration, not recursion.
	 * (3)size must take constant time.
	 * add并且remove操作不得涉及任何循环或递归。单个此类操作必须花费“恒定时间”，即执行时间不应取决于双端队列的大小。
	 * get 必须使用迭代，而不是递归。
	 * size 必须花费固定的时间。(??什么意思)
	 */

}
