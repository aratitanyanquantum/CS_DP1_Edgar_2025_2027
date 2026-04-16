package lesson_2026_04_03.homework;

class Employee{
    private String name;
    private String surname;
    private int age;
    private double weight;
    private double salary;
    private String country;
    private int yearsOfExperience;
    private boolean gender; // false for women, true for men
    private String mail;

    public Employee(String name, String surname, int age, double weight, double salary, String country, int yearsOfExperience, boolean gender, String mail)
    {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.weight = weight;
        this.salary = salary;
        this.country = country;
        this.yearsOfExperience = yearsOfExperience;
        this.gender = gender;
        this.mail = mail;
    }

    public Employee()
    {
        this.name = "Petros";
        this.surname = "Petrosyan";
        this.age = 25;
        this.weight = 85.7;
        this.salary = 2000000;
        this.country = "Armenia";
        this.yearsOfExperience = 20;
        this.gender = true;
        this.mail = "petrospetros@ysu.am";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        if (age < 18 || age > 70)
        {
            System.out.print("Employees age should be in range [18 ; 70]. \n");
            return;
        }
        this.age = age;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public int getAge() {
        return this.age;
    }

    public double getWeight() {
        return this.weight;
    }

    public double getSalary() {
        return this.salary;
    }

    public String getCountry() {
        return country;
    }

    public int getYearsOfExperience() {
        return this.yearsOfExperience;
    }

    public boolean getGender() {
        return this.gender;
    }

    public String getMail() {
        return this.mail;
    }

    public void fullInfo(){
        System.out.print("Name: ");
        System.out.print(this.name);
        System.out.print("\n");
        System.out.print("Surname: ");
        System.out.print(this.surname);
        System.out.print("\n");
        System.out.print("Age: ");
        System.out.print(this.age);
        System.out.print("\n");
        System.out.print("Weight: ");
        System.out.print(this.weight);
        System.out.print("\n");
        System.out.print("Salary: ");
        System.out.print(this.salary);
        System.out.print("\n");
        System.out.print("Country: ");
        System.out.print(this.country);
        System.out.print("\n");
        System.out.print("Years of experience: ");
        System.out.print(this.yearsOfExperience);
        System.out.print("\n");
        System.out.print("Gender: ");
        if (gender) {
            System.out.print("male");
        } else {
            System.out.print("female");
        }
        System.out.print("\n");
        System.out.print("Mail address: ");
        System.out.print(this.mail);
        System.out.print("\n");
    }

    public void shortInfo(){
        System.out.print("Name: ");
        System.out.print(this.name);
        System.out.print(" / ");
        System.out.print("Surname: ");
        System.out.print(this.surname);
        System.out.print(" / ");
        System.out.print("Country: ");
        System.out.print(this.country);
        System.out.print("\n");
    }

    public boolean isMailValid(){
        int aQuantity = 0;
        int dotQuantity = 0;
        int aPosition = -1;
        int dotPosition = -1;
        int forbiddenDotCount = this.mail.length()+1;
        for (int i = 0; i < this.mail.length(); i++)
        {
            if (mail.charAt(i) == '@') {
                aQuantity++;
                aPosition = i;
                forbiddenDotCount = dotQuantity + 2;
            }
            if (mail.charAt(i) == '.') {
                if (dotQuantity + 1 >= forbiddenDotCount) return false;
                dotQuantity++;
                dotPosition = i;
            }
        }
        if (aQuantity == 1 && dotQuantity >= 1){
            if (dotPosition > aPosition + 1 && aPosition != 0 && dotPosition != this.mail.length() - 1)
                return true;
            return false;
        }
        return false;
    }
}

class Sportsman extends Employee{
    private int numberOfTrophies;
    private String team;


    public Sportsman(int numberOfTrophies, String team){
        super();
        this.numberOfTrophies = numberOfTrophies;
        this.team = team;
    }

    public Sportsman(int numberOfTrophies, String team, String name, String surname, int age, double weight, double salary, String country, int yearsOfExperience, boolean gender, String mail){
        super(name, surname, age, weight, salary, country, yearsOfExperience, gender, mail);
        this.numberOfTrophies = numberOfTrophies;
        this.team = team;
    }

    public void setNumberOfTrophies(int numberOfTrophies) {
        this.numberOfTrophies = numberOfTrophies;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getNumberOfTrophies() {
        return numberOfTrophies;
    }

    public String getTeam() {
        return team;
    }

    public void info(){
        System.out.print("Employee info: \n");
        fullInfo();
        System.out.print("Sportsman specific info: \n");
        System.out.print("Number of trophies: ");
        System.out.print(this.numberOfTrophies);
        System.out.print("\n");
        System.out.print("Team name: ");
        System.out.print(this.team);
        System.out.print("\n");
    }

}

public class Hw160426 {
    public static void main(String[] args) {
        Sportsman s1 = new Sportsman(5, "Ararat", "Ani", "Mkrtchyan", 22, 58.5, 300000, "Armenia", 4, false, "ani@gmail.com");
        Sportsman s2 = new Sportsman(8, "Noah", "Karen", "Sargsyan", 27, 74.0, 500000, "Armenia", 7, true, "karen@gmail.com");
        Sportsman s3 = new Sportsman(3, "Urartu", "David", "Hakobyan", 20, 68.0, 250000, "Armenia", 2, true, "david@gmail.com");

        s1.info();
        s2.info();
        s3.info();
    }
}
