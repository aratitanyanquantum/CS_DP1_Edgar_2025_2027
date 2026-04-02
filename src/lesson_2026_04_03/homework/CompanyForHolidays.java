package lesson_2026_04_03.homework;

class Employee {
    int id;
    String name;
    String lastName;
    int age;

    // gender: false - female, true - male
    boolean gender;

    String department;
    double baseSalary;
    int yearsOfExperience;
    boolean isFullTime;

    Employee(int id, String name, String lastName, int age, boolean gender,
             String department, double baseSalary, int yearsOfExperience, boolean isFullTime) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.department = department;
        this.isFullTime = isFullTime;

        setAge(age);
        setBaseSalary(baseSalary);
        setYearsOfExperience(yearsOfExperience);
    }

    Employee() {
        id = 0;
        name = "Unknown";
        lastName = "Unknown";
        age = 18;
        gender = true;
        department = "Unknown";
        baseSalary = 0;
        yearsOfExperience = 1;
        isFullTime = false;
    }

    void setAge(int age) {
        if (age >= 18 && age <= 75) {
            this.age = age;
        } else {
            System.out.println("Invalid age. Valid range is 18 to 75.");
        }
    }

    void setBaseSalary(double baseSalary) {
        if (baseSalary >= 0 && baseSalary <= 1000000) {
            this.baseSalary = baseSalary;
        } else {
            System.out.println("Invalid base salary. Valid range is 0 to 1000000.");
        }
    }

    void setYearsOfExperience(int yearsOfExperience) {
        if (yearsOfExperience >= 1) {
            this.yearsOfExperience = yearsOfExperience;
        } else {
            System.out.println("Invalid years of experience. Valid range is 1 or more.");
        }
    }

    void printInfo() {
        System.out.println("ID: " + id + ", Name: " + name + ", Last Name: " + lastName);
    }

    void printFullInfo() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Last Name: " + lastName);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + (gender ? "Male" : "Female"));
        System.out.println("Department: " + department);
        System.out.println("Base Salary: " + baseSalary);
        System.out.println("Years Of Experience: " + yearsOfExperience);
        System.out.println("Full Time: " + isFullTime);
        System.out.println("Calculated Salary: " + calculateSalary());
        System.out.println("--------------------------------");
    }

    double calculateSalary() {
        double rate = 0.05;
        double salary = baseSalary * Math.pow(1 + rate, yearsOfExperience);

        if (!isFullTime) {
            salary = salary * 0.5;
        }

        return salary;
    }

    double calculateSalary(double rate) {
        double salary = baseSalary * Math.pow(1 + rate, yearsOfExperience);

        if (!isFullTime) {
            salary = salary * 0.5;
        }

        return salary;
    }

    boolean isEligibleForPromotion() {
        return yearsOfExperience > 5;
    }
}

class Company {
    String companyName;
    int yearCreated;
    String industry;
    Employee[] employees;
    int count;

    Company(String companyName, int yearCreated, String industry) {
        this.companyName = companyName;
        this.yearCreated = yearCreated;
        this.industry = industry;
        count = 0;
        employees = new Employee[10];
    }

    void addEmployee(Employee e) {
        if (count < employees.length) {
            employees[count] = e;
            count++;
            System.out.println("Employee added successfully.");
        } else {
            System.out.println("Company is full. Cannot add more employees.");
        }
    }

    void listAllEmployees() {
        if (count == 0) {
            System.out.println("No employees in the company.");
            return;
        }

        for (int i = 0; i < count; i++) {
            employees[i].printInfo();
        }
    }

    Employee findHighestPaidEmployee() {
        if (count == 0) {
            return null;
        }

        Employee highestPaid = employees[0];
        double highestSalary = employees[0].calculateSalary();

        for (int i = 1; i < count; i++) {
            double currentSalary = employees[i].calculateSalary();

            if (currentSalary > highestSalary) {
                highestSalary = currentSalary;
                highestPaid = employees[i];
            }
        }

        return highestPaid;
    }

    void printPromotionEligibleEmployees() {
        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (employees[i].isEligibleForPromotion()) {
                employees[i].printInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No employees are eligible for promotion.");
        }
    }

    void deleteEmployee(int id) {
        int index = -1;

        for (int i = 0; i < count; i++) {
            if (employees[i].id == id) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Employee with ID " + id + " was not found.");
            return;
        }

        for (int i = index; i < count - 1; i++) {
            employees[i] = employees[i + 1];
        }

        employees[count - 1] = null;
        count--;

        System.out.println("Employee with ID " + id + " deleted successfully.");
    }
}

public class CompanyForHolidays {
    public static void main(String[] args) {
        Company company = new Company("Quantum College", 2020, "Agriculture");

        Employee e1 = new Employee(1, "SpongeBob", "SquarePants", 25, false, "IT", 250000, 3, true);
        Employee e2 = new Employee(2, "Shrek", "Ogre", 30, true, "HR", 220000, 7, true);
        Employee e3 = new Employee(3, "Patrick", "Star", 22, false, "Sales", 180000, 2, false);

        company.addEmployee(e1);
        company.addEmployee(e2);
        company.addEmployee(e3);

        System.out.println("All employees:");
        company.listAllEmployees();

        System.out.println("Full info of one employee:");
        e1.printFullInfo();

        System.out.println("Highest paid employee:");
        Employee highest = company.findHighestPaidEmployee();
        if (highest != null) {
            highest.printFullInfo();
        }

        System.out.println("Employees eligible for promotion:");
        company.printPromotionEligibleEmployees();

        System.out.println("Delete employee with ID 2:");
        company.deleteEmployee(2);

        System.out.println("All employees after deletion:");
        company.listAllEmployees();

        System.out.println("Salary with custom rate:");
        System.out.println(e1.calculateSalary(0.1));
    }
}