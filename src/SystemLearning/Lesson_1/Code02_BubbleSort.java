package SystemLearning.Lesson_1;

import static SystemLearning.Common.ArrayFunction.*;
import static SystemLearning.Common.ArrayFunction.printArray;
import static SystemLearning.Lesson_1.Code01_SelectionSort.selectionSort;

public class Code02_BubbleSort {

    public static void bubbleSort(int arr[]) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    Swap(arr, i, j);
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
        System.out.println("总共耗时:" + (System.currentTimeMillis() - curTime));
    }
}
