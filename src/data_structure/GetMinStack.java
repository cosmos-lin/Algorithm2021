package data_structure;

import java.util.Stack;

/**
 * 实现有getMin功能的栈
 */
public class GetMinStack {
    
    public static class MyStack {

        // 准备 两个栈； 一个数据栈， 一个最小值栈
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack() {
            stackData = new Stack<>();
            stackMin = new Stack<>();
        }

        public void push(int num) {
            
            // 判断stackMIn 是否为空
            if (stackMin.isEmpty()) {
                stackMin.add(num);
            } else if ((stackMin.peek() < num)) {
                stackMin.add(stackMin.peek());
            } else {
                stackMin.add(num);
            }
            stackData.add(num);
        }

        public int pop() {
            if (stackData.isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            stackMin.pop();
            return stackMin.pop();
        }

        public int getMin() {
            if (stackMin.isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            return stackMin.peek();
        }
    }

    /**
     * 压栈： num<= 最小栈顶时， minStack.push(num)
     * 出栈： stack == minStack.peek() , minStack.pop()
     */
    public static class MyStack1 {

        public Stack<Integer> stackData;
        public Stack<Integer> stackMin;

        // 初始化
        public MyStack1() {
            stackData = new Stack<>();
            stackMin = new Stack<>();
        }

        public void push(int num) {
            if (stackMin.isEmpty()) {
                stackMin.push(num);
            } else if (num <= this.getMin()) {
                stackMin.push(num);
            }
            stackData.push(num);
        }

        public int pop() {
            if (stackData.isEmpty()) {
                throw new RuntimeException("stack is empty!");
            }
            int ans = stackData.pop();
            if (ans == this.getMin()) {
                stackMin.pop();
            }
            return ans;
        }

        public int getMin() {
            if (stackMin.isEmpty()) {
                throw new RuntimeException("min stack is empty!");
            }
            return stackMin.peek();
        }
    }

    public static void main(String[] args) {
		MyStack stack1 = new MyStack();
		stack1.push(3);
		System.out.println(stack1.getMin());
		stack1.push(4);
		System.out.println(stack1.getMin());
		stack1.push(1);
		System.out.println(stack1.getMin());
		System.out.println(stack1.pop());
		System.out.println(stack1.getMin());

		System.out.println("=============");

		MyStack1 stack2 = new MyStack1();
		stack2.push(3);
		System.out.println(stack2.getMin());
		stack2.push(4);
		System.out.println(stack2.getMin());
		stack2.push(1);
		System.out.println(stack2.getMin());
		System.out.println(stack2.pop());
		System.out.println(stack2.getMin());
	}
}
