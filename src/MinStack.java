import java.util.Stack;

/**
    * Created by ivy on 12/14/14.
    */

class MinStack {
    Stack s1;
    Stack s2;
    MinStack(){
        this.s1 = new Stack();
        this.s2 = new Stack();
    }
    public void push(int x) {
        s1.push(x);
        if(s2.isEmpty() || x <= (int) s2.peek()){
            s2.push(x);
        }
    }

    public void pop() {
        int val = (int) s1.pop();
        if(val == (int) s2.peek()){
            s2.pop();
        }
    }

    public int top() {
        return (int) s1.peek();
    }

    public int getMin() {
        return (int) s2.peek();
    }
}

