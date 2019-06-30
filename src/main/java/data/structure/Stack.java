package data.structure;

/**
 * ordered stack implemented based on array
 * @param <E>
 *
 * top: pointer of the top of a stack, initially set -1
 * get into stack：data[++top] = e
 * get out of stack：(E)data[top--]
 * obtain the top element：(E)data[top]
 * empty stack：top=-1
 */
public class Stack<E> {
    private Object[] data = null;
    private int maxSize = 0;   //the size of tack
    private int top = -1;  //the pointer of the top of stack

    /**
     * the constructor
     */
    public Stack(){
        this(99); // default size: 99
    }

    public Stack(int initialSize){
        if (initialSize >= 0) {
            this.maxSize = initialSize;
            data = new Object[initialSize];
            top = -1;
        } else {
            throw new RuntimeException("Initial size mustn't less than 0：" + initialSize);
        }
    }

    // get into stack, the top element: top = 0
    public boolean push(E e) {
        if (top == maxSize - 1) {
            throw new RuntimeException("The stack is full, and no element can get in！");
        } else {
            data[++top] = e;
            return true;
        }
    }

    // display the top element without deletion
    public E peek(){
        if (top == -1) {
            throw new RuntimeException("The stack is empty！");
        } else {
            return (E)data[top];
        }
    }

    // pop the top element
    public E pop(){
        if (top == -1) {
            throw new RuntimeException("The stack is empty！");
        } else {
            E ele = (E)data[top];
            Object[] _data = new Object[top];
            for (int i = 0; i < top; i++) {
                _data[i] = data[i];
            }
            data = _data;
            top--;
            return ele;
        }
    }

    // judge whether the stack is empty
    public boolean empty() {
        return top == -1 ? true : false;
    }

    // return the index of a given element, index based on 1
    public int search(E e) {
        int result = top;
        while(result != -1) {
            if(data[result] != e){
                result--;
            } else {
                return result + 1;
            }
        }
        return result; // -1 means not found
    }

}
