package HW070526;

import java.util.LinkedList;

class Client {
    private int customerID;
    private String name;
    private Dates arrive;
    private Dates leave;
    private Room bedroom;

    public Client(int id, String c, Dates dateIn, Dates dateOut, Room r) {
        setCustomerID(id);
        setName(c);
        setArrive(dateIn);
        setLeave(dateOut);
        setBedroom(r);
    }

    public void setCustomerID(int id) { customerID = id; }
    public void setName(String c) { name = c; }
    public void setArrive(Dates dateIn) { arrive = dateIn; }
    public void setLeave(Dates dateOut) { leave = dateOut; }
    public void setBedroom(Room r) { bedroom = r; }

    public int getCustomerID() { return customerID; }
    public String getName() { return name; }
    public Dates getArrive() { return arrive; }
    public Dates getLeave() { return leave; }
    public Room getBedroom() { return bedroom; }

    public void bill() {
        int nights = Dates.StayDays(arrive, leave);
        double totalCost = nights * bedroom.getPrice();

        System.out.println("----- Hotel Bill -----");
        System.out.println("Name: " + name);
        System.out.println("Room number: " + bedroom.getRoomNumber());
        System.out.println("Date arrived: " + arrive);
        System.out.println("Date leaving: " + leave);
        System.out.println("Total nights stayed: " + nights);
        System.out.println("Total cost: " + totalCost);
    }
}

class Dates {
    private int day;
    private int month;
    private int year;

    public Dates(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() { return day; }
    public int getMonth() { return month; }
    public int getYear() { return year; }

    public static int StayDays(Dates x, Dates y) {
        return daysFromStart(y) - daysFromStart(x);
    }

    public static Dates compareDate(Dates x, Dates y) {
        if (daysFromStart(x) <= daysFromStart(y)) {
            return x;
        }
        return y;
    }

    public static boolean equalDate(Dates x, Dates y) {
        return x.day == y.day && x.month == y.month && x.year == y.year;
    }

    public static boolean isEarlier(Dates x, Dates y) {
        return daysFromStart(x) < daysFromStart(y);
    }

    private static int daysFromStart(Dates d) {
        int days = 0;

        for (int year = 1; year < d.getYear(); year++) {
            days += isLeapYear(year) ? 366 : 365;
        }

        for (int month = 1; month < d.getMonth(); month++) {
            days += daysInMonth(month, d.getYear());
        }

        days += d.getDay();
        return days;
    }

    private static boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }

    private static int daysInMonth(int month, int year) {
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29 : 28;
            default:
                return 0;
        }
    }

    public String toString() {
        return day + "." + month + "." + year;
    }
}

class Room {
    private int roomNumber;
    private int beds;
    private double price;
    private boolean empty;

    public Room(int roomNumber, int beds, double price, boolean empty) {
        this.roomNumber = roomNumber;
        this.beds = beds;
        this.price = price;
        this.empty = empty;
    }

    public int getRoomNumber() { return roomNumber; }
    public int getBeds() { return beds; }
    public double getPrice() { return price; }
    public boolean getEmpty() { return empty; }

    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }
    public void setBeds(int beds) { this.beds = beds; }
    public void setPrice(double price) { this.price = price; }
    public void setEmpty(boolean empty) { this.empty = empty; }
}

class Group {
    private String name;
    private int number;
    private int[] gRooms;
    private Room[] allRooms;

    public Group(String name, int number, Room[] allRooms) {
        this.name = name;
        this.number = number;
        this.allRooms = allRooms;
        this.gRooms = new int[number];
    }

    public String getName() { return name; }
    public int getNumber() { return number; }
    public int[] getGRooms() { return gRooms; }

    public double bill(int[] gRooms) {
        double total = 0;

        for (int i = 0; i < gRooms.length; i++) {
            int roomNumber = gRooms[i];
            total += allRooms[roomNumber - 1].getPrice();
        }

        System.out.println("Group name: " + name);
        System.out.println("Number of rooms: " + number);
        System.out.println("Total cost for one day: " + total);

        return total;
    }
}

class GClient extends Client {
    private String groupName;

    public GClient(int id, String c, Dates dateIn, Dates dateOut, Room r, String groupName) {
        super(id, c, dateIn, dateOut, r);
        this.groupName = groupName;
    }

    public String getGroupName() { return groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }
}

public class homework {
    private LinkedList<Client> Bookings;
    private Room[] allRooms;

    public homework() {
        Bookings = new LinkedList<Client>();
        allRooms = new Room[100];
    }

    public int[] findRooms() {
        int count = 0;

        for (int i = 0; i < allRooms.length; i++) {
            if (allRooms[i] != null && allRooms[i].getEmpty() && allRooms[i].getBeds() == 2) {
                count++;
            }
        }

        int[] emptyRooms = new int[count];
        int index = 0;

        for (int i = 0; i < allRooms.length; i++) {
            if (allRooms[i] != null && allRooms[i].getEmpty() && allRooms[i].getBeds() == 2) {
                emptyRooms[index] = allRooms[i].getRoomNumber();
                index++;
            }
        }

        return emptyRooms;
    }

    public void newClient(Client c) {
        int position = 0;

        while (position < Bookings.size()) {
            Client current = Bookings.get(position);

            if (Dates.equalDate(c.getArrive(), current.getArrive())) {
                break;
            }

            if (Dates.compareDate(c.getArrive(), current.getArrive()) == c.getArrive()) {
                break;
            }

            position++;
        }

        Bookings.add(position, c);
    }

    public Client[] todayClients(Dates today) {
        Client[] temp = new Client[Bookings.size()];
        int count = 0;

        while (!Bookings.isEmpty() && Dates.equalDate(Bookings.getFirst().getArrive(), today)) {
            temp[count] = Bookings.removeFirst();
            count++;
        }

        Client[] arrivalsToday = new Client[count];

        for (int i = 0; i < count; i++) {
            arrivalsToday[i] = temp[i];
        }

        for (int i = 1; i < arrivalsToday.length; i++) {
            Client key = arrivalsToday[i];
            int j = i - 1;

            while (j >= 0 && Dates.isEarlier(key.getLeave(), arrivalsToday[j].getLeave())) {
                arrivalsToday[j + 1] = arrivalsToday[j];
                j--;
            }

            arrivalsToday[j + 1] = key;
        }

        return arrivalsToday;
    }

    public static void main(String[] args) {
        homework hotel = new homework();

        for (int i = 0; i < hotel.allRooms.length; i++) {
            hotel.allRooms[i] = new Room(i + 1, 2, 100.0, true);
        }

        Client c1 = new Client(121, "Aramazd", new Dates(5, 5, 2016), new Dates(8, 5, 2016), hotel.allRooms[0]);

        Client c2 = new Client(122, "Nazar", new Dates(5, 5, 2016), new Dates(7, 5, 2016), hotel.allRooms[1]);

        hotel.newClient(c1);
        hotel.newClient(c2);

        Client[] arrivals = hotel.todayClients(new Dates(5, 5, 2016));

        for (int i = 0; i < arrivals.length; i++) {
            arrivals[i].bill();
        }
    }
}