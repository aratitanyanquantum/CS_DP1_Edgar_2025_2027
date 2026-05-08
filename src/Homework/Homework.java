package Homework;

public class Homework {
    public static void main(String[] args) {
        ParkingArea area = new ParkingArea("Central Parking", 300);

        Vehicle car = new Car("X1234567");
        car.setColour(Vehicle.BLACK);

        area.addVehicle(car);
        area.addVehicle(new Motorbike("M1234567"));

        Vehicle removed = area.removeVehicle("X1234567");

        Stack stack = new Stack();
        stack.push(new Car("X00000011"));
        stack.push(new Car("X00000010"));
        stack.push(new Car("X00000051"));
        stack.push(new Car("X00000022"));
        stack.push(new Car("X00000050"));
        stack.push(new Car("X00000123"));

        StaffParking.staffRemoveCar(stack, "X00000011");

        ParkingMap map = new ParkingMap(200);
        map.addVehicle(new Car("C0000001"));
        map.addVehicle(new Motorbike("M0000001"));
        map.addVehicle(new Motorbike("M0000002"));

        RegistrationBinarySearchTree tree = map.createBinarySearchTree();
        Vehicle found = tree.search("M0000001");
    }
}

class ParkingArea {
    private Vehicle[] vehicles;
    private String name;
    private int carCount;
    private int motorbikeCount;

    public ParkingArea(String name, int capacity) {
        this.name = name;

        if (capacity > 300) {
            capacity = 300;
        }

        if (capacity < 0) {
            capacity = 0;
        }

        this.vehicles = new Vehicle[capacity];
        this.carCount = 0;
        this.motorbikeCount = 0;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return vehicles.length;
    }

    public int findVehicle(String reg) {
        if (reg == null) {
            return -1;
        }

        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i] != null && reg.equals(vehicles[i].getRegistration())) {
                return i;
            }
        }

        return -1;
    }

    public int addVehicle(Vehicle v) {
        if (v == null) {
            return -1;
        }

        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i] == null) {
                vehicles[i] = v;

                if (v.getKind() == 'c') {
                    carCount++;

                    if (carCount % 50 == 0) {
                        Vouchers.printCoffeeVoucher();
                    }
                } else if (v.getKind() == 'm') {
                    motorbikeCount++;

                    if (motorbikeCount % 60 == 0) {
                        Vouchers.printCoffeeVoucher();
                    }
                }

                return i;
            }
        }

        return -1;
    }

    public Vehicle removeVehicle(String reg) {
        if (reg == null) {
            return null;
        }

        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i] != null && reg.equals(vehicles[i].getRegistration())) {
                Vehicle removedVehicle = vehicles[i];
                vehicles[i] = null;
                return removedVehicle;
            }
        }

        return null;
    }
}

class Vehicle {
    private String registration;
    private byte colour;
    private boolean broken;

    public static final byte BLACK = 1;
    public static final byte WHITE = 2;
    public static final byte BLUE = 3;
    public static final byte RED = 4;
    public static final byte GREEN = 5;

    private static final double ADMIN_FEE = 3.0;

    public Vehicle() {
        this.registration = "";
        this.colour = 0;
        this.broken = false;
    }

    public Vehicle(String registration) {
        this.registration = registration;
        this.colour = 0;
        this.broken = false;
    }

    public Vehicle(String registration, byte colour) {
        this.registration = registration;
        this.colour = colour;
        this.broken = false;
    }

    public void setBroken(boolean broken) {
        this.broken = broken;
    }

    public void setColour(byte colour) {
        this.colour = colour;
    }

    public boolean getBroken() {
        return broken;
    }

    public String getRegistration() {
        return registration;
    }

    public byte getColour() {
        return colour;
    }

    public char getKind() {
        return 'v';
    }

    public double pay(int hours) {
        if (hours <= 5) {
            return ADMIN_FEE;
        }

        return 0.0;
    }
}

class Car extends Vehicle {
    public static double hourlyFee = 3.5;

    public Car() {
        super();
    }

    public Car(String registration) {
        super(registration);
    }

    public Car(String registration, byte colour) {
        super(registration, colour);
    }

    @Override
    public char getKind() {
        return 'c';
    }

    @Override
    public double pay(int hours) {
        return super.pay(hours) + (hours * hourlyFee);
    }
}

class Motorbike extends Vehicle {
    public static double hourlyFee = 2.5;

    public Motorbike() {
        super();
    }

    public Motorbike(String registration) {
        super(registration);
    }

    public Motorbike(String registration, byte colour) {
        super(registration, colour);
    }

    @Override
    public char getKind() {
        return 'm';
    }

    @Override
    public double pay(int hours) {
        return super.pay(hours) + (hours * hourlyFee);
    }
}

