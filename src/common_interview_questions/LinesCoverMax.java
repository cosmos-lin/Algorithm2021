package common_interview_questions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;


public class LinesCoverMax {
    
    public static int violence(int[][] lines) {

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < lines.length; i++) {
            max = Math.max(max, lines[i][1]);
            min = Math.min(min, lines[i][0]);
        }
        int maxCover = 0;
        for (double p = min + 0.5; p < max; p += 1) {
            int cur = 0;
            for (int j = 0; j < lines.length; j++) {
                if (lines[j][0] < p && lines[j][1] > p) {
                    cur++;
                }
            }
            maxCover = Math.max(maxCover, cur);
        }
        return maxCover;
    }

    public static int maxConver(int[][] arr) {

        Line[] lines = new Line[arr.length];
        for (int i = 0; i < arr.length; i++) {
            lines[i] = new Line(arr[i][0], arr[i][1]);
        }

        Arrays.sort(lines, new StartComparator());

        // 小根堆: 放每个线段结束的值end
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int maxConver = 0;
        for (int i = 0; i < lines.length; i++) {
            while (!heap.isEmpty() && heap.peek() <= lines[i].start) {
                heap.poll();
            }
            heap.add(lines[i].end);
            maxConver = Math.max(maxConver, heap.size());
        }

        return maxConver;
    }

    public static class Line {
        public int start;
        public int end;

        public Line(int s, int e) {
            start = s;
            end = e;
        }
    }

    public static class StartComparator implements Comparator<Line> {
    
        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    public static class EndComparator implements Comparator<Line> {
    
        @Override
        public int compare(Line o1, Line o2) {
            return o1.end - o2.end;
        }
    }

    // O(N*logN)
    public static int maxCover1(int[][] lines) {
        // m是二维数组，可以认为m内部是一个一个的一维数组
        // 每一个一维数组就是一个对象，也就是线段
        // 如下的code，就是根据每一个线段的开始位置排序
        // 比如, m = { {5,7}, {1,4}, {2,6} } 跑完如下的code之后变成：{ {1,4}, {2,6}, {5,7} }
        Arrays.sort(lines, (a, b) -> (a[0] - b[0]));

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int maxCover = 0;
        for (int[] line : lines) {
            while (!heap.isEmpty() && heap.peek() <= line[0]) {
                heap.poll();
            }
            heap.add(line[1]);
            maxCover = Math.max(maxCover, heap.size());
        }

        return maxCover;
    }

    // for test
    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) new Random().nextInt(N) + 1;
        int[][] ans  = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a =  L + new Random().nextInt(R - L + 1);
            int b =  L + new Random().nextInt(R - L + 1);
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println("test begin!");

        int N = 10;
        int L = 0;
        int R = 10;
        int testTimes = 200;
        for (int i = 0; i < testTimes; i++) {
                int[][] lines = generateLines(N, L, R);
                // for (int[] line : lines) {
                //     System.out.println(Arrays.toString(line));
                
                // }
                int ans = violence(lines);
                int ans1 = maxConver(lines);
                int ans2 = maxCover1(lines);
                if (ans != ans1 || ans1 != ans2) {
                    System.out.println("bad!");
                    System.out.println(ans);
                    System.out.println(ans1);
                    System.out.println(ans2);
                    break;
                }
        }

        System.out.println("test end!");
    }
}
