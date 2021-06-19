package Lesson5;

public class Code01_CountOfRangeSum {

	public int countRangeSum(int[] nums, int lower, int upper) {
		return 0;
	}

	// 求前缀和数组
	public static long[] calcSums(int[] nums) {
		long sum = 0;
		long[] sums = new long[nums.length];
		sums[0] = nums[0];

		for (int i = 1; i < nums.length; i++) {
			sums[i] = sums[i-1] + nums[i];
		}

		return sums;
	}

	//
	public static int comparator2(int[] nums, int lower, int upper) {
		if (nums.length==1) {
			return nums[0] >= lower && nums[0] <= upper ? 1 : 0;
		}
		int count = 0;
		// 获取前缀和数组
		long[] sums = calcSums(nums);
		//
		if (sums[0] >= lower && sums[0] <= upper )
			count++;
		//
		for (int i = 1; i < sums.length; i++) {
			for (int j = 0; j <= i; j++) {
				// 原数组从 j 位置开始到 i 位置结束的和 等于 前缀和数组从 0 开始到 i 结尾的值减去 从 0 开始到 j-1 位置的值
				// sum[j->i] = sum[0->i] - sum[0->(j-1)]
				long value = j == 0 ? sums[i] : sums[i] - sums[j-1];
				if (value >= lower && value <= upper)
					count++;
			}
		}
		return count;
	}

	// for test
	// O(N * 三次方)
	public static int comparator(int[] nums, int lower, int upper) {
		if (nums.length==1) {
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
		int[] sum = new int[]{-2147483647,0,-2147483647,2147483647};
		int lower = -564;
		int upper = 3864;
		System.out.println(comparator2(sum, lower, upper));
	}
}
