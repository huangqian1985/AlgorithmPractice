package Lesson6;

import Common.ArrayFunction;

/**
 *      堆处理
 */
public class Code01_Heap {

    // 已知数组和新插入的数所在的数组下标索引index, 让数组变为大根堆结构
    public void heapInsert(int[] arr, int index) {
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
    public void heapify(int[] arr, int index, int heapSize) {
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
}