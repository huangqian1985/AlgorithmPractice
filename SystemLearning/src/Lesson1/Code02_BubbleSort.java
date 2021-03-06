package Lesson1;

import Common.ArrayFunction;

// 冒泡排序
public class Code02_BubbleSort {
    // 时间复杂度 O(N平方)
    public static void bubbleSort(int arr[]) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    ArrayFunction.Swap(arr, i, j);
                }
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
            bubbleSort(arr1);
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
