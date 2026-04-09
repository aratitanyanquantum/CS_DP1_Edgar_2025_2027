package lesson_2026_04_03.homework;

import java.util.ArrayList;
import java.util.Random;

class UniversalArray {
    private int size;
    private int[] array;

    public UniversalArray(){
        this(1);
    }

    public UniversalArray(int[] array){
        if (array != null) {
            this.size = array.length;
            this.array = new int[this.size];
            for (int i = 0; i < array.length; i++) this.array[i] = array[i];
        }
        else {
            this.array = new int[size];
        }
    }

    public UniversalArray(int size){
        this.array = new int[size];
        this.size = size;
    }

    public int size(){
        return this.size;
    }

    public void initArray(){
        Random random = new Random();
        for (int i = 0; i < this.size; i++) this.array[i] = random.nextInt(1, 21);
    }

    public void printArray(){
        for (int i = 0; i < this.size; i++) System.out.print(this.array[i] + " ");
        System.out.print("\n");
    }

    private static int max(int a, int b)
    {
        return (a + b + Math.abs(a-b))/2;
    }

    private static int min(int a, int b)
    {
        return (a + b - Math.abs(a-b))/2;
    }

    public int max(){
        int mx = this.array[0];
        for (int i = 0; i < this.size; i++) mx = max(mx, this.array[i]);
        return mx;
    }

    public int min(){
        int mn = this.array[0];
        for (int i = 0; i < this.size; i++) mn = min(mn, this.array[i]);
        return mn;
    }

    public boolean isPalindrome(){
        boolean answer = true;
        for (int i = 0; i < this.size / 2; i++) answer &= this.array[i] == this.array[this.size - 1 - i];
        return answer;
    }

    private void swap(int i, int j){
        int temp = this.array[i];
        this.array[i] = this.array[j];
        this.array[j] = temp;
    }

    public void bubbleSort(){
        boolean sorted = false;
        while (!sorted){
            sorted = true;
            for (int i = 1; i < this.size; i++){
                if (this.array[i] < this.array[i-1])
                {
                    swap(i, i-1);
                    sorted = false;
                }
            }
        }
    }

    public void selectionSort(){
        for (int j = 0; j < this.size; j++)
        {
            int mn = this.array[j];
            int index = j;
            for (int i = j; i < this.size; i++){
                if (this.array[i] < mn){
                    mn = this.array[i];
                    index = i;
                }
            }
            swap(j, index);
        }
    }

    public void mergeSort(){
        int[] newArray = mergeSort(0, size - 1);
        for (int i = 0; i < size; i++) this.array[i] = newArray[i];
    }

    private int[] mergeSort(int l, int r){
        if (l == r) return new int[]{this.array[l]};
        int mid = (l+r)/2;
        return merge(mergeSort(l, mid), mergeSort(mid+1, r));
    }

    private int[] merge(int[] a, int[] b){
        int[] c = new int[a.length + b.length];
        int val1 = a[0];
        int index1 = 0;
        int val2 = b[0];
        int index2 = 0;
        for (int i = 0; i < a.length + b.length; i++)
        {
            if (val1 <= val2){
                c[i] = val1;
                if (index1 + 1 == a.length) val1 = Integer.MAX_VALUE;
                else {
                    val1 = a[index1 + 1];
                    index1++;
                }
            }
            else {
                c[i] = val2;
                if (index2 + 1 == b.length) val2 = Integer.MAX_VALUE;
                else {
                    val2 = b[index2 + 1];
                    index2++;
                }
            }
        }
        return c;
    }

    public boolean checkElement(int element){
        boolean found = false;
        for (int i = 0; i < this.size; i++) found |= this.array[i] == element;
        return found;
    }

    public int mod(){
        int[] array = mergeSort(0, size - 1);
        int maximumQuantity = 1;
        int finalValue = array[0];
        int quantity = 1;
        int value = array[0];
        for (int i = 1; i < this.size; i++){
            if (array[i] == value){
                quantity++;
            }
            else{
                if (quantity > maximumQuantity){
                    maximumQuantity = quantity;
                    finalValue = value;
                }
                quantity = 1;
                value = array[i];
            }
        }
        if (quantity > maximumQuantity){
            maximumQuantity = quantity;
            finalValue = value;
        }
        return finalValue;
    }

    private void changeSize(int size){
        int[] array = new int[size];
        for (int i = 0; i < min(size, this.size); i++)
            array[i] = this.array[i];
        this.array = array;
    }

    public void addElement(int element){
        changeSize(this.size * 2);
        this.array[this.size] = element;
        this.size++;
    }

    public void deleteElement(){
        if (this.size == 0) {System.out.print("Array is already empty: \n"); return;}
        this.size--;
    }

}

public class UniversalArrayHW {
    public static void main(String[] args) {
        UniversalArray univArray1 = new UniversalArray(8);
        univArray1.initArray();
        univArray1.printArray();

        System.out.println(univArray1.max());
        System.out.println(univArray1.min());
        System.out.println(univArray1.isPalindrome());
        univArray1.initArray();
        univArray1.printArray();
        univArray1.bubbleSort();
        System.out.println("Same array sorted (Bubble Sort): \n");
        univArray1.printArray();
        univArray1.initArray();
        univArray1.printArray();
        univArray1.selectionSort();
        System.out.println("Same array sorted (Selection Sort): \n");
        univArray1.printArray();
        univArray1.initArray();
        univArray1.printArray();
        univArray1.mergeSort();
        System.out.println("Same array sorted (Merge Sort): \n");
        univArray1.printArray();

        System.out.print("The new array: \n");

        univArray1.initArray();
        univArray1.printArray();

        System.out.print("Does the array contain number 1?: \n");
        System.out.print(univArray1.checkElement(1));

        System.out.print("Most frequent element: \n");
        System.out.print(univArray1.mod());

        System.out.print("Adding number 13 to the array: \n");
        univArray1.addElement(13);
        univArray1.printArray();

        System.out.print("Deleting final number from the array: \n");
        univArray1.deleteElement();
        univArray1.printArray();

        ArrayList<UniversalArray> list = new ArrayList<UniversalArray>();

        list.add(univArray1);

        UniversalArray univArray2 = new UniversalArray(5);
        univArray2.initArray();
        list.add(univArray2);

        UniversalArray univArray3 = new UniversalArray(6);
        univArray3.initArray();
        list.add(univArray3);

        int maximum = Integer.MIN_VALUE;
        for (int i = 0; i < list.size(); i++){
            int val = list.get(i).max();
            if (val > maximum)
                maximum = val;
        }
        System.out.print("Biggest element out of all: \n");
        System.out.print(maximum);


    }
}