package Lesson3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static Common.NodeFunction.*;

/**
 * 	使用双向链表实现队列和栈
 */
public class Code03_DoubleEndsQueueToStackAndQueue {

	public static class DoubleEndsQueue<T> {
		public DoubleNodeT<T> head = null;
		public DoubleNodeT<T> tail = null;

		public void addFromHead(T value) {
			DoubleNodeT<T> cur = new DoubleNodeT<>(value);
			if (head == null) {
				tail = cur;
			} else {
				head.pre = cur;
				cur.next = head;
			}
			head = cur;
		}

		public void addFromBottom(T value) {
			DoubleNodeT<T> cur = new DoubleNodeT<>(value);
			if (head == null) {
				head = cur;
			} else {
				// 新增节点的next一定是null, 即tail.next,
				// 而cur的next默认为 null, 所以可以不显式赋值
				cur.next = tail.next;
				//
				cur.pre = tail;
				tail.next = cur;
			}
			tail = cur;
		}

		public T popFromHead() {
			if (head == null) {
				return null;
			}
			DoubleNodeT<T> cur = head;
			if (head == tail) {
				head = null;
				tail = null;
			} else {
				head = cur.next;
				head.pre = null;
				cur.next = null;
			}
			return cur.value;
		}

		public T popFromBottom() {
			if (head == null) {
				return null;
			}
			DoubleNodeT<T> cur = head;
			if (head == tail) {
				head = null;
				tail = null;
			} else {
				tail = cur.pre;
				tail.next = null;
				cur.pre = null;
			}
			return cur.value;
		}

		public boolean isEmpty() {
			return head == null;
		}
	}

	/*
	 *		使用双向链表实现队列
	 */
	public static class MyQueue<T> {
		private DoubleEndsQueue<T> queue;

		public MyQueue() {
			queue = new DoubleEndsQueue<>();
		}

		public void push(T value) {
			queue.addFromBottom(value);
		}

		public T poll() {
			return queue.popFromHead();
		}

		public boolean isEmpty() { return queue.isEmpty(); }
	}

	/*
	 *		使用双向链表实现栈
	 */
	public static class MyStack<T> {
		private DoubleEndsQueue<T> queue;

		public MyStack() {
			queue = new DoubleEndsQueue<>();
		}

		public void push(T value) {
			queue.addFromHead(value);
		}

		public T pop() {
			return queue.popFromHead();
		}

		public boolean isEmpty() { return queue.isEmpty(); }
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
			MyStack<Integer> myStack = new MyStack<>();
			MyQueue<Integer> myQueue = new MyQueue<>();
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
				if (stack.isEmpty()) {
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
