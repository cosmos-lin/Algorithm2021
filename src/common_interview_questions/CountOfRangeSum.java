package common_interview_questions;

// 力扣： https://leetcode-cn.com/problems/count-of-range-sum/
public class CountOfRangeSum {
    
    public static int countRangeSum(int[] nums, int lower, int upper) {
        // 参数判断
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 求nums 前缀和
        long[] sum = new long[nums.length];

        sum[0] = nums[0];
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i-1] + nums[i];
        }

        // 递归求解
        return process(sum, 0, sum.length-1, lower, upper);
    }

    public static int process(long[] sum, int L, int R, int lower, int upper) {

        // base case L==R
        if (L==R) {
            return sum[L] >= lower && sum[L] <= upper ? 1 : 0;
        }

        // sum不只1个数时候
        // 中点
        int M = L + ((R - L) >> 1);
        // 返回左右两边和merge结果
        return process(sum, L, M, lower, upper) + process(sum, M+1, R, lower, upper) + merge(sum, L, M, R, lower, upper);

    }

    public static int merge(long[] sum, int L, int M, int R, int lower, int upper) {

        int ans = 0;
        // 定义窗口起始大小
        int windowL = L;
        int windowR = L;

        // 转换：对于右边的数组求左边数组每个累加和在( sum[i] - upper, sum[i] - lower)范围
        for (int i = M+1; i <= R; i++) {
            // 定义范围
            long min = sum[i] - upper;
            long max = sum[i] - lower;
            // 窗口左边移动到符合位置
            while (windowL <= M && sum[windowL] < min) {
                windowL++;
            }

            // 窗口右边移动到符合位置
            while (windowR <= M && sum[windowR] <= max) {
                windowR++;
            }

            ans += windowR - windowL;
        }

        // merge排序
        long[] help = new long[R - L + 1];
        int indexHelp = 0;
        int indexL = L;
        int indexR = M + 1;
        while (indexL <= M && indexR <= R) {
            help[indexHelp++] = sum[indexL] <= sum[indexR] ? sum[indexL++] : sum[indexR++];
        }
        // indexL 或者 indexR 超出限制
        while (indexL <= M) {
            help[indexHelp++] = sum[indexL++];
        }

        while (indexR <= R) {
            help[indexHelp++] = sum[indexR++];
        }

        // copy help to sum
        for (int i = 0; i < help.length; i++) {
            sum[L + i] = help[i];
        }

        return ans;
    }
}
