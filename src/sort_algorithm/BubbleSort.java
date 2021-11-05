package sort_algorithm;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        // 1.参数判断
        if (arr == null || arr.length < 2) return;


        for (int i = arr.length - 1; i > 0; i--) { // 临界把握： i = 0 时，arr已经排行序，所以i > 0
            // 0 ~ n-1 上两两交换找出最大值 放到n-1位置
            // 0 ~ n-2 上两两交换找出最大值 放到n-2位置
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j+1);
                }
            }
        }
    }

    // ^ 异或 交换 arr上i和j上的值；
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[j] ^ arr[i];
        arr[i] = arr[i] ^ arr[j];  
    }

    // 对数器：暴力测试
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
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

    public static int[] copyArray(int[] arr) {
        if (arr == null) return null;

        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
            
        }
        return res;
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 != null) return false;

        if (arr1 != null && arr2 == null) return false;

        if (arr1 == null && arr2 == null) return true;

        if (arr1.length != arr2.length) return false;

        for (int i = 0; i < arr2.length; i++) {
            if (arr1[i] != arr2[i]) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        int maxSize = 100;
        int maxValue = 100;
        int testTimes = 1000000;
        boolean success = true;
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);

            bubbleSort(arr1);
            comparator(arr2);

            if (!isEqual(arr1, arr2)) {
                success = false;
                System.out.println(Arrays.toString(arr1));
                System.out.println(Arrays.toString(arr2));
                break;
            }
        }

        System.out.println(success ? "Nice!" : "Bad!");
    }
}
