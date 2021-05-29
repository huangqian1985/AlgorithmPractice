package Lesson3;

import java.util.ArrayList;

public class Code02_DeleteGivenValue {

	// 单链表结构
	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			value = data;
		}
	}

	/**
	 *		删除链表所有等于某个值的元素
	 *
	 *		时间复杂度 O(N)
	 */
	public static Node removeValue(Node head, int num) {
		if (head == null) {
			return null;
		}

		// 如果头结点就是要删除的节点, 那么要找到新的头节点
		while (head.value == num) {
			head = head.next;
		}

		//
		Node cur = head;
		Node pre = head;
		while (cur != null) {
			//
			if (cur.value == num) {
				pre.next = cur.next;
			} else {
				pre = cur;
			}
			cur = cur.next;
		}

		return head;
	}

	public static void main(String[] args) {

	}
}

