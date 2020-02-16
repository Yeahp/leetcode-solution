package SwordCode;

import java.util.Stack;

public class ImplementQueueViaTwoStacks {

    // 核心思想： stack1 用于顺序存储数据进入; stack2 用于逆序存储数据进入
    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();

    public boolean appendTail(int ele) {
        if (stack2.empty()) {
            stack1.push(ele);
        } else {
            while (!stack2.empty()) {
                stack1.push(stack2.pop());
            }
            stack1.push(ele);
        }
        return true;
    }

    public Integer deleteHead() {
        if (stack1.empty() && stack2.empty()) return null;
        if (stack1.empty()) {
            stack2.pop();
        } else {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

}
