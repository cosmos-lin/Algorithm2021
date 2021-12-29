package common_interview_questions;

import java.util.Arrays;

public class ReversePair {
    
    public static int reversePairNumber(int[] arr) {
        // 1.参数判断
        if (arr == null || arr.length < 2) return 0;

        return process(arr, 0, arr.length-1);
    }

    public static int process(int[] arr, int L, int R) {

        // base case 产生0个逆序对
        if (L == R) return 0;

        // 中点
        int mid = L + ((R - L) >> 1);

        return process(arr, L, mid) + process(arr, mid+1, R) + merge(arr, L, mid, R);
    }

    public static int merge(int[] arr, int L, int M, int R) {
        // 辅助参数
        int[] help = new int[R-L+1];

        int res = 0;
        int p1 = L; // left 指针
        int p2 = M + 1; // right 指针
        int i = 0; // help 指针
        // 从左-> 右遍历 left 和right数组
        while (p1 <= M && p2 <= R) {
            res += arr[p1] > arr[p2] ? (M - p1 + 1) : 0;
            help[i++] = arr[p1] > arr[p2] ? arr[p2++] : arr[p1++];
        }

        // p1 或p2越界后处理
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }

        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        // 将help数组copy替换原来的arr
        for (int j = 0; j < help.length; j++) {
            arr[L+j] = help[j];
        }

        return res;
    }

    // 暴力方法
    public static int violence(int[] arr) {

        if (arr == null || arr.length < 2) return 0;
        
        int res = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    res++;
                }
            }
        }
        return res;
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
    
                int ans1 = reversePairNumber(arr1);
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
