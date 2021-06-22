package Lesson5;

import Common.ArrayFunction;

import java.util.Arrays;

public class Code02_PartitionAndQuickSort {

	// 给定一个数组arr，和一个整数num。请把小于等于num的数放在数组的左边，大于num的数放在数组的右边。
	// 要求额外空间复杂度O(1)，时间复杂度O(N)

	// arr[L..R]上，以arr[R]位置的数做划分值
	// <= X > X
	// <= X X
	public static int partition(int[] arr, int L, int R) {
		// Base Case
		if (L > R) return -1;
		if (L == R) return L;
		// 小于区域的边界索引
		int lessIndex = L - 1;
		// 当前位置的索引
		int curIndex = L;
		// 把R位置作为目标数字
		int compareValue = arr[R];
		// 当前位置没有到R之前循环
		while (curIndex < R) {
			// 如果当前数 <= 目标数, 当前数和 <= 区域下一个数交换, <= 区域向右扩, 当前数跳下一个
			// 如果当前数 > 目标数, 当前数跳下一个
			if (arr[curIndex] <= compareValue) {
				ArrayFunction.Swap(arr, curIndex, ++lessIndex);
			}
			curIndex++;
		}
		// 将目标数, 与 <= 区域下一个数交换, 返回 <= 区域下一个数的索引
		ArrayFunction.Swap(arr, ++lessIndex, R);

		return lessIndex;
	}

	// arr[L...R] 玩荷兰国旗问题的划分，以arr[R]做划分值
	// <arr[R] ==arr[R] > arr[R]
	//
	// 返回值为等于区域的索引范围
	public static int[] netherlandsFlag(int[] arr, int L, int R) {
		// base case
		if (L > R)
			return new int[]{-1, -1};
		if (L == R)
			return new int[]{L, R};
		// < 和 > 区域的边界索引
		int lessIndex = L - 1;
		int largeIndex = R;
		// 当前数索引
		int curIndex = L;
		// 把R位置作为目标数字
		int compareValue = arr[R];
		// 当前位置没有到 > 区域索引 之前循环
		while (curIndex < largeIndex) {
			// 如果当前数 = 目标数, 当前数跳下一个
			if (arr[curIndex] == compareValue) {
				curIndex++;
			}
			// 如果当前数 < 目标数, 当前数和 < 区域下一个数交换, < 区域向右扩, 当前数跳下一个
			else if (arr[curIndex] < compareValue) {
				ArrayFunction.Swap(arr, curIndex++, ++lessIndex );
			}
			// 如果当前述 > 目标数, 当前数和 > 区域的前一个交换, > 区域向左扩, 当前数不动(因为交换过来的数并未比较)
			else {
				ArrayFunction.Swap(arr, curIndex, --largeIndex );
			}
		}
		// 将目标数, 与 > 区域的第一个数交换, 大于区域向右扩
		ArrayFunction.Swap(arr, largeIndex, R);

		return new int[]{lessIndex+1, largeIndex};
	}


	public static void main(String[] args) {
		int[] arr = new int[]{0,5,9,2,4,-1,2,4,3,6,8,3};
		netherlandsFlag(arr, 0, arr.length-1);
		System.out.println(Arrays.toString(arr));
	}
}
