package lesson_2026_04_03.homework;

class MergeSort {
    public double[] array;
    final static double eps = 1e-9;

    public double[] Sort(){
        if (array == null || array.length == 0) return new double[0];
        int l = 0;
        int r = array.length - 1;
        double[] sortedArray = new double[array.length];
        double[] resultOfSort;
        resultOfSort = Sort(l, r);
        for (int i = 0; i < array.length; i++)
        {
            sortedArray[i] = resultOfSort[i];
        }
        return sortedArray;
    }

    public double[] Sort(double[] arrayA){
        array = arrayA;
        return Sort();
    }

    public double[] Merge(double[] array1, double[] array2){
        int k = array1.length + array2.length - 1;
        int index1 = 0, index2 = 0;
        double[] result = new double[k];
        for (int i = 0; i < k; i++)
        {
            if (array1[index1] <= array2[index2])
            {
                result[i] = array1[index1];
                index1++;
            }
            else
            {
                result[i] = array2[index2];
                index2++;
            }
        }
        return result;
    }

    public double[] Sort(int l, int r){
        if (l == r) return new double[]{array[l], Double.MAX_VALUE};
        int l1 = l;
        int r1 = (l+r)/2;
        int l2 = (l+r)/2 + 1;
        int r2 = r;
        return Merge(Sort(l1, r1), Sort(l2, r2));
    }
}

class Point{
    double x;
    double y;
    final static double eps = 1e-9;

    Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    Point(Point p){
        this.x = p.x;
        this.y = p.y;
    }
    Point(){
        this.x = 0;
        this.y = 0;
    }

    boolean isEqual(double a, double b)
    {
        return Math.abs(a-b) <= eps;
    }

    void printPoint(){
        System.out.println("Point is located at: (" + x + " ; " + y + " )");
    }

    void printPoint(Point p){
        System.out.println("Point is located at: (" + p.x + " ; " + p.y + " )");
    }

    boolean printEqual(Point p){
        return isEqual(this.x, p.x) && isEqual(this.y, p.y);
    }

    void quarterPrint(){
        if (x > 0 && y > 0)
            System.out.println("First quarter");
        if (x < 0 && y > 0)
            System.out.println("Second quarter");
        if (x < 0 && y < 0)
            System.out.println("Third quarter");
        if (x > 0 && y < 0)
            System.out.println("Forth quarter");
        if (isEqual(x, 0) && isEqual(y, 0))
            System.out.println("Origin");
        if (isEqual(x, 0) && !isEqual(y, 0))
            System.out.println("Y - axis");
        if (!isEqual(x, 0) && isEqual(y, 0))
            System.out.println("X - axis");
    }

    double distancePoint(Point p){
        return Math.pow((this.x - p.x) * (this.x - p.x) + (this.y - p.y) * (this.y - p.y), 0.5);
    }

    Point bigPoint(){
       return new Point(x * 4, y * 4);
    }

    void minDistance(Point[] points)
    {
        if (points.length == 0)
        {
            System.out.println("No points found");
            return;
        }

        Point Origin = new Point(0, 0);
        int desiredPointIndex = 0;
        double minimumDistance = points[desiredPointIndex].distancePoint(Origin);

        for (int i = 0; i < points.length; i++){
            double distance = points[i].distancePoint(Origin);
            if (distance < minimumDistance)
            {
                minimumDistance = distance;
                desiredPointIndex = i;
            }
        }
        printPoint(points[desiredPointIndex]);
    }

    boolean sameLine(Point a, Point b, Point c) {
        return isEqual(a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y), 0);
    }

    boolean isTriangle(Point a, Point b, Point c){
        return !sameLine(a, b, c);
    }

    double areaTriangle(Point a, Point b, Point c) {
        return Math.abs((a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y))) / 2;
    }

    Point midPoint(Point p)
    {
        return new Point((p.x + this.x) / 2, (p.y + this.y) / 2);
    }

    boolean isInsideCircle(Point center, double radius){
        return distancePoint(center) <= radius;
    }

    int maximumPointsFittingLine(Point[] points){

        if (points.length == 0)
            return 0;

        if (points.length == 1)
            return 1;

        MergeSort sort1 = new MergeSort();
        MergeSort sort2 = new MergeSort();

        int maxCount = 1;

        int initIndex = 0;
        int index = 0;
        double[] lines = new double[points.length * points.length + 1];
        sort1.array = lines;

        int initIndexVertical = 0;
        int indexVertical = 0;
        double[] verticalLines = new double[points.length * points.length + 1];
        sort2.array = verticalLines;

        for (int i = 0; i < points.length; i++){
            for (int j = 0; j < points.length; j++)
            {
                if (i == j) continue;
                if (!isEqual(points[i].x, points[j].x)){
                    double val = (points[j].y - points[i].y) / (points[j].x - points[i].x);
                    lines[index] = val;
                    index++;
                }
                else {
                    double val = points[i].x;
                    verticalLines[indexVertical] = val;
                    indexVertical++;
                }
            }

            int count = 1;
            double val = 0;


            if (index > initIndex) {
                double[] arr1 = sort1.Sort(initIndex, index - 1);
                val = arr1[0];
                for (int j = 1; j < arr1.length - 1; j++) {
                    if (!isEqual(arr1[j], val)) {
                        if (maxCount < count)
                            maxCount = count;
                        count = 1;
                        val = arr1[j];
                    } else {
                        count++;
                    }
                }
                if (maxCount < count)
                    maxCount = count;
                count = 1;
                initIndex = index;
            }

            if (indexVertical > initIndexVertical) {
                double[] arr2 = sort2.Sort(initIndexVertical, indexVertical - 1);
                val = arr2[0];
                for (int j = 1; j < arr2.length - 1; j++) {
                    if (!isEqual(arr2[j], val)) {
                        if (maxCount < count)
                            maxCount = count;
                        count = 1;
                        val = arr2[j];
                    } else {
                        count++;
                    }
                }
                if (maxCount < count)
                    maxCount = count;
                count = 1;
                initIndexVertical = indexVertical;
            }
        }

        maxCount++;
        return maxCount;
    }

}

