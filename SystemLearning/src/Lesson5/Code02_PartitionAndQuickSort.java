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


	public static void main(String[] args) {

	}
}
