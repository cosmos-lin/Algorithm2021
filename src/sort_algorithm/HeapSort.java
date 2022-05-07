package sort_algorithm;

import java.util.Arrays;
import java.util.Random;

public class HeapSort {
    
    public static void heapSort(int[] arr) {

        if (null == arr || arr.length == 1) {
            return;
        }

        // 步骤1：遍历arr；在arr上形成大根堆结构
        // O(N*logN)
        // for (int i = 0; i < arr.length; i++) {
        //     heapInsert(arr, i);
        // }
        
        // O(N)
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }

        // 步骤2，3：
        int heapSize = arr.length;
        while(heapSize > 0) {
            // 交换大根堆根节点与最后子节点位置；heapSize-1
            swap(arr, 0, --heapSize);
            // 交换后根节点位置的数，从该位置出发，下沉操作
            heapify(arr, 0, heapSize);
        }
    }

    public static void heapInsert(int[] arr, int index) {
        // 新增节点；维持大根堆结构
        while (arr[index] > arr[(index-1)/2]) {
            swap(arr, index, (index-1)/2);
            index = (index - 1) / 2;
        }
    }

    public static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    // 替换后的节点破坏原有大根堆的结构；进行heapify下沉操作
    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index << 1 | 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;

            if (arr[index] > arr[largest]) {
                break;
            }

            swap(arr, index, largest);
            index = largest;
            left = index << 1 | 1;
        }
    }
    
    public static void violence(int[] arr) {
        Arrays.sort(arr);
    }

    // 随机生成数组
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[new Random().nextInt(maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            // [-n, n]
            arr[i] = new Random().nextInt(maxSize) - new Random().nextInt(maxSize);
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }

        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
            
        }
        return res;
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 != null) {
            return false;
        }

        if (arr1 != null && arr2 == null) {
            return false;
        }

        if (arr1 == null && arr2 == null) {
            return true;
        }

        if (arr1.length != arr2.length) {
            return false;
        }

        for (int i = 0; i < arr2.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
        }
            }

        return true;
    }

    public static void main(String[] args) {
        
        int maxTimes = 100000;
        int maxValue = 100;
        int maxSize = 100;
        System.out.println("test begin...");
        boolean success = true;
        for (int i = 0; i < maxTimes; i++) {
            
            int[] arr = generateRandomArray(maxSize, maxValue);
            int[] test = copyArray(arr);

            heapSort(arr);
            violence(test);
            if (!isEqual(arr, test)) {
                success = false;
                break;
            }
        }

        System.out.println(success ? "Nice!" : "Bad!");
        System.out.println("test finish!");

    }
}
