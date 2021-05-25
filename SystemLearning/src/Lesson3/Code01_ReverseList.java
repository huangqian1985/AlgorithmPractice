package Lesson3;

import java.util.ArrayList;
import java.util.List;

public class Code01_ReverseList {
	// 单链表结构
	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			value = data;
		}
	}

	// 双链表结构
	public static class DoubleNode {
		public int value;
		public DoubleNode pre;
		public DoubleNode next;

		public DoubleNode(int data) {
			value = data;
		}
	}

	/**
	 * 反转单链表
	 * a -> b -> c -> null ==> c -> b -> a -> null
	 * 时间复杂度 O(N)
	 */
	public static Node reverseLinkedList(Node head) {
		Node next = null;
		Node pre = null;
		while (head != null) {
			// 保留next
			next = head.next;
			//
			head.next = pre;
			//
			pre = head;
			//
			head = next;
		}

		return pre;
	}

	/**
	 * 反转双链表
	 * 时间复杂度 O(N)
	 */
	public static DoubleNode reverseDoubleList(DoubleNode head) {
		DoubleNode next = null;
		DoubleNode pre = null;
		while (head != null) {
			next = head.next;

			head.pre = next;
			head.next = pre;

			pre = head;
			head = next;
		}

		return pre;
	}

	/**
	 * 对数器
	 * 反转单链表（使用额外空间）
	 * 时间复杂度 O(N平方)
	 */
	public static Node testReverseLinkedList(Node head) {
		//
		if (head == null) {
			return null;
		}
		ArrayList<Node> list = new ArrayList<>();
		// 添加进List中
		while (head != null) {
			list.add(head);
			head = head.next;
		}
		// 将第0位置的next设置为null
		list.get(0).next = null;
		// 挨个设置其他的next
		int N = list.size();
		for (int i = 1; i < N; i++) {
			list.get(i).next = list.get(i - 1);
		}
		return list.get(N - 1);
	}

	/**
	 * 对数器
	 * 反转单链表（使用额外空间）
	 * 时间复杂度 O(N平方)
	 */
	public static DoubleNode testReverseDoubleList(DoubleNode head) {
		if (head == null) {
			return null;
		}
		// 只有一个元素
		if (head.next == null) {
			return head;
		}
		//
		ArrayList<DoubleNode> list = new ArrayList<>();
		// 添加进List中
		while (head != null) {
			list.add(head);
			head = head.next;
		}
		// 将第0位置的next设置为null
		list.get(0).next = null;
		list.get(0).pre = list.get(1);
		// 挨个设置其他的next
		int N = list.size();
		for (int i = 1; i < N; i++) {
			DoubleNode node = list.get(i);
			node.next = list.get(i - 1);
			node.pre = (i + 1) >= N ? null : list.get(i + 1);
		}
		return list.get(N - 1);
	}

	// for test
	public static Node generateRandomLinkedList(int len, int value) {
		int size = (int) (Math.random() * (len + 1));
		if (size == 0) {
			return null;
		}
		size--;
		Node head = new Node((int) (Math.random() * (value + 1)));
		Node pre = head;
		while (size != 0) {
			Node cur = new Node((int) (Math.random() * (value + 1)));
			pre.next = cur;
			pre = cur;
			size--;
		}
		return head;
	}

	// for test
	public static DoubleNode generateRandomDoubleList(int len, int value) {
		int size = (int) (Math.random() * (len + 1));
		if (size == 0) {
			return null;
		}
		size--;
		DoubleNode head = new DoubleNode((int) (Math.random() * (value + 1)));
		DoubleNode pre = head;
		while (size != 0) {
			DoubleNode cur = new DoubleNode((int) (Math.random() * (value + 1)));
			pre.next = cur;
			cur.pre = pre;
			pre = cur;
			size--;
		}
		return head;
	}

	public static DoubleNode testReverseDoubleList2(DoubleNode head) {
		if (head == null) {
			return null;
		}
		ArrayList<DoubleNode> list = new ArrayList<>();
		while (head != null) {
			list.add(head);
			head = head.next;
		}
		list.get(0).next = null;
		DoubleNode pre = list.get(0);
		int N = list.size();
		for (int i = 1; i < N; i++) {
			DoubleNode cur = list.get(i);
			cur.pre = null;
			cur.next = pre;
			pre.pre = cur;
			pre = cur;
		}
		return list.get(N - 1);
	}

	// for test
	public static List<Integer> getLinkedListOriginOrder(Node head) {
		List<Integer> ans = new ArrayList<>();
		while (head != null) {
			ans.add(head.value);
			head = head.next;
		}
		return ans;
	}

	// for test
	public static boolean checkLinkedListReverse(List<Integer> origin, Node head) {
		for (int i = origin.size() - 1; i >= 0; i--) {
			if (!origin.get(i).equals(head.value)) {
				return false;
			}
			head = head.next;
		}
		return true;
	}

	// for test
	public static List<Integer> getDoubleListOriginOrder(DoubleNode head) {
		List<Integer> ans = new ArrayList<>();
		while (head != null) {
			ans.add(head.value);
			head = head.next;
		}
		return ans;
	}

	// for test
	public static boolean checkDoubleListReverse(List<Integer> origin, DoubleNode head) {
		DoubleNode end = null;
		for (int i = origin.size() - 1; i >= 0; i--) {
			if (!origin.get(i).equals(head.value)) {
				return false;
			}
			end = head;
			head = head.next;
		}
		for (int i = 0; i < origin.size(); i++) {
			if (!origin.get(i).equals(end.value)) {
				return false;
			}
			end = end.pre;
		}
		return true;
	}

	// for test
	public static void main(String[] args) {
		int len = 50;
		int value = 100;
		int testTime = 100000;
		System.out.println("test begin!");
		for (int i = 0; i < testTime; i++) {
			Node node1 = generateRandomLinkedList(len, value);
			List<Integer> list1 = getLinkedListOriginOrder(node1);
			node1 = reverseLinkedList(node1);
			if (!checkLinkedListReverse(list1, node1)) {
				System.out.println("Oops1!");
			}

			Node node2 = generateRandomLinkedList(len, value);
			List<Integer> list2 = getLinkedListOriginOrder(node2);
			node2 = testReverseLinkedList(node2);
			if (!checkLinkedListReverse(list2, node2)) {
				System.out.println("Oops2!");
			}

			DoubleNode node3 = generateRandomDoubleList(len, value);
			List<Integer> list3 = getDoubleListOriginOrder(node3);
			node3 = testReverseDoubleList(node3);
			if (!checkDoubleListReverse(list3, node3)) {
				System.out.println("Oops3!");
			}

			DoubleNode node4 = generateRandomDoubleList(len, value);
			List<Integer> list4 = getDoubleListOriginOrder(node4);
			node4 = testReverseDoubleList2(node4);
			if (!checkDoubleListReverse(list4, node4)) {
				System.out.println("Oops4!");
			}

		}
		System.out.println("test finish!");
	}
}
