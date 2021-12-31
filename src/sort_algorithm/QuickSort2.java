package sort_algorithm;

import java.util.Arrays;

/**
 * 快排2.0版本
 * 相对于1.0版本(一次搞定1个位置上的数),根据荷兰国旗划分规则一次性搞掂一批数；
 */
public class QuickSort2 {
    
    public static void quickSort(int[] arr) {
        // 参数判断
        if (arr == null || arr.length < 2) return;

        process(arr, 0, arr.length-1);
    }

    public static void process(int[] arr, int L, int R) {

        // base case
        if (L >= R) return;

        // 获取荷兰国旗边界
        int[] bound = netherlands(arr, L, R);
        process(arr, L, bound[0]);
        process(arr, bound[1], R);
    }

    public static int[] netherlands(int[] arr, int L, int R) {

        // 参数判断
        if (arr == null || arr.length == 0) return new int[]{-1, -1};

        // 只有1个数
        if (arr.length == 1) return new int[]{L, R};

        // 不只1个数
        int index = L;
        int lowerEqual = L - 1;
        int upperEqual = R;
        // 遍历arr; 注意index 不能越upperEqual边界
        while (index < upperEqual) {
            if (arr[index] < arr[R]) {
                swap(arr, index++, ++lowerEqual);
            } else if (arr[index] > arr[R]) {
                // 注意交换后数需要在进行判断，所以index不变
                swap(arr, index, --upperEqual);
            } else {
                index++;
            }
        }

        // 交换 arr[R] 与 arr[upperEqual]
        swap(arr, R, upperEqual);
        return new int[]{lowerEqual, upperEqual};
    }

   // 交换两个数
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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

            quickSort(arr1);
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