class Vouchers {
    public static void printCoffeeVoucher() {
        System.out.println("Free coffee voucher");
    }
}

class ParkingMap {
    private Vehicle[][] parkingSpaces;

    public ParkingMap(int spaces) {
        if (spaces < 0) {
            spaces = 0;
        }

        parkingSpaces = new Vehicle[2][spaces];
    }

    public int addVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            return -1;
        }

        if (vehicle.getKind() == 'c') {
            for (int col = 0; col < parkingSpaces[0].length; col++) {
                if (parkingSpaces[0][col] == null && parkingSpaces[1][col] == null) {
                    parkingSpaces[0][col] = vehicle;
                    return col;
                }
            }
        } else if (vehicle.getKind() == 'm') {
            for (int col = 0; col < parkingSpaces[0].length; col++) {
                if (parkingSpaces[0][col] == null) {
                    parkingSpaces[0][col] = vehicle;
                    return col;
                }

                if (parkingSpaces[0][col].getKind() == 'm' && parkingSpaces[1][col] == null) {
                    parkingSpaces[1][col] = vehicle;
                    return col;
                }
            }
        }

        return -1;
    }

    public Vehicle findVehicle(String reg) {
        if (reg == null) {
            return null;
        }

        for (int row = 0; row < parkingSpaces.length; row++) {
            for (int col = 0; col < parkingSpaces[row].length; col++) {
                if (parkingSpaces[row][col] != null && reg.equals(parkingSpaces[row][col].getRegistration())) {
                    return parkingSpaces[row][col];
                }
            }
        }

        return null;
    }

    public Vehicle removeVehicle(String reg) {
        if (reg == null) {
            return null;
        }

        for (int row = 0; row < parkingSpaces.length; row++) {
            for (int col = 0; col < parkingSpaces[row].length; col++) {
                if (parkingSpaces[row][col] != null && reg.equals(parkingSpaces[row][col].getRegistration())) {
                    Vehicle removedVehicle = parkingSpaces[row][col];
                    parkingSpaces[row][col] = null;
                    return removedVehicle;
                }
            }
        }

        return null;
    }

    public RegistrationBinarySearchTree createBinarySearchTree() {
        RegistrationBinarySearchTree tree = new RegistrationBinarySearchTree();

        for (int row = 0; row < parkingSpaces.length; row++) {
            for (int col = 0; col < parkingSpaces[row].length; col++) {
                if (parkingSpaces[row][col] != null) {
                    tree.insert(parkingSpaces[row][col]);
                }
            }
        }

        return tree;
    }
}

class RegistrationBinarySearchTree {
    private TreeNode root;

    public RegistrationBinarySearchTree() {
        root = null;
    }

    public void insert(Vehicle vehicle) {
        if (vehicle == null || vehicle.getRegistration() == null) {
            return;
        }

        root = insert(root, vehicle);
    }

    private TreeNode insert(TreeNode node, Vehicle vehicle) {
        if (node == null) {
            return new TreeNode(vehicle);
        }

        int comparison = vehicle.getRegistration().compareTo(node.vehicle.getRegistration());

        if (comparison < 0) {
            node.left = insert(node.left, vehicle);
        } else if (comparison > 0) {
            node.right = insert(node.right, vehicle);
        } else {
            node.vehicle = vehicle;
        }

        return node;
    }

    public Vehicle search(String registration) {
        if (registration == null) {
            return null;
        }

        TreeNode current = root;

        while (current != null) {
            int comparison = registration.compareTo(current.vehicle.getRegistration());

            if (comparison == 0) {
                return current.vehicle;
            } else if (comparison < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    private static class TreeNode {
        private Vehicle vehicle;
        private TreeNode left;
        private TreeNode right;

        private TreeNode(Vehicle vehicle) {
            this.vehicle = vehicle;
            this.left = null;
            this.right = null;
        }
    }
}

class Stack {
    private Node top;

    public Stack() {
        top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(Vehicle v) {
        Node node = new Node(v);
        node.next = top;
        top = node;
    }

    public Vehicle pop() {
        if (isEmpty()) {
            return null;
        }

        Vehicle vehicle = top.vehicle;
        top = top.next;
        return vehicle;
    }

    private static class Node {
        private Vehicle vehicle;
        private Node next;

        private Node(Vehicle vehicle) {
            this.vehicle = vehicle;
            this.next = null;
        }
    }
}

class StaffParking {
    public static void staffRemoveCar(Stack stack, String reg) {
        Stack temp = new Stack();

        Vehicle current = stack.pop();

        while (current != null && !reg.equals(current.getRegistration())) {
            temp.push(current);
            current = stack.pop();
        }

        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }
}

