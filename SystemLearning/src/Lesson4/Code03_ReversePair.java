package Lesson4;

import static Common.ArrayFunction.*;

public class Code03_ReversePair {
	/**
	 * 在一个数组中，
	 * 任何一个前面的数a，和任何一个后面的数b，
	 * 如果(a,b)是降序的，就称为逆序对
	 * 返回数组中所有的逆序对
	 */
	public static int reverPairNumber(int[] arr) {
		if (arr == null || arr.length < 2) {
			return -1;
		}
		// 递归解法
//		return process(arr, 0, arr.length - 1);
		// 非递归解法
		return mergeByStep(arr);
	}

	/**
	 * 归并排序基于递归的解法
	 */
	public static int process(int[] arr, int L, int R) {
		if (arr.length < R || L == R) {
			return 0;
		}

		int M = L + ((R - L) >> 1);

		return process(arr, L, M) + process(arr, M + 1, R) + merge(arr, L, M, R);
	}

	/**
	 * 归并排序基于非递归的解法
	 */
	public static int mergeByStep(int[] arr) {
		if (arr.length <= 1) {
			return 0;
		}
		int res = 0;
		int step = 1;
		int N = arr.length;
		while (step < N) {
			int L = 0;
			while (L < N) {
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

			//
			step <<= 1;
		}

		return res;
	}

	/**
	 * 	逆序对处理的逻辑, 和上一题的小和相反, 在左右两组数据比较时, 从两组数据的末尾开始向前比较(因为数组是按照升序排列)
	 * 	只关心左组当前值是否大于右组当前值, 如果是, 那么右组就有 (M+1位置到右组当前位置个数和左组当前数形成逆序对), 继续...
	 */
	public static int merge(int[] arr, int L, int M, int R) {
		int[] helpArr = new int[R - L + 1];
		int res = 0;
		int helpIndex = helpArr.length - 1;
		int LIndex = M;
		int RIndex = R;
		while (LIndex >= L && RIndex > M) {
			//
			res += arr[LIndex] > arr[RIndex] ? RIndex - M : 0;
			//
			helpArr[helpIndex--] = arr[LIndex] > arr[RIndex] ? arr[LIndex--] : arr[RIndex--];
		}

		while (LIndex >= L) {
			helpArr[helpIndex--] = arr[LIndex--];
		}

		while (RIndex > M) {
			helpArr[helpIndex--] = arr[RIndex--];
		}

		for (int i = 0; i < helpArr.length; i++) {
			arr[L + i] = helpArr[i];
		}

		return res;
	}

	// for test
	public static int comparator(int[] arr) {
		int ans = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					ans++;
				}
			}
		}
		return ans;
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 10;
		int maxValue = 10;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int[] originArr = generateRandomArray(maxSize, maxValue);
			if (originArr.length >= 2) {
				int[] arr1 = copyArray(originArr);
				int[] arr2 = copyArray(originArr);
				int resA = reverPairNumber(arr1);
				int resB = comparator(arr2);
				if (resA != resB) {
					System.out.println("Oops! A:" + resA + " B:" + resB);
					printArray(originArr);
					printArray(arr1);
					printArray(arr2);
					break;
				}
			}
		}
		System.out.println("测试结束");
	}
}
