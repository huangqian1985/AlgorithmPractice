package Lesson3;

import java.util.Stack;

public class Code05_GetMinStack {
/*
*	实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能
*	1）pop、push、getMin操作的时间复杂度都是 O(1)。
*	2）设计的栈类型可以使用现成的栈结构。
*/
	public static class GetMinStack {
		private Stack<Integer> dataStack;
		// 增加一个最小值栈
		private Stack<Integer> minStack;

		public GetMinStack() {
			dataStack = new Stack();
			minStack = new Stack();
		}

		/* 	Point在于, 在把 value 压入 dataStack 的同时, 也压入 minStack
		 *	但有一个前提, 是 value 小于 minStack 的栈顶元素, 否则, 将 minStack 栈顶的值重复压栈
		 * 	这样永远保证 minStack 的栈顶元素是栈中元素的最小值
		 * 	并出栈的时候, dataStack 和 minStack 同步出栈
		*/
		public void push(int value) {
			if (dataStack.isEmpty()) {
				dataStack.push(value);
				minStack.push(value);
			} else {
				int min = getMin();
				if (value < min) {
					dataStack.push(value);
					minStack.push(value);
				} else {
					dataStack.push(value);
					minStack.push(min);
				}
			}
		}

		public Integer pop() {
			if (dataStack.isEmpty()) {
				System.out.println("Stack is empty!");
				return Integer.MIN_VALUE;
			}
			minStack.pop();
			return dataStack.pop();
		}

		public Integer getMin() {
			return minStack.peek();
		}

		public boolean isEmpty() { return dataStack.isEmpty(); }
	}

	public static class TestGetMinStack {
		private Stack<Integer> stack;

		public TestGetMinStack() {
			stack = new Stack<>();
		}

		public void push(Integer value) { stack.push(value); }

		public Integer pop() { return stack.pop(); }

		public boolean isEmpty() { return stack.isEmpty(); }

		public Integer getMin() {
			int min = Integer.MAX_VALUE;
			for (Integer i : stack) {
				if (i < min) {
					min = i;
				}
			}
			return min;
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
			GetMinStack getMinStack = new GetMinStack();
			TestGetMinStack testGetMinStack = new TestGetMinStack();

			for (int j = 0; j < oneTestDataNum; j++) {
				int nums = (int) (Math.random() * value);
				if (getMinStack.isEmpty()) {
					getMinStack.push(nums);
					testGetMinStack.push(nums);

					if (!getMinStack.getMin().equals(testGetMinStack.getMin())) {
						System.out.println("oops!");
					}
				} else {
					if (Math.random() < 0.5) {
						getMinStack.push(nums);
						testGetMinStack.push(nums);

						if (!getMinStack.getMin().equals(testGetMinStack.getMin())) {
							System.out.println("oops!");
						}
					} else {
						if (!isEqual(getMinStack.pop(), testGetMinStack.pop())) {
							System.out.println("oops!");
						}

						if (!getMinStack.isEmpty()) {
							if (!getMinStack.getMin().equals(testGetMinStack.getMin())) {
								System.out.println("oops!");
							}
						}
					}
				}
			}
		}
		System.out.println("finish!");
	}
}
