package Common;

public class NodeFunction {
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

	// 双链表泛型
	public static class DoubleNodeT<T> {
		public T value;
		public DoubleNodeT<T> pre;
		public DoubleNodeT<T> next;

		public DoubleNodeT(T data) {
			value = data;
		}
	}

	// for test
	public static Node generateRandomLinkedList(int len, int value) {
		int size = (int) (Math.random() * (len + 1));
		if (size == 0) {
			size = 1;
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
			size = 1;
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
}
