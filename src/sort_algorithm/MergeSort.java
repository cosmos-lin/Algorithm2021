package sort_algorithm;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {
    
    // 递归实现
    public static void mergeSort1(int[] arr) {

        // 参数判断
        if (arr == null || arr.length == 0) return;

        process(arr, 0, arr.length-1);
    }

    // 实现arr 在 L...R上有序
    // T(N) = 2 * T(N/2) + O(N) -> O(N * logN)
    public static void process(int[] arr, int L, int R) {

        // base case L == R return
        if (L == R) return;

        int mid = L + ((R - L) >> 1);
        
        // L...mid上继续递归
        process(arr, L, mid);
        process(arr, mid+1, R);

        // 合并排序
        merge(arr, mid, L, R);
    }

    public static void merge(int[] arr, int M, int L, int R) {
        // 辅助数组
        int[] help = new int[R - L + 1];
        // 同时遍历 L...M，与M+1...R
        int p1 = L;  // L..M 指针
        int p2 = M + 1; // M+1...R 指针
        int i = 0; // help 指针
        while (p1 <= M && p2 <= R) { // 循环退出条件：p1越界， 或者p2越界
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // L...M 或 M+1...R 还有数直接拷贝到help
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }

        // 将help中的排好序的数据刷回arr
        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }
    }

    // 归并： 非递归版本
    public static void mergeSort2(int[] arr) {

        // 1.参数判断
        if (arr == null || arr.length == 0) return;

        int mergeSize = 1;  // 步长，循环控制迭代次数
        int N = arr.length;

        while (mergeSize < N) {

            // 当前左组（第一组）的第一个位置
            int L = 0;
            // 循环1：搞定  arr[0~1], arr[2~3] ... arr[N-2~N-1]
            // 循环2：搞定  arr[0~3], arr[4~7] ... arr[N-4~N-1]
            // ...
            // 循环n: 搞定  arr[0 ~ N - 1]
            while (L < N) {
                if (mergeSize >= N - L) break;
                // 中点
                int M = L + mergeSize - 1;
                int R = M + Math.min(mergeSize, N - M - 1); // R 边界 取最小值
                merge(arr, M, L, R);
                L = R + 1;
            }

            // 防止溢出
            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;
        }
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
            int testTimes = 100000;
            boolean success = true;
            for (int i = 0; i < testTimes; i++) {
                int[] arr1 = generateRandomArray(maxSize, maxValue);
                int[] arr2 = copyArray(arr1);
    
                mergeSort1(arr1);
                mergeSort2(arr2);
    
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
