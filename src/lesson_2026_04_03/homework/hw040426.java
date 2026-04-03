package lesson_2026_04_03.homework;

class Cylinder{
    static final double PI = Math.PI;
    static final double EPS = 1e-9;
    static int count = 0;

    double height;
    double radius;

    {count++;}

    Cylinder(){
        this(10, 5);
    }

    Cylinder(double height, double radius){
        this.height = height;
        this.radius = radius;
    }

    static boolean isEqual(double a, double b)
    {
        return Math.abs(a-b) <= EPS;
    }

    double volume(){
        return volume(this);
    }

    static double volume(Cylinder c){
        return PI * c.radius * c.radius * c.height;
    }

    double lateralSurfaceArea(){
        return lateralSurfaceArea(this);
    }

    static double lateralSurfaceArea(Cylinder c){
        return 2 * PI * c.radius * c.height;
    }

    double totalSurfaceArea(){
        return totalSurfaceArea(this);
    }

    static double totalSurfaceArea(Cylinder c)
    {
        return lateralSurfaceArea(c) + 2 * PI * c.radius * c.radius;
    }

    static boolean equal(Cylinder a, Cylinder b){
        return isEqual(a.radius, b.radius) && isEqual(a.height, b.height);
    }

    static Cylinder secondSmallestCylinder(Cylinder[] cylinders){
        if (cylinders.length <= 1){
            return null;
        }
        Cylinder smallest;
        Cylinder secondSmallest;
        if (cylinders[0].volume() >= cylinders[1].volume()) {
            smallest = cylinders[1];
            secondSmallest = cylinders[0];
        }
        else
        {
            smallest = cylinders[0];
            secondSmallest = cylinders[1];
        }
        double smallestVolume = smallest.volume();
        double secondSmallestVolume = secondSmallest.volume();
        for (int i = 2; i < cylinders.length; i++)
        {
            if (cylinders[i].volume() <= smallestVolume)
            {
                secondSmallest = smallest;
                smallest = cylinders[i];
                smallestVolume = smallest.volume();
                secondSmallestVolume = secondSmallest.volume();
                continue;
            }
            if (cylinders[i].volume() <= secondSmallestVolume){
                secondSmallest = cylinders[i];
                secondSmallestVolume = secondSmallest.volume();
            }
        }
        return secondSmallest;
    }

    static int howManyCylinders(){
        return count;
    }
}

class BigInt{
    static final int range = 100;

    int[] n = new int[range];
    int digits = 0;

    BigInt(){
        this(0);
    }

    BigInt(int n){
        if (n == 0) {digits = 1; this.n[0] = 0; return;}
        while (n != 0){
            this.n[digits] = n % 10;
            n /= 10;
            digits++;
        }
    }

    BigInt(String n){
        if (n.equals("0")) {digits = 1; this.n[0] = 0; return;}
        for (int i = 0; i < n.length(); i++)
            this.n[n.length() - i - 1] = n.charAt(i) - '0';
        this.digits = n.length();
    }

    static void print(BigInt a){
        for (int i = a.digits - 1; i >= 0; i--)
        {
            System.out.print(a.n[i]);
        }
        System.out.print("\n");
    }

    void print(){
        print(this);
    }

    static BigInt sum(BigInt a, BigInt b){
        if (a.digits <= b.digits){
            BigInt c = a;
            a = b;
            b = c;
        }

        BigInt c = new BigInt();
        int index = 0;
        int reminder = 0;
        while (true){
            int digit1, digit2;
            digit1 = a.n[index];
            digit2 = b.n[index];
            c.n[index] = (digit1 + digit2 + reminder) % 10;
            reminder = (digit1 + digit2 + reminder) / 10;
            index++;
            if (reminder == 0 && a.digits <= index)
                break;
        }
        c.digits = index;
        return c;
    }

    BigInt sum(BigInt a){
        return sum(this, a);
    }

    static BigInt product(BigInt a, BigInt b){
        if (a.digits <= b.digits){
            BigInt c = a;
            a = b;
            b = c;
        }

        BigInt c = new BigInt();

        if ((a.digits == 1 && a.n[0] == 0) || (b.digits == 1 && b.n[0] == 0)){
            return new BigInt(0);
        }

        if (b.digits == 1){
            int index = 0;
            int reminder = 0;
            while (true){
                int digit1, digit2;
                digit1 = a.n[index];
                digit2 = b.n[0];
                c.n[index] = (digit1 * digit2 + reminder) % 10;
                reminder = (digit1 * digit2 + reminder) / 10;
                index++;
                if (reminder == 0 && a.digits <= index)
                    break;
            }
            c.digits = index;
            return c;
        }

        if (b.digits == 2 && b.n[0] == 0 && b.n[1] == 1){
            for (int i = 0; i < a.digits; i++){
                c.n[i+1] = a.n[i];
            }
            c.digits = a.digits + 1;
            return c;
        }

        for (int i = 0; i < b.digits; i++)
        {
            c = c.sum(product(a, new BigInt(b.n[i])));
            a = product(a, new BigInt(10));
        }

        return c;
    }
}

class Polynomial{
    static final int range = 100;
    static final double EPS = 1e-9;
    double[] coefficients = new double[range];
    int degree;

    Polynomial(){
        this(new double[1]);
    }

    Polynomial(double[] coefficients){
        int mx = 0;
        for (int i = 0; i < coefficients.length; i++){
            this.coefficients[i] = coefficients[i];
            if (!isEqual(coefficients[i], 0)){
                mx = i;
            }
        }
        degree = mx;
    }

    static int findDegree(Polynomial a){
        int mx = 0;
        for (int i = 0; i < a.coefficients.length; i++){
            if (!isEqual(a.coefficients[i], 0)){
                mx = i;
            }
        }
        return mx;
    }

    static boolean isEqual(double a, double b)
    {
        return Math.abs(a-b) <= EPS;
    }

    static Polynomial sum(Polynomial a, Polynomial b){
        Polynomial c = new Polynomial();
        for (int i = 0; i < range; i++){
            c.coefficients[i] = a.coefficients[i] + b.coefficients[i];
        }
        c.degree = findDegree(c);
        return c;
    }

    Polynomial sum(Polynomial a){
        return sum(this, a);
    }

    static Polynomial subtract(Polynomial a, Polynomial b){
        Polynomial c = new Polynomial();
        for (int i = 0; i < range; i++){
            c.coefficients[i] = a.coefficients[i] - b.coefficients[i];
        }
        c.degree = findDegree(c);
        return c;
    }

    Polynomial subtract(Polynomial a){
        return subtract(this, a);
    }

    static Polynomial product(Polynomial a, Polynomial b){
        Polynomial c = new Polynomial();
        for (int i = 0; i < range; i++){
            for (int j = 0; j < range; j++){
                double val = a.coefficients[i] * b.coefficients[j];
                if (!isEqual(val, 0) && i + j < range){
                    c.coefficients[i+j] += val;
                }
            }
        }
        c.degree = findDegree(c);
        return c;
    }

}

public class hw040426 {
    public static void main(String[] args) {

    }
}
