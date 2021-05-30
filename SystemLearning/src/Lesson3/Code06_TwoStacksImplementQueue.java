package Lesson3;

import java.util.Stack;

public class Code06_TwoStacksImplementQueue {
	/*
	*	用栈实现队列
	*
	* 	实现原理:
	* 		1. 使用push和pop两个栈处理, 压栈的时候数据进去push栈. 当出栈的时候, 把push栈的数据按顺序压入pop栈
	* 		这样一来第一个压入push栈的值会来到pop栈的栈顶, 即为所需的值
	* 		2. 当压入过pop栈之后, pop栈内元素全部出栈才可以再压入pop栈
	 */
	public static class TwoStacksQueue {
		private Stack<Integer> pushStack;
		private Stack<Integer> popStack;

		public TwoStacksQueue() {
			pushStack = new Stack<>();
			popStack = new Stack<>();
		}

		public void push(int x) {
			pushStack.push(x);
		}

		public int pop() {
			if (!popStack.isEmpty()) {
				return popStack.pop();
			}

			checkMove();

			if (popStack.isEmpty()) {
				return Integer.MIN_VALUE;
			}

			return popStack.pop();
		}

		public int peek() {
			if (!popStack.isEmpty()) {
				return popStack.peek();
			}

			checkMove();

			if (popStack.isEmpty()) {
				return Integer.MIN_VALUE;
			}

			return popStack.peek();
		}

		public boolean empty() {
			return pushStack.empty() && popStack.empty();
		}

		public void checkMove() {
			if (!popStack.empty()) {
				return;
			}

			while (!pushStack.empty()) {
				popStack.push(pushStack.pop());
			}
		}
	}
}
