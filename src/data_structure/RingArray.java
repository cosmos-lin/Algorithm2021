package data_structure;

/**
 * 用环形数组实现栈和队列
 */
public class RingArray {

    public static class Myqueue {
        private int[] arr;
        private int pushi; // 往数组添加元素指针
        private int polli; // 往数组拿取元素指针
        private int size;
        private final int limit; // 数组限制

        public Myqueue(int limit) {
            arr = new int[limit];
            pushi = 0;
            polli = 0;
            size = 0;
            this.limit = limit;
        }

        public void push(int value) {
            // 判断当前队列是否满了
            if (size == limit) {
                throw new RuntimeException("队列满了， 不能再加了");
            }
            arr[pushi] = value;
            size++;
            // 注意： 这里环形数据， 当指针到达边界时需要置0
            pushi = nextIndex(pushi);
        }

        public int pop() {
            // 判断 队列是否为空
            if (size == 0) {
                throw new RuntimeException("队列为空，没有数据");
            }
            int ans = arr[polli];
            size--;
            polli = nextIndex(polli);
            return ans;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        /**
         *  现在下标为index, 返回下一个位置
         * @param index
         * @return
         */
        public int nextIndex(int index) {
            return index < limit - 1 ? index + 1 : 0;
        }



    }


}
