package dichotomy_related;

/**
 * 求arr中最大值
 */
public class GetMax {
    
    public static int getMax(int[] arr) {
        if (arr == null || arr.length == 0) return -1;

        return process(arr, 0, arr.length-1);
    }

    public static int process(int[] arr, int L, int R) {
        // base case arr中只有一个数，直接返回
        if (L == R) return arr[L];
        // arr 中还有不止一个数
        int mid = L + ((R - L) >> 1); // 求中点
        int leftMax = process(arr, L, mid);
        int rightMax = process(arr, mid+1, R);
        return Math.max(leftMax, rightMax);
    }
}
