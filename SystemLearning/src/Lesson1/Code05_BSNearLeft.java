package Lesson1;

import Common.ArrayFunction;

import java.util.Arrays;

// 在一个有序数组中，找>=某个数最左侧的位置
// 时间复杂度 O(logN)
public class Code05_BSNearLeft {

    /**
     *
     * @param arr       有序数组
     * @param value     >=的某个数
     * @return          >= value 的最左侧的index
     */
    public static int nearestIndex(int[] arr, int value) {
        if (null == arr || arr.length == 0) {
            return -1;
        }
        // 记录当前 >= 数的index位置, 初始赋值为无效值(数组下标最大值+1)
        int nearestIndex = -1;
        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        //
        while (L <= R) {
            // 二分到当前L R的中间
            mid = L + ( (R - L) >> 1 );
            // 当前值 >= value, 向左二分
            if ( arr[mid] >= value) {
                // 更新需要返回的索引值
                nearestIndex = mid;
                R = mid - 1;
            } else {
                // 否则向右二分
                L = mid + 1;
            }
        }
        return nearestIndex;
    }

    // for test
    public static int test(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= value) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int testTime = 100000;
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
