package Lesson4;

import Common.ArrayFunction;

import java.util.Arrays;
import static Common.ArrayFunction.*;

public class Code01_MergeSort {

    /**
     *      归并排序递归实现
     */
    public static void MergeSort(int[] arr) {
        if (arr.length == 0) return;
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    public static void merge(int[] arr, int L, int M, int R) {
        // 辅助数组, 长度是R-L+1
        int[] helpArr = new int[R - L + 1];
        // 辅助数组的下标
        int index = 0;
        // 左右两边的下标
        int LIndex = L;
        int RIndex = M + 1;
        // 左右下标从初始位置起, 比较大小, 如果左边<=右边的数, 那么把左边的数放入辅助数组中
        // 直到左右下标其中一个越界
        while (LIndex <= M && RIndex <= R) {
            helpArr[index++] = (arr[LIndex] <= arr[RIndex]) ? arr[LIndex++] : arr[RIndex++];
        }
        // 处理未越界的下标元素, 放入辅助数组中
        while (LIndex <= M) {
            helpArr[index++] = arr[LIndex++];
        }
        while (RIndex <= R) {
            helpArr[index++] = arr[RIndex++];
        }
        // 将辅助数组的值拷贝到原数组中
        for (int i = 0; i < helpArr.length; i++) {
            arr[L + i] = helpArr[i];
        }
    }

    public static void main(String[] args) {
        int maxSize = 100;
        int maxValue = 200;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            int[] arrs1 = generateRandomArray(maxSize, maxValue);
            int[] arrs2 = copyArray(arrs1);
            //
            MergeSort(arrs1);
            comparator(arrs2);
            //
            if ( !isEqual(arrs1, arrs2) ) {
                System.out.println("Failed!");
                break;
            }
        }
    }
}
