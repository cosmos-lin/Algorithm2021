package sort_algorithm;

import java.util.Arrays;

/**
 * 插入排序
 */

public class InsertionSort {
    public static void insertionSort(int[] arr) {
        // 1.参数判断
        if (arr == null || arr.length < 2) return;

        // 0 天然有序
        // 0 ~ 1 上 实现有序
        // 0 ~ 2 上实现有序
        //...
        for (int i = 1; i < arr.length; i++) {
            // for (int j = i - 1; j >= 0; j--) {  // 0 ~ i 上实现有序
            //     if (arr[j+1] < arr[j]) {
            //         swap(arr, j + 1, j);
            //     } else {
            //         break;
            //     } 
            // }

            // 简化
            for (int j = i - 1; j >= 0 && arr[j + 1] < arr[j]; j--) {
                swap(arr, j+1, j);
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

            insertionSort(arr1);
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
