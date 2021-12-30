package sort_algorithm;

import java.util.Arrays;

/**
 * 荷兰国旗问题：
 * arr[L..R]上，以arr[R]位置的数做划分值arr[i] < arr[R] 在左; arr[i] > arr[R] 在右; arr[i] == arr[R] 在中间
 */
public class QuickSortNetherlands {
    
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        // 参数判断
        if (arr == null || arr.length == 0) return new int[]{-1, -1};

        //arr只有一个数
        if (L==R) return new int[]{L, R};

        // arr不只一个数
        int index = L;
        int lowerBoundary = L - 1; // <arr[R] 边界指针
        int upperBoundary = R; // > arr[R] 大于边界从R-1开始

        // 遍历arr
        // 遍历arr
        // while (index < R) {
        while (index < upperBoundary) { // 注意当前index 不能与右边界撞上
            if (arr[index] < arr[R]) {
                swap(arr, index++, ++lowerBoundary);
            } else if (arr[index] > arr[R]) {
                // arr[index] 与arr[--upperBoundary] 交换后
                // arr[index] 是个未判断的数，下次循环需要在进行比较判断
                swap(arr, index, --upperBoundary);
            } else {
                index++;
            }
        }

        // 交换>arr[R] 边界arr[upperBoundary] 与arr[R] 位置
        swap(arr, R, upperBoundary);
        return new int[]{lowerBoundary, upperBoundary+1};
    }

    // 交换两个数
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {2, 8, 9, 5, 1, 8, 1, 7, 5};
        System.out.println(Arrays.toString(arr));
        int[] ans = netherlandsFlag(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(ans));
        System.out.println(Arrays.toString(arr));
    }
}
