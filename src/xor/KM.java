package xor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 一个数组中有一种数出现K次，其他数都出现了M次，已知M > 1，K < M，找到出现了K次的数
 * 要求额外空间复杂度O(1)，时间复杂度O(N)
 */
public class KM {
    
    // 辅助map
    public static HashMap<Integer, Integer> map = new HashMap<>();

    // 保证arr中， 只有一种数出现K次， 其它数出现了M次
    public static int onlyKTimes(int[] arr, int k, int m) {
        // 初始化map
        if (map.size() == 0) {
            mapCrreater(map);
        }
        // 对于arr中的所有数， 统计二进制上每一位上1的个数
        int[] help = new int[32];
        for (int num : arr) {
            while (num != 0) {

                // 提取最右侧的1
                int rightOne = num & (-num);
                // 最右侧的1 当前1所在的位 + 1
                help[map.get(rightOne)]++; 
                num ^= rightOne;
            }
        }

        int ans = 0;
        // 遍历help 中每位上的数，进行取模运算，为k的说明：出现k次的数上有该位置上的1
        for (int i = 0; i < help.length; i++) {
            if (help[i] % m != 0) {
                if (help[i] % m == k) {
                    ans |= (1 << i);
                } else { // 说明help[i] 出现的次数不为m, 也不为k
                    return -1;
                }
            }
        }

        // arr 中出现的次数为k的可能为0
        if (ans == 0) {
            int count = 0;
            for (int num : arr) {
                if (num == 0) {
                    count++;
                }
            }
            if (count != k) {
                return -1;
            }
        }

        return ans;
    }

    //map -> {1:1, 2:2, 4:3, 8:4} 
    public static void mapCrreater(HashMap<Integer, Integer> map) {

        int key = 1;
        for (int i = 0; i < 32; i++) {
            map.put(key, i);
            key <<= 1;
        }
    }


    public static int violence(int[] arr, int k, int m) {
        HashMap<Integer, Integer> hmMap = new HashMap<>();
        for (int num : arr) {
            if (hmMap.containsKey(num)) {
                hmMap.put(num, hmMap.get(num) + 1);
            } else {
                hmMap.put(num, 1);
            }
        }

        for (int num : hmMap.keySet()) {
            if (hmMap.get(num) == k) {
                return num;
            }
        }
        return -1;
    }

    public static int[] randomArray(int maxKinds, int range, int k, int m) {
		int ktimeNum = randomNumber(range);
		
		int times = Math.random() < 0.5 ? k : ((int) (Math.random() * (m - 1)) + 1);
		// 2
		int numKinds = (int) (Math.random() * maxKinds) + 2;
		// k * 1 + (numKinds - 1) * m
		int[] arr = new int[times + (numKinds - 1) * m];
		int index = 0;
		for (; index < times; index++) {
			arr[index] = ktimeNum;
		}
		numKinds--;
		HashSet<Integer> set = new HashSet<>();
		set.add(ktimeNum);
		while (numKinds != 0) {
			int curNum = 0;
			do {
				curNum = randomNumber(range);
			} while (set.contains(curNum));
			set.add(curNum);
			numKinds--;
			for (int i = 0; i < m; i++) {
				arr[index++] = curNum;
			}
		}
		// arr 填好了
		for (int i = 0; i < arr.length; i++) {
			// i 位置的数，我想随机和j位置的数做交换
			int j = (int) (Math.random() * arr.length);// 0 ~ N-1
			int tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
		}
		return arr;
	}

	// [-range, +range]
	public static int randomNumber(int range) {
		return ((int) (Math.random() * range) + 1) - ((int) (Math.random() * range) + 1);
	}

	public static void main(String[] args) {
		int kinds = 5;
		int range = 30;
		int testTime = 100000;
		int max = 9;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int a = (int) (Math.random() * max) + 1; // a 1 ~ 9
			int b = (int) (Math.random() * max) + 1; // b 1 ~ 9
			int k = Math.min(a, b);
			int m = Math.max(a, b);
			// k < m
			if (k == m) {
				m++;
			}
			int[] arr = randomArray(kinds, range, k, m);
			int ans1 = violence(arr, k, m);
			int ans2 = onlyKTimes(arr, k, m);
			if (ans1 != ans2) {
                System.out.println("出现 " + k + " 次的数：");
                System.out.println(Arrays.toString(arr));
				System.out.println(ans1);
				System.out.println(ans2);
				System.out.println("出错了！");
                break;
			}
		}
		System.out.println("测试结束");

	}
}
