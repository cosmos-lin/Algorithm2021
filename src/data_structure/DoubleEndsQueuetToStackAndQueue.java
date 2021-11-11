package data_structure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 用双链表实现栈和队列
 */
public class DoubleEndsQueuetToStackAndQueue {

    public static class DoubleNode<T> {
        public T t;
        public DoubleNode<T> last;
        public DoubleNode<T> next;

        public DoubleNode(T t) {
            this.t = t;
        }
    }

    public static class DoubleEndsQueue<T> {
        public DoubleNode<T> head;
        public DoubleNode<T> tail;

        public void addFromHead(T value) {
            DoubleNode<T> cur = new DoubleNode<>(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                cur.next = head;
                head.last = cur;
                head = cur;
            }
        }

        public void addFromBottom(T value) {
            DoubleNode<T> cur = new DoubleNode<>(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                head.next = cur;
                cur.last = head;
                head = cur;
            }
        }

        public T popFromBottom() {
            if (head == null) return null;
            
            DoubleNode<T> cur = tail;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.last;
                tail.next = null;
                cur.last = null;
            }
            return cur.t;
        }

        public T popFromHead() {
            if (head == null) return null;
            
            DoubleNode<T> cur = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.last = null;
                cur.next = null;
            }
            return cur.t;
        }

        public boolean isEmpty() {
            return head == null;
        }
    }


    // 实现stack
    public static class MyStack<T> {
          private DoubleEndsQueue<T> queue;

          public MyStack() {
              queue = new DoubleEndsQueue<>();
          }

          public void push(T value) {
              queue.addFromHead(value);
          }

          public T pop() {
              return queue.popFromHead();
          }

          public boolean isEmpty() {
              return queue.isEmpty();
          }
    }

    // 实现 queue
    public static class MyQueue<T> {
        private DoubleEndsQueue<T> queue;

        public MyQueue() {
            queue = new DoubleEndsQueue<>();
        }

        public void push(T value) {
            queue.addFromHead(value);
        }

        public T pop() {
            return queue.popFromBottom();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
  }

    public static boolean isEqual(Integer o1, Integer o2) {
        if ((o1 != null && o2 == null) || (o1 == null && o2 != null)) return false;

        if (o1 == null && o2 == null) return true;
        return o1.equals(o2);
    }

    public static void main(String[] args) {
        int oneTestDataNum = 100;
        int value = 10000;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            MyStack<Integer> myStack = new MyStack<>();
            MyQueue<Integer> myQueue = new MyQueue<>();
            Stack<Integer> stack =  new Stack<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTestDataNum; j++) {
                int nums = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myStack.push(nums);
                    stack.push(nums);
                } else {
                    if (Math.random() < 0.5) {
                        myStack.push(nums);
                        stack.push(nums);
                    } else {
                        if (!isEqual(myStack.pop(), stack.pop())) {
                            System.out.println("myStatck: Bad!!!");
                            break;
                        }
                    }
                }

                int numq = (int) (Math.random() * value);
                if (queue.isEmpty()) {
                    myQueue.push(numq);
                    queue.offer(numq);
                } else {
                    if (Math.random() < 0.5) {
                        myQueue.push(numq);
                        queue.offer(numq);
                    } else {
                        if (!isEqual(myQueue.pop(), queue.poll())) {
                            System.out.println("myQueue: Bad!!!");
                            break;
                        }
                    }
                }
            }
        }

        System.out.println("finish!");
    }
}