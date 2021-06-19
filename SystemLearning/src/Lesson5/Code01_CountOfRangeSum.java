package Lesson5;

public class Code01_CountOfRangeSum {

	public static int countRangeSum(int[] nums, int lower, int upper) {
		if (nums.length == 1) {
			return nums[0] >= lower && nums[0] <= upper ? 1 : 0;
		}

		long[] sums = calcSums(nums);

		return process(sums, 0, sums.length - 1, lower, upper);
	}

	// 递归调用
	public static int process(long[] sum, int L, int R, int lower, int upper) {
		if (L == R) {
			return sum[L] >= lower && sum[L] <= upper ? 1 : 0;
		}

		int M = L + ((R - L) >> 1);
		int leftCount = process(sum, L, M, lower, upper);
		int rightCount = process(sum, M + 1, R, lower, upper);
		int mergeCount = merge(sum, L, M, R, lower, upper);

		return leftCount + rightCount + mergeCount;
	}

	//
	public static int merge(long[] sum, int L, int M, int R, int lower, int upper) {
		// 不回退窗口计算
		int result = 0;
		// 窗口的左右初始化在 L 位置
		int windowL = L;
		int windowR = L;
		// 遍历右组每一个数
		for (int i = M + 1; i <= R; i++) {
			// 对于右组的当前前缀和 sum[i], 如果左组的值位于 sum[i] - upper 到 sum[i] - lower
			// 那么左组的数 + 1位置到右组数的子数组的和, 就位于 lower 到 upper 之间
			long min = sum[i] - upper;
			long max = sum[i] - lower;
			// min -> max 即为窗口的范围, 分别在左组寻找符合窗口的左值和右值
			while (sum[windowL] < min && windowL <= M) {
				windowL++;
			}
			while (sum[windowR] <= max && windowR <= M) {
				windowR++;
			}
			result += windowR - windowL;
		}

		// 归并排序
		long[] help = new long[R - L + 1];
		int helpIndex = 0;
		int LIndex = L;
		int RIndex = M + 1;
		while (LIndex <= M && RIndex <= R) {
			help[helpIndex++] = sum[LIndex] <= sum[RIndex] ? sum[LIndex++] : sum[RIndex++];
		}

		while (LIndex <= M) {
			help[helpIndex++] = sum[LIndex++];
		}

		while (RIndex <= R) {
			help[helpIndex++] = sum[RIndex++];
		}

		for (int i = 0; i < help.length; i++) {
			sum[L + i] = help[i];
		}

		return result;
	}

	// 求前缀和数组
	public static long[] calcSums(int[] nums) {
		long[] sums = new long[nums.length];
		sums[0] = nums[0];

		for (int i = 1; i < nums.length; i++) {
			sums[i] = sums[i - 1] + nums[i];
		}

		return sums;
	}

	//
	public static int comparator2(int[] nums, int lower, int upper) {
		if (nums.length == 1) {
			return nums[0] >= lower && nums[0] <= upper ? 1 : 0;
		}
		int count = 0;
		// 获取前缀和数组
		long[] sums = calcSums(nums);
		//
		if (sums[0] >= lower && sums[0] <= upper)
			count++;
		//
		for (int i = 1; i < sums.length; i++) {
			for (int j = 0; j <= i; j++) {
				// 原数组从 j 位置开始到 i 位置结束的和 等于 前缀和数组从 0 开始到 i 结尾的值减去 从 0 开始到 j-1 位置的值
				// sum[j->i] = sum[0->i] - sum[0->(j-1)]
				long value = j == 0 ? sums[i] : sums[i] - sums[j - 1];
				if (value >= lower && value <= upper)
					count++;
			}
		}
		return count;
	}

	// for test
	// O(N * 三次方)
	public static int comparator(int[] nums, int lower, int upper) {
		if (nums.length == 1) {
			return nums[0] >= lower && nums[0] <= upper ? 1 : 0;
		}
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			long sum = 0;
			for (int j = i; j < nums.length; j++) {
				sum += nums[j];
				if (sum >= lower && sum <= upper) {
					count++;
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[] sum = new int[]{2147483647,-2147483648,-1,0};
		int lower = -1;
		int upper = 0;
		System.out.println(countRangeSum(sum, lower, upper));
	}
}
