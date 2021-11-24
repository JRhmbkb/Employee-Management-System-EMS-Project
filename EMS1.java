import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EMS1 {
    public static void main(String[] args) {

        //display a welcome message
        System.out.println("Welcome to Employee Management System (EMS)");

        //Initialize an empty hash map in the memory - for database
        Map<Integer, Map<String, Object>> database = new HashMap<>();

        //Initialize an empty hash map in the memory - for employee
        Map<String, Object> employee = new HashMap<>();

        //Create a loop, display the menu, and interact with the user (each menu option will call the
        //appropriate method corresponding that menu option
        int selection;
        do {
            selection = menu();

            if (selection == 1) {
                addEmployee(database, employee);
            } else if (selection == 2) {
                findEmployee_EID(database, employee);
            } else if (selection == 3) {
                findEmployee_name(database, employee);
            } else if (selection == 4) {
                deleteEmployee(database);
            } else if (selection == 5) {
                displayStat();
            } else if (selection == 6) {
                displayEmployee(database);
            } else if (selection == 7) {
                System.out.println("Thank you for using Employee Management System (EMS)");
            }
        }while (selection != 7);

    }

    public static int menu (){

        int selection;
        Scanner keyboard = new Scanner(System.in);


            System.out.println("Main Menu:");
            System.out.println("1.\tAdd an Employee");
            System.out.println("2.\tFind an Employee (By Employee ID)");
            System.out.println("3.\tFind an Employee (By Name)");
            System.out.println("4.\tDelete an Employee");
            System.out.println("5.\tDisplay Statistics");
            System.out.println("6.\tDisplay All Employees");
            System.out.println("7.\tExit");
            System.out.println("Enter you selection (1..7):");

        selection = keyboard.nextInt();
        while (selection!=1 &&selection!=2 && selection!=3 && selection!=4&& selection!=5&& selection!=6&& selection!=7 ){
            System.out.println("Invalid selection.");
            System.out.println("Enter you selection (1..7):");
            selection = keyboard.nextInt();
        }

        return selection;

    }

    public static void addEmployee(Map<Integer, Map <String, Object>> database, Map<String, Object> employee ) {
        String EID;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter an Employee ID or QUIT to stop:");
        EID = keyboard.nextLine();

        //input validation
        if (EID.equalsIgnoreCase("quit")) {
            return;
        }
        while (Integer.parseInt(EID) <= 0) {
            System.out.println("Invalid ID. Employee ID should be a positive integer number.");
            System.out.println("Enter an Employee ID or QUIT to stop:");
            EID = keyboard.nextLine();

            if (EID.equalsIgnoreCase("quit")) {
                return;
            }
        }

        while (database.containsKey(Integer.parseInt(EID))) {
            System.out.println("Employee ID: "+ EID+ " already exists in the database.");
            System.out.println("Enter an Employee ID or QUIT to stop:");

            EID = keyboard.nextLine();
            if (EID.equalsIgnoreCase("quit")){
                return;
            }
        }

            //get name
            String name;
            System.out.println("Enter employee name:");
            name = keyboard.nextLine();

            //get department
            String department;
            System.out.println("Enter employee department:");
            department = keyboard.nextLine();

            //get title
            String title;
            System.out.println("Enter employee title:");
            title = keyboard.nextLine();

            //get salary
            double salary;
            System.out.println("Enter employee salary:");
            salary = keyboard.nextDouble();

            while (salary <= 0) {
                System.out.println("Invalid salary. Salary should be a positive number.");
                System.out.println("Enter employee salary:");
                salary = keyboard.nextDouble();
            }

            //associate the EID with database and employee stats
            employee.put("EID", Integer.parseInt(EID));
            employee.put("Name", name);
            employee.put("Department", department);
            employee.put("Title", title);
            employee.put("Salary", salary);

            database.put(Integer.valueOf(EID), employee);

    }

    public static void findEmployee_EID (Map<Integer, Map <String, Object>> database, Map<String, Object> employee ){

        int EID;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter an Employee ID or QUIT to stop:");
        EID = keyboard.nextInt();

        //input validation
        while (EID <= 0) {
            System.out.println("Invalid ID. Employee ID should be a positive integer number.");
            System.out.println("Enter an Employee ID or QUIT to stop:");
            EID = keyboard.nextInt();

            if (String.valueOf(EID).equalsIgnoreCase("quit")) {
                break;
            }
        }

        if (database.containsKey(EID)) {
            System.out.println("Employee ID: "+ EID+ " already exists in the database.");
        }
    }

    public static void findEmployee_name(Map<Integer, Map <String, Object>> database, Map<String, Object> employee){
        System.out.println("Not functional in phase 1.");
    }

    public static void deleteEmployee(Map<Integer, Map <String, Object>> database){
        int EID;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter an Employee ID or QUIT to stop:");
        EID = keyboard.nextInt();

        //input validation
        while (EID <= 0) {
            System.out.println("Invalid ID. Employee ID should be a positive integer number.");
            System.out.println("Enter an Employee ID or QUIT to stop:");
            EID = keyboard.nextInt();

            if (String.valueOf(EID).equalsIgnoreCase("quit")) {
                break;
            }
        }

        if (database.containsKey(EID)) {
            database.remove(EID);
        }
        else{
            System.out.println("None existing key!");
        }
    }

    public static void displayStat(){
        System.out.println("Not functional in phase 1");
    }

    public static void displayEmployee(Map<Integer, Map <String, Object>> database){

        int counter = 0;
        for (int eid : database.keySet()){
            System.out.println("Employee ID: " + eid);
            System.out.println("\tName: " + database.get(eid).get("Name"));
            System.out.println("\tDepartment: " + database.get(eid).get("Department"));
            System.out.println("\tTitle: " + database.get(eid).get("Title"));
            System.out.println(String.format("\tSalary: %,.2f",database.get(eid).get("Salary")));
            ++counter;
        }
        System.out.println("There is "+counter + " employee in the database.");
    }
}
