package dichotomy_related;

import java.util.Arrays;

/**
 * 给定一个数组arr，已知任何两个相邻的数都不相等，找到随便一个局部最小位置返回
 */
public class BSLocalMin {
    
    public static int getLessIndex(int[] arr) {
        // 1.参数判断
        if (arr == null || arr.length == 0) return -1;

        // arr中只有1个数或者0位置开始升序， 返回0位置
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }

        // 局部尾部降序
        if (arr[arr.length -1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }

        // 先降后升求 局部最小 注意，这里从1开始 到arr.length - 2
        int L = 1; 
        int R = arr.length - 2;
        int mid = 0;
        // 注意任何两个相邻的数都不相等
        while (L < R) {
            mid = L + ((R - L) >> 1);
            // mid = (R + L) / 2;
            if (arr[mid] > arr[mid - 1]) {
                R = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                L = mid + 1;
            } else {
                return mid;
            }
        }
        // 跳出循环，说明L == R, 返回L
        return L;
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
        int maxSize = 10;
        int maxValue = 10;
        int[] arr = generateRandomArray(maxSize, maxValue);
        Arrays.stream(arr).distinct().forEach(System.out::println);
        int[] arr1 = Arrays.stream(arr).distinct().toArray();
        System.out.println(Arrays.toString(arr1));
        System.out.println(getLessIndex(arr1));
    }
}
