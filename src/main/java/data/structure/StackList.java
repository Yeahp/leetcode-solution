package data.structure;

import java.util.LinkedList;

/**
 * implement stack based LinkedList
 * this type of stack is somehow a linked stack
 *
 * push: addFirst()
 * pop: removeFirst()
 * peek: getFirst()
 * empty: isEmpty()
 */
public class StackList<E> {
    private LinkedList<E> linkedList = new LinkedList<E>();

    // get into stack
    public void push(E e){
        linkedList.addFirst(e);
    }

    // display the top element without deletion
    public E peek(){
        return linkedList.getFirst();
    }

    // get out of stack
    public E pop(){
        return linkedList.removeFirst();
    }

    // judge whether the stack is empty
    public boolean empty(){
        return linkedList.isEmpty();
    }

    // print all elements
    public String toString(){
        return linkedList.toString();
    }

}
