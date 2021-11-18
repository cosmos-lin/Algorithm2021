package common_interview_questions;

import java.util.Arrays;

/**
 * 在一个数组中，一个数左边比它小的数的总和，叫该数的小和所有数的小和累加起来，叫数组小和, 
 * 给定一个数组arr，求数组小和例子： 
 * [1,3,4,2,5] 
 * 1左边比1小的数：没有
 * 3左边比3小的数：1
 * 4左边比4小的数：1、3
 * 2左边比2小的数：1
 * 5左边比5小的数：1、3、4、 2
 * 所以数组的小和为1+1+3+1+1+3+4+2=16  
 */
public class SmallSum {
    
    public static int smallSum(int[] arr) {
        // 1. 参数判断
        if (arr == null || arr.length < 2) return 0;

        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int L, int R) {
        // base case 只有1个数，小和为0
        if (L == R) return 0;

        // 中点位置
        int mid = L + ((R - L) >> 1); // 注意：L 可以是任意位置， 所以 不能直接 (R - L)/2

        return process(arr, L, mid) + process(arr, mid+1, R) + merge(arr, L, mid, R);
    }

    public static int merge(int[] arr, int L, int M, int R) {
        // 辅助数组
        int[] help = new int[R - L + 1];

        // L...M 指针
        int leftIndex = L;
        int rightIndex = M + 1;
        // help 指针
        int i = 0;
        int res = 0;
        // 同时遍历 L...M, M+1...R
        while (leftIndex <= M && rightIndex <= R) {
            // 由于 M+1~R 有序， 所以：arr[leftIndex] < arr[rightIndex] -> arr[leftIndex] < arr[rightIndex+n]
            res += arr[leftIndex] < arr[rightIndex] ? arr[leftIndex] * (R - rightIndex + 1): 0;
            // 注意这里必须也是严格与上面一样< ; 若是<= 会导致 res计算错误
            help[i++] = arr[leftIndex] < arr[rightIndex] ? arr[leftIndex++] : arr[rightIndex++];
        }
        // leftIndex 或 rightIndex 越界
        while (leftIndex <= M) {
            help[i++] = arr[leftIndex++];
        }
        while (rightIndex <= R) {
            help[i++] = arr[rightIndex++];
        }
        
        // 将help数据刷回到arr中
        for (int j = 0; j < help.length; j++) {
            arr[L+j] = help[j];
        }

        return res;
    }

    // 暴力方法 O(N^2)
    public static int violence(int[] arr) {
        if (arr == null || arr.length < 2) return 0;

        int ans = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                ans += arr[j] < arr[i] ? arr[j] : 0;
            }
        }

        return ans;
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

    public static void main(String[] args) {
        int maxSize = 100;
        int maxValue = 100;
        int testTimes = 100000;
        boolean success = true;
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);

            int ans1 = smallSum(arr1);
            int ans2 = violence(arr2);

            if (ans1 != ans2) {
                success = false;
                System.out.println(ans1);
                System.out.println(Arrays.toString(arr1));
                System.out.println(ans2);
                System.out.println(Arrays.toString(arr2));
                break;
            }
        }

        System.out.println(success ? "Nice!" : "Bad!");
    }
}
