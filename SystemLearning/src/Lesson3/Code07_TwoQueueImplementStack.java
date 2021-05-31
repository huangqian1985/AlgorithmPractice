package Lesson3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code07_TwoQueueImplementStack {

	/*
	 *	用队列实现栈
	 *
	 * 	实现原理:
	 * 		1. 使用两个队列, A和B, 压入数据的时候首先压入A, 当弹出数据的时候, 将A队列中除了最后一个元素压入B队列, 返回A队列最后一个元素
	 */
	static class MyStack {

		Queue<Integer> queueA;
		Queue<Integer> queueB;

		public MyStack() {
			queueA = new LinkedList<>();
			queueB = new LinkedList<>();
		}

		public void push(int x) {
			queueA.offer(x);
		}

		public int pop() {
			// 将A中除了最后一位的元素压入B
			while (queueA.size() > 1) {
				queueB.offer(queueA.poll());
			}
			// 取出A的最后一个元素作为返回
			int result = queueA.poll();
			// 将队列交换
			Queue<Integer> tmp = queueA;
			queueA = queueB;
			queueB = tmp;

			return result;
		}

		public int top() {
			// 将A中除了最后一位的元素压入B
			while (queueA.size() > 1) {
				queueB.offer(queueA.poll());
			}
			// 取出A的最后一个元素作为返回
			int result = queueA.poll();
			// 与pop唯一区别, 获得到结果后重新压入
			queueB.offer(result);
			// 将队列交换
			Queue<Integer> tmp = queueA;
			queueA = queueB;
			queueB = tmp;

			return result;
		}

		public boolean empty() {
			return queueA.isEmpty();
		}
	}
}
