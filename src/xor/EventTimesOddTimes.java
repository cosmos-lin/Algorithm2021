package xor;
/**
 * 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
 * 怎么把一个int类型的数，提取出二进制中最右侧的1来
 * 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
 */
public class EventTimesOddTimes {
    
    // arr中，只有一种数，出现奇数次
    public static void printOnlyOneTimesNumber(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
         System.out.println(eor);;
    }

    // arr中，只有2种数，出现奇数次
    public static void printOneTimesNumber(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        // 若 a, b 出现奇数次; eor = a ^ b


        // 提取最右侧的1 -> 对于 a 或 b  有一个数是含有最右侧1
        int rightOne = eor & (-eor); // -eor <=> ~eor + 1

        int onlyOne = 0;

        // 对于整个数组， 含有最右侧1的数必定为奇数
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & rightOne) != 0) {
                onlyOne ^= arr[i];
            }
        }
        System.out.println(onlyOne + "--" + (onlyOne ^ eor));

    }

    
	public static void main(String[] args) {

		int[] arr1 = { 3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1 };
		printOnlyOneTimesNumber(arr1);

		int[] arr2 = { 4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2 };
		printOneTimesNumber(arr2);

	}
}
