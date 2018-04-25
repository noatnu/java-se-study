package Interview.a5;

import java.util.ArrayDeque;
import java.util.Deque;

public class ConnTEST {
    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<Integer>();//后进先出
        stack.push(2);
        stack.push(7);
        System.out.println(stack.pop());
    }
}
