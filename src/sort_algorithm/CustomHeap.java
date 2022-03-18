package sort_algorithm;

import java.util.Random;

public class CustomHeap {

    private int[] heap;
    private final int limit;
    private int heapSize;

    public CustomHeap(int limit) {
        this.limit = limit;
        heap = new int[limit];
        heapSize = 0;
    }

    public void push(int value) {
        if (heapSize == limit) {
            throw new RuntimeException("heap is full!");
        }
        heap[heapSize] = value;
        heapInsert(heap, heapSize++);
    }

    // 返回最大值；并把在大根堆中把最大值删掉；剩下的数依然维持大根堆
    public int pop() {
        int ans = heap[0];
        // 逻辑删除
        swap(heap, 0, --heapSize);
        // 调整大根堆；将0位置的数下沉到合适位置
        heapify(heap, 0, heapSize);
        return ans;
    }

    public void  heapInsert(int[] arr, int index) {
        // 两层含义：index=0 不成立
        // arr[index] <= arr[(index-1) >> 1] 不成立
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index-1) / 2;
        }
    }

    // 从index位置往下看，下沉找到合适的位置
    public void heapify(int[] arr, int index, int heapSize) {

        int left = index << 1 | 1;
        // 边界： (没子节点)左子节点不能超过heapSize大小
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left+1] > arr[left] ? left + 1 : left;
            // 边界：index位置节点不比子节点小，停止下沉
            if (arr[index] >= arr[largest]) {
                break;
            }

            // index和较大的孩子互换
            swap(arr, index, largest);
            index = largest;
            left = index << 1 | 1;
        }
    }

    public boolean isEmpty() {

        return heapSize == 0;
    }

    public boolean isFull() {
        return heapSize == limit;
    }

    private void swap(int[] arr, int x, int y) {

        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }


    public static class RightMaxHeap {
        private int[] arr;
        private final int limit;
        private int size;

        public RightMaxHeap(int limit) {
            arr = new int[limit];
            this.limit = limit;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("heap is full");
            }
            arr[size++] = value;
        }

        public int pop() {
            int maxIndex = 0;
            for (int i = 1; i < size; i++) {
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }
            int ans = arr[maxIndex];
            arr[maxIndex] = arr[--size];
            return ans;
        }

    }

    public static void main(String[] args) {
        int value = 1000;
        int limit = 100;
        int testTimes = 100000;
        System.out.println("test begin!");
        for (int i = 0; i < testTimes; i++) {
            int curLimit = new Random().nextInt(limit) + 1;
            CustomHeap custom = new CustomHeap(curLimit);
            RightMaxHeap test = new RightMaxHeap(curLimit);
            int curTimes = new Random().nextInt(limit);
            for (int j = 0; j < curTimes; j++) {
                if (custom.isEmpty() != test.isEmpty()) {
                    System.out.println("isEmpty Bad!");
                    break;
                }
                if (custom.isFull() != test.isFull()) {
                    System.out.println("isFull Bad!");
                    break;
                }

                if (custom.isEmpty()) {
                    int curValue = new Random().nextInt(value);
                    custom.push(curValue);
                    test.push(curValue);
                } else if (custom.isFull()) {
                    if (custom.pop() != test.pop()) {
                        System.out.println("pop value Bad!");
                        break;
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = new Random().nextInt(value);
                        custom.push(curValue);
                        test.push(curValue);
                    } else {
//                        System.out.println(Arrays.toString(custom.heap));
//                        System.out.println(Arrays.toString(test.arr));
                        int cus = custom.pop();
                        int tests = test.pop();
//                        System.out.println(cus + "--" + tests);
                        if (cus != tests) {
                            System.out.println("pop value Bad!!!");
                            break;
                        }
                    }
                }
            }
        }

        System.out.println("finish!");
    }
}
