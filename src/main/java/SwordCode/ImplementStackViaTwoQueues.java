package SwordCode;

import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackViaTwoQueues {

    // 核心思想：queue1和 queue2均存储数据顺序进入
    // 进栈：直接追加到队尾
    // 出栈：将非空队列依次出队进入空队列，返回最后一个元素
    private Queue<Integer> queue1 = new LinkedList<>();
    private Queue<Integer> queue2 = new LinkedList<>();

    public boolean add(int ele) {
        if (queue1.size() != 0) queue1.add(ele);
        else queue2.add(ele);
        return true;
    }

    public Integer poll() {
        if (queue1.size() == 0 && queue2.size() == 0) return null;
        if (queue1.size() > 0) {
            while (queue1.size() > 1) {
                queue2.add(queue1.poll());
            }
            return queue1.poll();
        } else {
            while (queue2.size() > 1) {
                queue1.add(queue2.poll());
            }
            return queue2.poll();
        }
    }

}
