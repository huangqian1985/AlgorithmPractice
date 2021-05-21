package SystemLearning.Lesson_1;

import SystemLearning.Common.ArrayFunction;

import java.util.Arrays;

// 在一个有序数组中，找<=某个数最右侧的位置
// 时间复杂度 O(logN)
public class Code06_BSNearRight {

    /**
     * @param arr       有序数组
     * @param value     <=的某个数
     * @return          <= value 的最右侧的index
     */
    public static int nearestIndex(int[] arr, int value) {
        if (null == arr || arr.length == 0) {
            return -1;
        }

        int L = 0;
        int R = arr.length - 1;
        int nearestIndex = -1;
        int mid = 0;
        while (L <= R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] <= value) {
                nearestIndex = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return nearestIndex;
    }

    // for test
    public static int test(int[] arr, int value) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= value) {
                return i;
            }
        }
        return -1;
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
            if (test(arr, value) != nearestIndex(arr, value)) {
                ArrayFunction.printArray(arr);
                System.out.println(value);
                System.out.println(test(arr, value));
                System.out.println(nearestIndex(arr, value));
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
