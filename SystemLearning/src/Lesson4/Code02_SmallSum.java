package Lesson4;

import static Common.ArrayFunction.*;

public class Code02_SmallSum {
	/**
	 * 小和问题
	 * <p>
	 * 在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫数组小和。求数组小和。
	 * 例子： [1,3,4,2,5]
	 * 1左边比1小的数：没有
	 * 3左边比3小的数：1
	 * 4左边比4小的数：1、3
	 * 2左边比2小的数：1
	 * 5左边比5小的数：1、3、4、 2
	 * 所以数组的小和为1+1+3+1+1+3+4+2=16
	 */
	public static int smallSum(int[] arr) {
		if (arr.length <= 1) return 0;
		return MergeSortByStep(arr);
//		return process(arr, 0, arr.length - 1);
	}

	/**
	 * 基于归并排序非递归的解法
	 */
	public static int MergeSortByStep(int[] arr) {
		if (arr.length <= 1) {
			return 0;
		}
		int res = 0;
		int N = arr.length;
		// 步长
		int step = 1;
		// step < arr数组长度
		while (step < N) {
			// 当前step下左组的下标位置
			int L = 0;
			while (L < N) {
				// 找到中间位置
				int M = L + step - 1;
				if (M >= N) {
					break;
				}

				int R = Math.min(M + step, N - 1);

				res += merge(arr, L, M, R);

				L = R + 1;
			}

			if (step > (N / 2)) {
				break;
			}

			// step长度翻倍
			step <<= 1;
		}

		return res;
	}

	/**
	 * 基于归并排序递归的解法
	 */
	public static int process(int[] arr, int L, int R) {
		if (L == R) {
			return 0;
		}
		int res = 0;
		int mid = L + ((R - L) >> 1);
		return process(arr, L, mid) + process(arr, mid + 1, R) + merge(arr, L, mid, R);
	}

	public static int merge(int[] arr, int L, int M, int R) {
		int res = 0;
		int[] helpArr = new int[R - L + 1];
		// 辅助数组的下标
		int index = 0;
		// 左右两边的下标
		int LIndex = L;
		int RIndex = M + 1;
		while (LIndex <= M && RIndex <= R) {
			res += (arr[LIndex] < arr[RIndex]) ? (R - RIndex + 1) * arr[LIndex] : 0;
			helpArr[index++] = arr[LIndex] < arr[RIndex] ? arr[LIndex++] : arr[RIndex++];
		}

		while (LIndex <= M) {
			helpArr[index++] = arr[LIndex++];
		}
		while (RIndex <= R) {
			helpArr[index++] = arr[RIndex++];
		}

		for (int i = 0; i < helpArr.length; i++) {
			arr[L + i] = helpArr[i];
		}

		return res;
	}

	// for test
	public static int comparator(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int res = 0;
		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				res += arr[j] < arr[i] ? arr[j] : 0;
			}
		}
		return res;
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			if (smallSum(arr1) != comparator(arr2)) {
				succeed = false;
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}
}
