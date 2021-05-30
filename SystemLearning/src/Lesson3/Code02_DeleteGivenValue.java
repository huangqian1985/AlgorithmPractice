package Lesson3;

import static Common.NodeFunction.*;

import java.util.Arrays;
import java.util.LinkedList;

public class Code02_DeleteGivenValue {

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
			// 如果head是唯一节点, 而且删除的值就是head
			if (head == null) {
				break;
			}
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

	/**
	 *	对数器
	 */
	public static LinkedList<Node> testRemoveValue(Node head, int num) {
		LinkedList<Node> list = new LinkedList<>();
		while (head != null) {
			if (head.value != num) {
				list.add(new Node(head.value));
			}
			head = head.next;
		}
		return list;
	}

	public static boolean checkEquals( LinkedList<Node> list, Node head ) {
		for (Node n : list) {
			if (head == null) {
				return false;
			}

			if (n.value != head.value) {
				return false;
			}

			head = head.next;
		}
		return true;
	}

	public static void main(String[] args) {
		int len = 50;
		int value = 100;
		int testTime = 100000;
		System.out.println("test begin!");
		for (int i = 0; i < testTime; i++) {
			Node head = generateRandomLinkedList(len, value);
			if (head == null) {
				System.out.println("head == null");
				continue;
			}

			LinkedList<Node> list = testRemoveValue(head, head.value);
			Node resultNode = removeValue(head, head.value);

			if ( !checkEquals(list, resultNode) ) {
				System.out.println("test Failed!");
				break;
			}
		}
		System.out.println("test finish!");
	}
}