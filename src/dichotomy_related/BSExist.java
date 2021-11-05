package dichotomy_related;

import java.util.Arrays;

/**
 * 有序数组中找到num
 */
public class BSExist {
    // O(log(n))
    public static boolean exist(int[] sortedArr, int num) {
        // 1.参数判断
        if (sortedArr == null || sortedArr.length < 1) return false;

        // 定义参数
        int L = 0;
        int R = sortedArr.length - 1;

        // 二分循环判断：N -> N/2 -> N/2^2 ... N/2^n <=> 1 => L == R (L < R(边界))
        // L == R 跳出循环
        while (L < R) {
            int mid = L + ((R - L) >> 1);
            if (sortedArr[mid] == num) {
                return true;
            } else if (sortedArr[mid] > num) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return sortedArr[L] == num; // L == R 判断该数是否相等
    }

    // 对数器： 暴力解
    // O(N)
    public static boolean violence(int[] sortedArr, int num) {
        for (int cur : sortedArr) {
            if (cur == num) {
                return true;
            }
        }
        return false;
    }

    // 随机生成数组
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            // [-n, n]
            arr[i] = (int) ((Math.random() * maxValue) + 1) - (int) ((Math.random() * maxValue) + 1);
        }
        return arr;
    }

    public static void main(String[] args) {
        int maxSize = 100;
        int maxValue = 100;
        int testTimes = 100000;
        boolean success = true;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int num = (int) (Math.random() * maxValue) + 1;
            if (exist(arr, num) != violence(arr, num)) {
                success = false;
                break;
            }
        }
        System.out.println(success ? "Nice!" : "Bad!");
    }

}