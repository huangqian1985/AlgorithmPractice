package Lesson3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * 	使用数组实现队列和栈
 */
public class Code04_RingArray {

	/*
	 * 	使用数组实现栈
	 */
	public static class MyStack {
		private int index;
		private int[] arr;

		public MyStack(int size) {
			arr = new int[size];
			index = 0;
		}

		public void push(int value) {
			if (index >= arr.length) {
				System.out.println("超过最大长度");
				return;
			}
			arr[index++] = value;
		}

		public int pop() {
			if (index <= 0) {
				System.out.println("堆栈为空");
				return Integer.MIN_VALUE;
			}
			return arr[--index];
		}
	}

	/*
	 * 	使用数组实现队列
	 */
	public static class MyQueue {
		private int beginIndex;
		private int endIndex;
		private int[] arr;
		private int curSize;

		public MyQueue(int size) {
			arr = new int[size];
			beginIndex = endIndex = curSize = 0;
		}

		public void push( int value ) {
			if (curSize == arr.length) {
				System.out.println("队列长度已满");
				return;
			}

			arr[endIndex] = value;
			endIndex = nextIndex(endIndex);
			curSize++;
		}

		public int poll() {
			if (curSize == 0) {
				System.out.println("队列已空");
				return Integer.MIN_VALUE;
			}
			curSize--;
			int value = arr[beginIndex];
			beginIndex = nextIndex(beginIndex);
			return value;
		}

		public boolean isEmpty() {
			return curSize == 0;
		}

		public int nextIndex(int index) {
			return ( index < arr.length - 1 ) ? index + 1 : 0;
		}
	}

	public static boolean isEqual(Integer o1, Integer o2) {
		if (o1 == null && o2 != null) {
			return false;
		}
		if (o1 != null && o2 == null) {
			return false;
		}
		if (o1 == null && o2 == null) {
			return true;
		}
		return o1.equals(o2);
	}

	public static void main(String[] args) {
		int oneTestDataNum = 100;
		int value = 10000;
		int testTimes = 100000;
		for (int i = 0; i < testTimes; i++) {
			MyStack myStack = new MyStack(oneTestDataNum);
			MyQueue myQueue = new MyQueue(oneTestDataNum);
			Stack<Integer> stack = new Stack<>();
			Queue<Integer> queue = new LinkedList<>();
			for (int j = 0; j < oneTestDataNum; j++) {
				int nums = (int) (Math.random() * value);
				if (stack.isEmpty()) {
					myStack.push(nums);
					stack.push(nums);
				} else {
					if (Math.random() < 0.5) {
						myStack.push(nums);
						stack.push(nums);
					} else {
						if (!isEqual(myStack.pop(), stack.pop())) {
							System.out.println("oops!");
						}
					}
				}

				int numq = (int) (Math.random() * value);
				if (queue.isEmpty()) {
					myQueue.push(numq);
					queue.offer(numq);
				} else {
					if (Math.random() < 0.5) {
						myQueue.push(numq);
						queue.offer(numq);
					} else {
						if (!isEqual(myQueue.poll(), queue.poll())) {
							System.out.println("oops!");
						}
					}
				}
			}
		}
		System.out.println("finish!");
	}
}
