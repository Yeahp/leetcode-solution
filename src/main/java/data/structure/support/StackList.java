package data.structure.support;

import java.util.LinkedList;

/**
 * 基于LinkedList实现栈
 * 在LinkedList实力中只选择部分基于栈实现的接口
 *
 * push-----addFirst()
 * pop------removeFirst()
 * peek-----getFirst()
 * empty----isEmpty()
 */
public class StackList<E> {
    private LinkedList<E> ll = new LinkedList<E>();

    //入栈
    public void push(E e){
        ll.addFirst(e);
    }

    //查看栈顶元素但不移除
    public E peek(){
        return ll.getFirst();
    }

    //出栈
    public E pop(){
        return ll.removeFirst();
    }

    //判空
    public boolean empty(){
        return ll.isEmpty();
    }

    //打印栈元素
    public String toString(){
        return ll.toString();
    }

    public static void main(String[] args) {
        StackList<Integer> stack=new StackList<Integer>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
        System.out.println(stack.toString());
        for (int i = 0; i < 5; i++) {
            System.out.print(stack.pop()+" ");
        }
    }
}
