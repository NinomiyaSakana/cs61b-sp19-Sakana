package dafault;
/**
 * ArrayDeque 是一个用数组实现的双端队列 Deque
 * 为满足可以同时在数组两端插入或删除元素的需求，
 * 该数组还必须是循环的，即循环数组（circular array），
 * 也就是说数组的任何一点都可能被看作起点或者终点，ArrayDeque 是非线程安全的，
 * 当多个线程同时使用的时候需要手动同步，此外该容器不允许放 null 元素，
 * 同时与 ArrayList 和 LinkedList 不同的是没有索引位置的概念，不能根据索引位置进行操作。
 * 
 * array与linkedlist双端队列的区别
 * ArrayDeque 和 LinkedList 都实现了 Deque 接口，
 * 如果只需要 Deque 接口且从两端进行操作则一般来说 ArrayDeque 效率更高一些，
 * 如果同时需要根据索引位置进行操作或经常需要在中间进行插入和删除操作
 * 则应该优先选 LinkedList 效率高些，
 * 因为 ArrayDeque 实现是循环数组结构
 * （即一个动态扩容数组，默认容量 16，然后通过一个 head 和 tail 索引记录首尾相连），
 * 而 LinkedList 是基于双向链表结构的。
 * 
 * @author qubeier
 * @param <T>
 */

public class ArrayDeque<T> {
	private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private static final int INIT_CAPACITY = 8;
    private static final int RFACTOR = 2;
    private static final double MIN_USAGE_RATIO = 0.25;
	
    
    /*Creates an empty array Deque. The starting size of your array should be 8. */
	public ArrayDeque() {
		items=(T[])new Object[INIT_CAPACITY];
		size=0;
		nextFirst=4;
		nextLast=5;
	}
	
	//这个就是复制粘贴的
	//目前没有搞懂other的意义
	//但是别的都可以写
	public ArrayDeque(ArrayDeque other) {
		items = (T[]) new Object[INIT_CAPACITY];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
	}
	
	//这两个method有点不明白是用来干嘛的
	//直接照着写的
	//但其实就是i--得意思
	 private int minusOne(int index) {
		 return (index - 1 + items.length) % items.length;  
		 //为了防止超出长度？因为是个环形链表
	    }

	 //i++
	 private int plusOne(int index) {
		 return (index + 1) % items.length;
	    }
	 
	 //重构array增加容量 基本套路
	 private void resize(int capacity) {
		 T[] newArray= (T[])new Object[capacity];
		 
		 int curr=plusOne(nextFirst);
		 for(int i=1;i<size;i++) {
			 newArray[i]=items[curr];
			 curr=plusOne(curr); //不知道为何要用这样的方式计算索引
			 
		 }
		 items=newArray; //将新的array赋给items
		 nextFirst=capacity-1;  
		 nextLast=size;
				 
	 }
	
	 
	
	public void addFirst(T item) {
		if(size==items.length) {
			resize(size*RFACTOR); //边界条件
		}
		items[nextLast]=item;
		size++;
		nextFirst=minusOne(nextFirst);
	}
	
	public void addLast(T item) {
		if(size==items.length) {
			resize(size*RFACTOR);
		}
		items[nextLast]=item; //赋值给last
		size++;
		nextLast=plusOne(nextLast);
	}
		
	public boolean isEmpty() {
		return size==0;
	}
	
	public int size() {
		return size;
	}
	
	public void printDeque() {
		int i=plusOne(nextFirst);
		
		while(i!=nextLast) {
			System.out.println(items[i]+"");
			i=plusOne(i); //i++
		}
		System.out.println();
	}
	
	public T removeFirst() {
		if(size==0) {
			return null;
		}
		int first=plusOne(nextFirst); //nextFirst++
		T firstItem=items[first];
		items[first]=null;
		nextFirst=first;
		size--;
		
		
		if (items.length >= 16 && size < items.length * MIN_USAGE_RATIO) {
            resize(items.length / 2);
        }
		return firstItem;
		
	}
	
	public T removeLast() {
		if(size==0) {
			return null;
		}
		int last=minusOne(nextLast);
		T lastItem=items[last];
		items[last]=null;
		nextLast=last;
		size--;
		
		if (items.length >= 16 && size < items.length * MIN_USAGE_RATIO) {
            resize(items.length / 2);
        }
		return lastItem;
		
	} 
	
	public T get(int index) {
		if(index>size) {
			return null;
		}
		
		index = (plusOne(nextFirst) + index) % items.length;
		return items[index];
	}
	
	/**
	 * 递归解法
	 * @param index
	 * @return
	 */
	public T getRecursive(int index) {
		if(index>size) {
			return null;
		}
		
		if(index==0) {
			return items[plusOne(nextFirst)];
		}else {
			index = (plusOne(nextFirst) + index) % items.length;
			return get(index-1);
		}
		
	}

	
	/**
	 * 您不得在实现中使用Java内置的LinkedList数据结构（或其中的任何数据结构java.util.*）。
	 * 对于ArrayDeque，请考虑完全不调整大小
	 * 了解了Sentinel节点后，生活变得更加轻松。
	 * 考虑一个辅助函数来完成一些小任务，例如计算数组索引。
	 * 例如，在的实现中ArrayDeque，我编写了一个函数int minusOne(int index)，该函数立即在给定索引之前“计算”索引。
	 * Consider a helper function to do little tasks like compute array indices. 
	 * For example, in my implementation of ArrayDeque, 
	 * I wrote a function called int minusOne(int index) 
	 * that computed the index immediately “before” a given index.
	 * create an array of generic objects:T[] a = (T[]) new Object[1000];
	 * 
	 */
}
