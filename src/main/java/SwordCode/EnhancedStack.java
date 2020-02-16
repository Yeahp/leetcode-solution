package SwordCode;

import java.util.Stack;

public class EnhancedStack {

    // 定义栈结构, 满足调用 min、pop、push 函数的时间复杂度均为 o(1)
    private Stack<Integer> stack1 = new Stack<>();  // 用于存储一次进入的数据
    private Stack<Integer> stack2 = new Stack<>();  // 用于存储最小值

    public Integer min() {
        if (!stack2.empty()) return stack2.peek();
        return null;
    }

    public void push(int ele) {
        stack1.push(ele);
        if (stack2.empty()) {
            stack2.push(ele);
        } else {
            if (stack2.peek() < ele) stack2.push(stack2.peek());
            else stack2.push(ele);
        }
    }

    public Integer pop() {
        if (!stack1.empty()) {
            stack2.pop();
            return stack1.pop();
        }
        return null;
    }

}
