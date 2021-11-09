package dichotomy_related;

import java.util.Arrays;

/**
 * 有序数组中找到<=num最右的位置
 */
public class BSNearRight {
    
    public static int nearestInedx(int[] arr, int num) {
        // 1.参数判断
        if (arr == null || arr.length == 0) return -1;

        int L = 0;
        int R = arr.length - 1;
        int index = -1; // 记录最右位置
        // base 边界条件： 二分到只剩下1个数时，退出循环
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] <= num) {
                index = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return index;
    }

    //  暴力解，
    public static int violence(int[] arr, int num) {
        if (arr == null || arr.length == 0) return 0;

        int index = -1;
        for (int i = 0; i < arr.length && arr[i] <= num; i++) {
            index = i;
        }

        return index;
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
