package Lesson5;

public class Code01_CountOfRangeSum {

	public int countRangeSum(int[] nums, int lower, int upper) {
		return 0;
	}

	// for test
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
		System.out.println(comparator(sum, lower, upper));
	}
}
