package lesson_2026_04_03.homework;

class Node {
    int data;
    Node next;
    Node prev;

    Node(int data) {
        this.data = data;
        next = null;
        prev = null;
    }

    Node(int data, Node next, Node prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }
}

class LinkedList {
    Node head;
    int size;

    boolean isEmpty() {
        return head == null;
    }

    void add(int data, int index) {
        if (!(index >= 0 && index <= size)) {
            throw new IllegalArgumentException("LinkedList index is out of range:\n");
        }

        Node newNode = new Node(data);

        if (index == 0) {
            newNode.next = head;
            if (head != null) {
                head.prev = newNode;
            }
            head = newNode;
            size++;
            return;
        }

        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        newNode.next = current.next;
        newNode.prev = current;

        if (current.next != null) {
            current.next.prev = newNode;
        }

        current.next = newNode;
        size++;
    }

    void delete(int index) {
        if (!(index >= 0 && index < size)) {
            throw new IllegalArgumentException("LinkedList index is out of range:\n");
        }

        if (index == 0) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
            size--;
            return;
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        current.prev.next = current.next;
        if (current.next != null) {
            current.next.prev = current.prev;
        }

        size--;
    }

    int find(int data) {
        Node current = head;
        int index = 0;

        while (current != null) {
            if (current.data == data) {
                return index;
            }
            current = current.next;
            index++;
        }

        return -1;
    }

    void print() {
        Node current = head;

        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}

class Stack {
    int[] array;
    int top;

    Stack() {
        array = new int[0];
    }

    int size() {
        return array.length;
    }

    int top() {
        if (size() == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        return top;
    }

    void resize(boolean delete) {
        if (!delete) {
            int[] newArray = new int[array.length + 1];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        } else {
            int[] newArray = new int[array.length - 1];
            for (int i = 0; i < array.length - 1; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
    }

    void push(int value) {
        resize(false);
        array[array.length - 1] = value;
        top = value;
    }

    int pop() {
        if (size() == 0) {
            throw new IllegalStateException("Stack is empty");
        }

        int removed = array[array.length - 1];
        resize(true);

        if (size() > 0) {
            top = array[array.length - 1];
        }

        return removed;
    }
}

class Queue {
    int[] array;
    int head;
    int tail;

    Queue() {
        array = new int[0];
    }

    int size() {
        return array.length;
    }

    int head() {
        if (size() == 0) {
            throw new IllegalStateException("Queue is empty");
        }
        return head;
    }

    int tail() {
        if (size() == 0) {
            throw new IllegalStateException("Queue is empty");
        }
        return tail;
    }

    void resize(boolean delete) {
        if (!delete) {
            int[] newArray = new int[array.length + 1];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        } else {
            int[] newArray = new int[array.length - 1];
            for (int i = 0; i < array.length - 1; i++) {
                newArray[i] = array[i + 1];
            }
            array = newArray;
        }
    }

    void enqueue(int value) {
        resize(false);
        array[array.length - 1] = value;

        head = array[0];
        tail = array[array.length - 1];
    }

    int dequeue() {
        if (size() == 0) {
            throw new IllegalStateException("Queue is empty");
        }

        int removed = array[0];
        resize(true);

        if (size() > 0) {
            head = array[0];
            tail = array[array.length - 1];
        }

        return removed;
    }
}

public class ElementaryDataStructures {
    public static void main(String[] args) {

        System.out.println("Happy Birthday Git!");

        Stack s = new Stack();
        s.push(10);
        s.push(20);
        s.push(30);

        System.out.println("Stack top: " + s.top());   // 30
        System.out.println("Pop: " + s.pop());         // 30
        System.out.println("Stack top: " + s.top());   // 20

        Queue q = new Queue();
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);

        System.out.println("Queue head: " + q.head()); // 10
        System.out.println("Queue tail: " + q.tail()); // 30
        System.out.println("Dequeue: " + q.dequeue()); // 10
        System.out.println("Queue head: " + q.head()); // 20
    }
}