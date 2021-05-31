package Lesson3;

public class Code08_GetMax {

	/*
		用递归实现在arr数组中求最大值
	 */
	public static int getMax(int[] arr) {
		return process(arr, 0, arr.length - 1);
	}

	public static int process(int[] arr, int L, int R) {
		if (L == R) {
			return arr[L];
		}
		if (R - L == 1) {
			return Math.max(arr[L], arr[R]);
		}
		int mid = L + ((R - L) >> 1);
		int leftMax = process(arr, L, mid);
		int rightMax = process(arr, mid+1, R);
		return Math.max(leftMax, rightMax);
	}

	public static void main(String[] args) {
		int arr[] = {1,10,20,3,5,30,11,25,43,2,8,0};
		System.out.println(getMax(arr));
	}
}
