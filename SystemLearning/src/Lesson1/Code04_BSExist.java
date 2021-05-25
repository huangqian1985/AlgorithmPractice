package Lesson1;

import Common.ArrayFunction;

import java.util.Arrays;

// 在一个有序数组中，找某个数是否存在
public class Code04_BSExist {
    /**
     * @param sortedArr 有序数组
     * @param num       需要查找的数
     * @return          是否存在
     * 时间复杂度 O(logN)
     */
    public static boolean exist(int[] sortedArr, int num) {
        if (null == sortedArr || sortedArr.length == 0) {
            return false;
        }
        int L = 0;
        int R = sortedArr.length - 1;
        int mid = 0;
        // 边界条件, L < R 表示至少还有2个数字
        while (L < R) {
            // 二分, 取中间位置的值
            mid = L + ((R - L) >> 1);
            if (sortedArr[mid] == num) {
                return true;
            } else if ( sortedArr[mid] > num ) {
                // 如果中间数大于查找数, 证明查找数在左边, 将 R 移动到 midIndex位置左边一位
                R = mid - 1;
            } else {
                // 如果中间数小于查找数, 证明查找数在右边, 将 L 移动到 midIndex位置右边一位
                L = mid + 1;
            }
        }
        // 边界条件, 当 L == R 的时候
        // 需要再判断一次是否是num
        return sortedArr[L] == num;
    }

    // for test
    public static boolean test(int[] sortedArr, int num) {
        for(int cur : sortedArr) {
            if(cur == num) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = ArrayFunction.generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            if (test(arr, value) != exist(arr, value)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
