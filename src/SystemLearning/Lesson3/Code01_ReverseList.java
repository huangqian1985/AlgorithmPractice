package SystemLearning.Lesson3;

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
		public DoubleNode last;
		public DoubleNode next;

		public DoubleNode(int data) {
			value = data;
		}
	}

	/**
	 *		反转单链表
	 *		a -> b -> c -> null ==> c -> b -> a -> null
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
}
