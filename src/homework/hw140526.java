package homework;

class Stack{
    private int tail;
    private int[] array;

    public Stack(int[] array){
        this.array = new int[array.length];
        for (int i = 0; i < array.length; i++)
            this.array[i] = array[i];
        this.tail = array.length;
    }

    public boolean isEmpty() {
        if (tail == 0)
            return true;
        return false;
    }

    public int getTail() {
        if (!isEmpty())
            return array[tail - 1];
        throw new IllegalArgumentException("Error: Array is empty\n");
    }

    public int[] getArray() {
        int[] array = new int[tail];
        for (int i = 0; i < tail; i++)
            array[i] = this.array[i];
        return array;
    }

    private void resize(){
        if (tail < this.array.length)
            return;
        int[] array = new int[this.array.length * 2 + 1];
        for (int i = 0; i < this.array.length; i++)
            array[i] = this.array[i];
        this.array = array;
    }

    public void push(int element){
        resize();
        this.array[tail] = element;
        this.tail++;
    }

    public int pop(){
        if (isEmpty()) {
            throw new IllegalStateException("Error: underflow\n");
        }
        tail--;
        return array[tail];
    }
}

class WeirdStack {
    private int top1;
    private int top2;
    private int[] array;

    public WeirdStack(int size) {
        this.array = new int[size];
        this.top1 = -1;
        this.top2 = size;
    }

    public boolean isEmptyForLeft() {
        return top1 == -1;
    }

    public boolean isEmptyForRight() {
        return top2 == array.length;
    }

    public boolean isFull() {
        return top1 + 1 == top2;
    }

    public void pushLeft(int element) {
        if (isFull()) {
            throw new IllegalStateException("Error: overflow");
        }

        top1++;
        array[top1] = element;
    }

    public void pushRight(int element) {
        if (isFull()) {
            throw new IllegalStateException("Error: overflow");
        }

        top2--;
        array[top2] = element;
    }

    public int popLeft() {
        if (isEmptyForLeft()) {
            throw new IllegalStateException("Error: left stack underflow");
        }

        int element = array[top1];
        top1--;
        return element;
    }

    public int popRight() {
        if (isEmptyForRight()) {
            throw new IllegalStateException("Error: right stack underflow");
        }

        int element = array[top2];
        top2++;
        return element;
    }

    public int peekLeft() {
        if (isEmptyForLeft()) {
            throw new IllegalStateException("Error: left stack is empty");
        }

        return array[top1];
    }

    public int peekRight() {
        if (isEmptyForRight()) {
            throw new IllegalStateException("Error: right stack is empty");
        }

        return array[top2];
    }

    public int sizeLeft() {
        return top1 + 1;
    }

    public int sizeRight() {
        return array.length - top2;
    }

    public int[] getArray() {
        int[] copy = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }

        return copy;
    }
}

class Queue {
    private int head;
    private int tail;
    private int size;
    private int[] array;

    public Queue(int[] array) {
        this.array = new int[array.length * 2 + 1];

        for (int i = 0; i < array.length; i++) {
            this.array[i] = array[i];
        }

        this.head = 0;
        this.tail = array.length;
        this.size = array.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize() {
        if (size < array.length) {
            return;
        }

        int[] newArray = new int[array.length * 2 + 1];

        for (int i = 0; i < size; i++) {
            newArray[i] = array[(head + i) % array.length];
        }

        array = newArray;
        head = 0;
        tail = size;
    }

    public void enqueue(int element) {
        resize();

        array[tail] = element;
        tail = (tail + 1) % array.length;
        size++;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Error: underflow");
        }

        int element = array[head];
        head = (head + 1) % array.length;
        size--;

        return element;
    }

    public int start() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Error: Array is empty\n");
        }

        return array[head];
    }

    public int[] getArray() {
        int[] result = new int[size];

        for (int i = 0; i < size; i++) {
            result[i] = array[(head + i) % array.length];
        }

        return result;
    }
}

