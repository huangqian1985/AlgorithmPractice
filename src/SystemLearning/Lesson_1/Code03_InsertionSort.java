package SystemLearning.Lesson_1;

import SystemLearning.Common.ArrayFunction;

// 插入排序
public class Code03_InsertionSort {
    // 时间复杂度 O(N平方)
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = i-1; j >= 0 && arr[j] > arr[j+1]; j--) {
                ArrayFunction.Swap(arr, j, j+1);
            }
        }
    }

    public static void main(String[] args) {
        long curTime = System.currentTimeMillis();
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = ArrayFunction.generateRandomArray(maxSize, maxValue);
            int[] arr2 = ArrayFunction.copyArray(arr1);
            insertionSort(arr1);
            ArrayFunction.comparator(arr2);
            if (!ArrayFunction.isEqual(arr1, arr2)) {
                succeed = false;
                ArrayFunction.printArray(arr1);
                ArrayFunction.printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
        System.out.println("总共耗时:" + (System.currentTimeMillis() - curTime));
    }
}
