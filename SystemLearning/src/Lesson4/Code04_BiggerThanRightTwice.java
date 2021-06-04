package Lesson4;

import java.util.LinkedList;

public class Code04_BiggerThanRightTwice {

	/**
	 * 在一个数组中，
	 * 对于每个数num，求有多少个后面的数 * 2 依然<num，求总个数
	 * 比如：[3,1,7,0,2]
	 * 3的后面有：1，0
	 * 1的后面有：0
	 * 7的后面有：0，2
	 * 0的后面没有
	 * 2的后面没有
	 * 所以总共有5个
	 */
	public static int biggerTwice(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		return process(arr, 0, arr.length - 1);
	}

	public static int process(int[] arr, int L, int R) {
		if (L == R) return 0;
		int M = L + ((R - L) >> 1);
		return process(arr, L, M) + process(arr, M + 1, R) + merge(arr, L, M, R);
	}

	/**
	 * 这题和之前题目的区别在于, 之前的结果在归并排序过程中,就可以计算出来,而本题需要单独计算.
	 * 因为需要在右组移动窗口直到R位置确定右组的每一个数*2是否小于左组
	 */
	public static int merge(int[] arr, int L, int M, int R) {
		if (L == R) return 0;
		int res = 0;
		int windowR = M + 1; // windowR位于右组第一个的位置
		// 遍历左组
		for (int i = L; i <= M; i++) {
			// 循环条件 	1. windowR没有越界
			//			2. windowR位置的数 * 2 < 左组的数
			while (windowR <= R && arr[windowR] * 2 < arr[i]) {
				// 满足条件, windowR向右移动
				windowR++;
			}
			// 最终windowR停在符合条件的数的右边一个位置, 所以下面计算个数的时候是 windowR - M - 1
			// 移动结束, 开始计算符合条件的个数
			res += windowR - M - 1;
		}

		// 正常的归并排序
		int[] help = new int[R - L + 1];
		int helpIndex = 0;
		int LIndex = L;
		int RIndex = M + 1;
		while (LIndex <= M && RIndex <= R) {
			help[helpIndex++] = arr[LIndex] < arr[RIndex] ? arr[LIndex++] : arr[RIndex++];
		}

		while (LIndex <= M) {
			help[helpIndex++] = arr[LIndex++];
		}
		while (RIndex <= R) {
			help[helpIndex++] = arr[RIndex++];
		}

		for (int i = 0; i < help.length; i++) {
			arr[L + i] = help[i];
		}

		return res;
	}

	// for test
	public static int comparator(int[] arr) {
		int ans = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > (arr[j] << 1)) {
					ans++;
				}
			}
		}
		return ans;
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
		}
		return arr;
	}

	// for test
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			int res1 = biggerTwice(arr1);
			int res2 = comparator(arr2);
			if (res1 != res2) {
				System.out.println("Oops! res1 : " + res1 + " res2 : " + res2);
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println("测试结束");
	}
}