class Vector{
    Point start;
    Point end;
    final static double eps = 1e-9;

    boolean isEqual(double a, double b)
    {
        return Math.abs(a-b) <= eps;
    }

    Vector(Point start, Point end){
        this.start = new Point(start);
        this.end = new Point(end);
    }

    void printVector(){
        System.out.println("Vector: " + "{ " + (end.x - start.x) + " ; " + (end.y - start.y) + " }");
        System.out.print("\t Start: ");
        start.printPoint();
        System.out.print("\t End: ");
        end.printPoint();
    }

    double vectorLength(){
        return start.distancePoint(end);
    }
    double vectorLength(Vector a){
        return a.start.distancePoint(a.end);
    }

    Point vectorData(Vector a){
        return new Point((a.end.x - a.start.x), (a.end.y - a.start.y));
    }

    Point vectorData(){
        return new Point((end.x - start.x), (end.y - start.y));
    }


    boolean areParallel(Vector a, Vector b){
        Point first = a.vectorData();
        Point second = b.vectorData();
        return isEqual(first.x * second.y - first.y * second.x, 0);
    }

    double scalarProduct(Vector a, Vector b)
    {
        Point first = a.vectorData();
        Point second = b.vectorData();
        return first.x * second.x + first.y * second.y;
    }

    double angle(Vector a, Vector b){
        if (isEqual(vectorLength(a) * vectorLength(b), 0)) return 0;
        return Math.acos(scalarProduct(a, b) / (vectorLength(a) * vectorLength(b)));
    }

    Vector sum(Vector a, Vector b){
        Point first = a.vectorData();
        Point second = b.vectorData();
        Point sumVector = new Point(first.x + second.x, first.y + second.y);
        Point sumEnd = new Point(a.start.x + sumVector.x, a.start.y + sumVector.y);
        return new Vector(a.start, sumEnd);
    }

    void longestVector(Vector[] vectors){
        if (vectors.length == 0)
        {
            System.out.println("Invalid array: No vector provided.");
            return;
        }
        Vector longest = vectors[0];
        double longestLength = longest.vectorLength();
        for (int i = 1; i < vectors.length; i++)
        {
            double tempLength = vectors[i].vectorLength();
            if (tempLength > longestLength)
            {
                longestLength = tempLength;
                longest = vectors[i];
            }
        }
        longest.printVector();
    }

}

public class PointVectorForHolidays {
    public static void main(String[] args) {
        Point point1 = new Point(2, 3);
        Point point2 = new Point(-1, 4);
        Point point3 = new Point(point1);
        Point point4 = new Point();
        Point point5 = new Point(0, -2);

        point1.printPoint();
        point2.printPoint(point2);

        System.out.println(point1.printEqual(point3));

        point1.quarterPrint();
        System.out.println(point1.distancePoint(point2));

        point3 = point1.bigPoint();
        point3.printPoint();

        Point[] points = new Point[5];
        points[0] = point1;
        points[1] = point2;
        points[2] = point3;
        points[3] = point4;
        points[4] = point5;

        point1.minDistance(points);

        System.out.println(point1.sameLine(point1, point2, point3));
        System.out.println(point1.isTriangle(point1, point2, point5));
        System.out.println(point1.areaTriangle(point1, point2, point5));

        Point mid = point1.midPoint(point2);
        mid.printPoint();

        System.out.println(point1.isInsideCircle(new Point(0, 0), 5));

        System.out.println(point1.maximumPointsFittingLine(points));

        Vector vector1 = new Vector(new Point(0, 0), new Point(3, 4));
        Vector vector2 = new Vector(new Point(1, 1), new Point(4, 5));
        Vector vector3 = new Vector(new Point(0, 0), new Point(-4, 3));
        Vector vector4 = new Vector(new Point(2, 2), new Point(5, 6));

        vector1.printVector();
        vector2.printVector();

        System.out.println(vector1.vectorLength());
        System.out.println(vector1.areParallel(vector1, vector2));
        System.out.println(vector1.scalarProduct(vector1, vector3));
        System.out.println(vector1.angle(vector1, vector3));

        Vector sumVector = vector1.sum(vector1, vector3);
        sumVector.printVector();

        Vector[] vectors = new Vector[4];
        vectors[0] = vector1;
        vectors[1] = vector2;
        vectors[2] = vector3;
        vectors[3] = vector4;

        vector1.longestVector(vectors);
    }
}
