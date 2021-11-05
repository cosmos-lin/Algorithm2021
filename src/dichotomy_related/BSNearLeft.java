package dichotomy_related;

import java.util.Arrays;

/**
 * 有序数组中找到>=num最左的位置
 */
public class BSNearLeft {
    
    // O(log(n))
    public static int nearestInedx(int[] arr, int num) {
        // 1. 参数判断
        if (arr == null || arr.length < 1) return -1;

        // arr >= 1 
        int L = 0;
        int R = arr.length - 1;
        // 记录最左满足条件位置
        int ans = -1;

        while (L <= R) {  // 只有1个数满足条件，直接返回0
            int mid = L + ((R - L) >> 1); // 中点位置
            if (arr[mid] >= num) {
                ans = mid; // mid 满足条件先赋值， 但是不确定是否是最左
                R = mid - 1; // 继续在 L .. mid-1上求解答案
            } else {
                L = mid + 1;
            }
        }
        return ans;
    }

    // 对数器： 暴力解
    // O(N)
    public static int violence(int[] arr, int num) {
        if (arr == null || arr.length < 1) return -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= num) {
                return i;
            }
        }
        return -1;
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
            if (nearestInedx(arr, num) != violence(arr, num)) {
                success = false;
                System.out.println(nearestInedx(arr, num));
                System.out.println(violence(arr, num));
                break;
            }
        }
        System.out.println(success ? "Nice!" : "Bad!");
    }
}
