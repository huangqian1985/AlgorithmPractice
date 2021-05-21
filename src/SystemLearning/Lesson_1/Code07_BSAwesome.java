package SystemLearning.Lesson_1;

/**
 *  局部最小值问题
 *  1. 无序数组, 相邻位置的2个数不相等, 求数组内任意局部最小的值的Index
 *
 *  时间复杂度
 */
public class Code07_BSAwesome {

    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        // 如果数组长度 = 1 直接返回
        // 如果 0 位置的值 < 1 位置的值，那么 0 位置的值就是局部最小，直接返回
        if (arr[0] == 1 || arr[0] < arr[1]) {
            return 0;
        }
        // 同理, N-1 位置也是如此
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        //
        int L = 1;
        int R = arr.length - 2;
        int mid = 0;
        while (L < R) {
            mid = L + (( R - L ) >> 1);
            // 1. 同时满足 < 左边 and < 右边, 直接返回
            if ( arr[mid] < arr[mid-1] && arr[mid] < arr[mid+1]) {
                return mid;
            }
            // 2. 如果 > 左边, 那么局部最小值应该在左边, 向左边二分
            if ( arr[mid] > arr[mid-1]) {
                R = mid - 1;
            }
            // 3. 如果 > 右边, 那么局部最小值应该在右边, 向右边二分
            if (arr[mid] > arr[mid+1]) {
                L = mid + 1;
            }
        }
        return L;
    }
}
