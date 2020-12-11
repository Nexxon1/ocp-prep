package de.mino.chapter3.collections.queue;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueueMethods {

  private static void queueMethods() {
    System.out.println("Working with a queue - FIFO");
    Queue<Integer> queue = new ArrayDeque<>();
    System.out.println(
        "Offer adds an elem to the back. \nPeek returns the head of the queue \nPoll removes the head of the queue");
    System.out.println(queue.offer(10)); // true
    System.out.println(queue.offer(4)); // true
    System.out.println(queue); // [10, 4]
    System.out.println(queue.peek()); // 10
    System.out.println(queue.peek()); // 10
    System.out.println(queue.poll()); // 10
    System.out.println(queue.poll()); // 4
    System.out.println(queue); // []
    System.out.println(queue.peek()); // null
    System.out.println(queue.poll()); // null
  }

  private static void arrayDequeMethods() {
    System.out.println("\nWorking with a Stack (ArrayDeque) - LIFO");
    ArrayDeque<Integer> stack = new ArrayDeque<>();
    System.out.println(
        "Push adds an elem to the front. \nPeek returns the head of the queue \nPoll removes the head of the queue");
    stack.push(10); // true
    stack.push(4); // true
    System.out.println(stack); // [4, 10]
    System.out.println(stack.peek()); // 4
    System.out.println(stack.peek()); // 4
    System.out.println(stack.poll()); // 4
    System.out.println(stack.poll()); // 10
    System.out.println(stack); // []
    System.out.println(stack.peek()); // null
    System.out.println(stack.poll()); // null
  }

  public static void main(String[] args) {
    queueMethods();
    arrayDequeMethods();
  }
}
