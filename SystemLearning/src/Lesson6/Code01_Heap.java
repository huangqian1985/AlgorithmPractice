package Lesson6;

import Common.ArrayFunction;

import java.util.Arrays;

/**
 * 堆处理
 */
public class Code01_Heap {

    // 已知数组和新插入的数所在的数组下标索引index, 让数组变为大根堆结构
    public static void heapInsert(int[] arr, int index) {
        if (arr.length == 0 || index < 1)
            return;
        // 如果当前节点的值 > 当前节点的父节点的值, 进行交换(同样也包括了到了0位置的节点的情况)
        int parentIndex = (index - 1) / 2;
        while (arr[index] > arr[parentIndex]) {
            ArrayFunction.Swap(arr, index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    // 下沉index位置的元素, 直到子节点都比当前节点的值小
    public static void heapify(int[] arr, int index, int heapSize) {
        int leftNodeIndex = index * 2 + 1;
        while (leftNodeIndex < heapSize) {
            // 获取左节点和右节点中较大的节点
            // 1. 如果右节点存在 && 右节点的值大于左节点, 那么较大的节点即为右节点, 否则为左节点
            int largeIndex = leftNodeIndex + 1 < heapSize && arr[leftNodeIndex] < arr[leftNodeIndex + 1] ? leftNodeIndex + 1 : leftNodeIndex;
            // 2. 步骤1 的较大节点与当前节点的值比较, 如果当前节点的值大于步骤1的较大节点, 覆盖 largeIndex 的值
            largeIndex = arr[largeIndex] > arr[index] ? largeIndex : index;
            // 3. 如果 largeIndex == index , 说明子节点的值都比当前值要小, 结束循环
            if (largeIndex == index)
                break;
            // 4. 开始交换
            ArrayFunction.Swap(arr, largeIndex, index);
            index = largeIndex;
            leftNodeIndex = index * 2 + 1;
        }
    }

    // 堆排序
    // 步骤1. 先让整个数组变成大根堆, 数组中最大的数在 index=0 位置
    // 步骤2. 将 0 位置的数 与 heapSize 位置的数做交换, heapSize--
    // 重复步骤1
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length <= 1)
            return;

        // 遍历初始arr调用heapInsert将数组变为大根堆
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }

        int heapSize = arr.length;
        while (heapSize > 0) {
            ArrayFunction.Swap(arr, --heapSize, 0);
            heapify(arr, 0, heapSize);
        }
    }

    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = ArrayFunction.generateRandomArray(maxSize, maxValue);
            int[] arr2 = ArrayFunction.copyArray(arr1);
            heapSort(arr1);
            comparator(arr2);
            if (!ArrayFunction.isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}