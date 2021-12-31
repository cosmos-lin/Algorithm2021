package sort_algorithm;

import java.util.Arrays;

/**
 * arr[L..R]上，以arr[R]位置的数做划分值arr[i] <= arr[R] 在左arr[i] > arr[R] 在右
 */

class Partition {

    public static int partition(int[] arr, int L, int R) {
        // 1.参数判断
        if (arr == null || arr.length == 0) return -1;

        // 2. 只有一个数，直接return
        if (L == R) return arr[L];

        // 3.不只一个数
        int lowerEqual = L - 1; // 划分界线起始位置
        int index = L; // arr指针
        // 遍历arr
        while (index < R) {
            if (arr[index] <= arr[R]) {
                swap(arr, index, ++lowerEqual);
            }
            index++;
        }

        // 此时index == R - 1; 完成最后arr[R] 与 arr[++lowerEqual] 交换
        swap(arr, R, ++lowerEqual);

        return lowerEqual;
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
        int lowerEqual = partition(arr, 0, arr.length-1);
        System.out.println(lowerEqual);
        System.out.println(Arrays.toString(arr));
    }
}