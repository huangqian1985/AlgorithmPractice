package Lesson4;

import static Common.ArrayFunction.*;

public class Code01_MergeSort {
    /**
     * 归并排序递归实现
     * Master公式：
     * T(N) = 2 * T(N / 2) + O(N)
     * 时间复杂度 O(N * logN)
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
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            //
            MergeSortByStep(arr1);
            comparator(arr2);
            //
            if (!isEqual(arr1, arr2)) {
                System.out.println("Failed!");
                break;
            }
        }
    }

    /**
     * 归并排序非递归实现(步长)
     */
    public static void MergeSortByStep(int[] arr) {
        if (arr.length <= 1) return;
        processByStep(arr);
    }

    public static void processByStep(int[] arr) {
        int arrSize = arr.length;
        int step = 1;
        while (step < arrSize) {
            // 当前左组的下标位置
            int L = 0;
            // 以左组下标不超过数组长度为条件循环
            while (L < arrSize) {
                // 如果没有右组, 跳出循环
                // 中间位置已经 >= 数组长度, 没有右值
                int M = L + step - 1;
                if (M >= arrSize) {
                    break;
                }
                // 右边位置为 中间位置+步长 与 数组长度 的小值
                int R = Math.min(M + step, arrSize - 1);
                // 执行归并排序
                merge(arr, L, M, R);
                // 重新设置左组位置
                L = R + 1;
            }
            // 防止 step 越界
            if (step > (arrSize / 2)) {
                break;
            }
            //
            step <<= 1;
        }
    }
}
