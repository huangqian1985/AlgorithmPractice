package SystemLearning.Lesson2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Code2_KM {

	/**
	 * 一个数组中有一种数出现K次，其他数都出现了M次，
	 * M > 1,  K < M
	 * 找到，出现了K次的数，
	 * 要求，额外空间复杂度O(1)，时间复杂度O(N)
	 * <p>
	 * 思路:
	 * 1. 	使用一个int[32]的数组, 存放所有的数的二进制位数上出现过1的次数, 例如 3(0011)出现2次, 4(0100)出现3次, 5(0101)出现3次
	 * 那么数组的值就是[..., 0, 6, 2, 5], 因为 K < M, 所以数组的每一位的值 % M 的结果不等于0, 那么那个数在这个位置上就是 1
	 * 重新组合所有位置上的 1, 得出结果
	 */
	public static int onlyKTimes(int[] arr, int k, int m) {
		if (arr == null || arr.length < k + m || k >= m) {
			return -1;
		}
		// 申请数组
		int[] t = new int[32];
		// 遍历传入的原数组
		for (int num : arr) {
			// 按位取 1
			for (int i = 0; i < t.length; i++) {
				t[i] += (num >> i) & 1;
			}
		}

		int result = 0;
		for (int i = 0; i < t.length; i++) {
			// 如果 i 位置的数不能被 m 整除, 那么 result 的第 i 位上是 1
			if (t[i] % m != 0) {
				result |= (1 << i);
			}
		}

		return result;
	}

	public static void main(String[] args) {
		// 最多出现几种数字
		int maxKinds = 9;
		// 数组元素范围
		int range = 100;
		// 测试次数
		int testTimes = 100000;
		System.out.println("Test Begin");
		for (int i = 0; i < testTimes; i++) {
			// 最多出现次数
			int maxTimes = 9;
			int a = (int) (Math.random() * maxTimes) + 1;    // 1~9
			int b = (int) (Math.random() * maxTimes) + 1;    // 1~9
			int m = Math.max(a, b);
			int k = Math.min(a, b);
			if (k == m) {
				m++;
			}

			int[] arr = randomArry(maxKinds, range, k, m);

			int onlyKTimesNum = onlyKTimes(arr, k, m);
			int testNum = test(arr, k, m);
			System.out.println("onlyKTimesNum :"+onlyKTimesNum+" testNum:" + testNum);
			if (onlyKTimesNum != testNum) {
				System.out.println("ERROR");
				break;
			}
		}
		System.out.println("Test Finish");
	}

	// 对数器
	public static int test(int[] arr, int k, int m) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int num : arr) {
			if (map.containsKey(num)) {
				map.put(num, map.get(num) + 1);
			} else {
				map.put(num, 1);
			}
		}
		System.out.println(map);
		for (int num : map.keySet()) {
			if (map.get(num) == k) {
				return num;
			}
		}
		return -1;
	}

	/**
	 * @param maxKinds 	最多出现几种数字
	 * @param range    	数组元素的范围
	 * @param k        	k times
	 * @param m			m times
	 * @return			int[] arr
	 */
	public static int[] randomArry(int maxKinds, int range, int k, int m) {
		// 保证 kindNum >= 2
		int kindNum = (int) (Math.random() * maxKinds) + 2;
		int knum = randomNum(range);
		// 数组长度
		int[] arr = new int[k + (kindNum - 1) * m];
		// HashSet的目的是为了去重
		HashSet<Integer> set = new HashSet<>();
		set.add(knum);
		kindNum--;
		//
		int index = 0;
		// 把 knum 填入数组
		for (; index < k; index++) {
			arr[index] = knum;
		}
		//
		for (int i = 0; i < kindNum; i++) {
			//
			int mnum;
			do {
				mnum = randomNum(range);
			} while (set.contains(mnum));
			set.add(mnum);

			for (int j = 0; j < m; j++) {
				arr[index++] = mnum;
			}
		}
		System.out.println(Arrays.toString(arr));
		return arr;
	}

	public static int randomNum(int range) {
		return (int) (Math.random() * range) - (int) (Math.random() * range);
	}
}
