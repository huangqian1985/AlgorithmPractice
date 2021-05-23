package SystemLearning.Lesson2;

/**
 * 	异或处理习题
 *
 * 	异或处理知识点
 * 	1. 0^N == N
 * 	2. N^N == 0
 * 	3. 异或运算满足交换律和结合率
 */
public class Code01_EvenTimesOddTimes {
	/**
	 * 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
	 *
	 * 时间复杂度 O(N)
	 *
	 * 利用异或知识点处理,就不用写对数器了.
	 */
	public static void printOddTimesNum1(int[] arr) {
		int eor = 0;
		for (int i : arr) {
			eor ^= arr[i];
		}
		System.out.println(eor);
	}

	/**
	 *  怎么把一个int类型的数，提取出二进制最右侧的1来
	 *
	 *  思路:
	 *          a取反 + 1 的结果 和 a 做异或
	 *          如果 a = 01001100
	 *              ~a          = 10110011
	 *              ~a + 1      = 10110100
	 *              a & (~a+1)  = 00000100   所以最右侧的1是第3位
	 *              (~a+1) = -a 取反+1就是-a
	 *          所以这题的解法就是 a & -a
	 */
	public static int findRightBitOne(int num) {
		return num & -num;
	}

	/**
	 *  一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
	 *
	 *  思路:
	 *          1. 假设2个数为 a 和 b, 用一个数挨个遍历数组元素做异或计算，结果就是 a ^ b
	 *          2. 找到 a ^ b 结果的最右侧的1是第几位, 比如是n, 也就意味着, a 和 b 在 n 位置上一定不相等(a的n位是1,b的n位是0 或者相反)
	 *          3. 假设 a 的 n 位是1, 再次遍历数组, 如果当前元素的 n 位 是1, 那么参与异或, 满足条件的一定是基数次的 a 和偶数次的n位是1的数字,
	 *          4. 那么异或的结果, 就是a, 那么 a 异或上 步骤1 的结果, 得到 b
	 *
	 *  时间复杂度 O(N * 2)
	 */
	public static void printOddTimesNum2(int[] arr) {
		// 步骤1
		int eor = 0;
		for (int i : arr) {
			eor ^= arr[i];
		}
		//
		int flag = eor & -eor;
		int a = 0;
		// 步骤3
		for (int i : arr) {
			if ((arr[i] & flag) != 0) {
				a ^= arr[i];
			}
		}
		int b = a ^ eor;

		System.out.println("两种数是 " + a + " " + b);
	}
}