class Deque {
    private int head;
    private int tail;
    private int size;
    private int[] array;

    public Deque(int capacity) {
        this.array = new int[capacity];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

    public int size() {
        return size;
    }

    public void insertFront(int element) {
        if (isFull()) {
            throw new IllegalStateException("Error: overflow");
        }

        head = (head - 1 + array.length) % array.length;
        array[head] = element;
        size++;
    }

    public void insertBack(int element) {
        if (isFull()) {
            throw new IllegalStateException("Error: overflow");
        }

        array[tail] = element;
        tail = (tail + 1) % array.length;
        size++;
    }

    public int deleteFront() {
        if (isEmpty()) {
            throw new IllegalStateException("Error: underflow");
        }

        int element = array[head];
        head = (head + 1) % array.length;
        size--;

        return element;
    }

    public int deleteBack() {
        if (isEmpty()) {
            throw new IllegalStateException("Error: underflow");
        }

        tail = (tail - 1 + array.length) % array.length;
        int element = array[tail];
        size--;

        return element;
    }

    public int[] getArray() {
        int[] result = new int[size];

        for (int i = 0; i < size; i++) {
            result[i] = array[(head + i) % array.length];
        }

        return result;
    }
}

class QueueUsingTwoStacks {
    private Stack s1;
    private Stack s2;

    public QueueUsingTwoStacks() {
        this.s1 = new Stack(new int[0]);
        this.s2 = new Stack(new int[0]);
    }

    public boolean isEmpty() {
        return s1.isEmpty() && s2.isEmpty();
    }

    public void enqueue(int element) {
        s1.push(element);
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Error: underflow");
        }

        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }

        return s2.pop();
    }
}

class StackUsingTwoQueues {
    private Queue q1;
    private Queue q2;

    public StackUsingTwoQueues() {
        this.q1 = new Queue(new int[0]);
        this.q2 = new Queue(new int[0]);
    }

    public boolean isEmpty() {
        return q1.isEmpty();
    }

    public void push(int element) {
        q1.enqueue(element);
    }

    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Error: underflow");
        }

        while (q1.size() > 1) {
            q2.enqueue(q1.dequeue());
        }

        int element = q1.dequeue();

        Queue temp = q1;
        q1 = q2;
        q2 = temp;

        return element;
    }
}

public class hw140526 {
    /*
        Exercise 10.1-1

        Initially
        array: [0, 0, 0, 0, 0, 0]
        top: 0

        Push(array, 4)
        array: [4, 0, 0, 0, 0, 0]
        top: 1

        Push(array, 1)
        array: [4, 1, 0, 0, 0, 0]
        top: 2

        Push(array, 3)
        array: [4, 1, 3, 0, 0, 0]
        top: 3

        Pop(array)
        array: [4, 1, (3), 0, 0, 0]
        top: 2

        Push(array, 8)
        array: [4, 1, 8, 0, 0, 0]
        top: 3

        Pop(array)
        array: [4, 1, (8), 0, 0, 0]
        top: 2


        Exercise 10.1-3

        Initially
        array: [0, 0, 0, 0, 0, 0]
        head: 1
        tail: 1

        Enqueue(Q, 4)
        array: [4, 0, 0, 0, 0, 0]
        head: 1
        tail: 2

        Enqueue(Q, 1)
        array: [4, 1, 0, 0, 0, 0]
        head: 1
        tail: 3

        Enqueue(Q, 3)
        array: [4, 1, 3, 0, 0, 0]
        head: 1
        tail: 4

        Dequeue(Q)
        array: [(4), 1, 3, 0, 0, 0]
        head: 2
        tail: 4

        Enqueue(Q, 8)
        array: [(4), 1, 3, 8, 0, 0]
        head: 2
        tail: 5

        Dequeue(Q)
        array: [(4), (1), 3, 8, 0, 0]
        head: 3
        tail: 5
     */
}


