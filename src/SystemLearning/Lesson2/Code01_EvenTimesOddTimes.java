package SystemLearning.Lesson2;

/**
 *  异或处理知识点
 *
 *  1. 0^N == N
 *  2. N^N == 0
 *  3. 异或运算满足交换律和结合率
 */

/**
 * 异或处理习题
 *
 * 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
 *
 * 时间复杂度 O(N)
 *
 * 利用异或知识点处理,就不用写对数器了.
 */
public class Code01_EvenTimesOddTimes {
    public static void evenTimesOddTimes(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }
}
