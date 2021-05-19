package SystemLearning.Lesson_1;

import java.util.Arrays;

import static SystemLearning.Common.ArrayFunction.*;

public class Code01_SelectionSort {

    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        // 找到 0 到 N-1 位置最小的数, 和 0 位置的数交换
        // 找到 1 到 N-1 位置最小的数, 和 1 位置的数交换
        // ...
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            // 如果 i == minIndex, 那么 i 位置就是当前的最小值
            if (i != minIndex) {
                Swap(arr, i, minIndex);
            }
        }
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            selectionSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}