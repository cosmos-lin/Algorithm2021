package xor;

import java.util.Arrays;

/**
 * 不用格外变量交换两个数
 */

public class Swap {
    
    public static void main(String[] args) {
        
        int a = 10;
        int b = 100;

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a);
        System.out.println(b);

        int[] arr =  new int[]{10, 100};
        int i = 0;
        int j = 1;
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
        System.out.println(Arrays.toString(arr));
        
        // 注意同一位置的数指向同一个内存地址， 无法异或交换，
        arr =  new int[]{99, 999};
        i = 0;
        j = 0;
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
        System.out.println(Arrays.toString(arr)); // arr[0] = 0

        int s = 3;
        System.out.println(s & (-s));
    }
}
