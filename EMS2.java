import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Key;
import java.util.*;

public class EMS2 {
    public static void main(String[] args) throws IOException {

        //display a welcome message
        System.out.println("Welcome to Employee Management System (EMS)");

        //create the database
        Map <Integer, Map<String, Object>> database = load(args[0]);

        //Create a loop, display the menu, and interact with the user (each menu option will call the
        //appropriate method corresponding that menu option
        int selection;
        do {
            selection = menu();

            if (selection == 1) {
                addEmployee(database);
            } else if (selection == 2) {
                findEmployee_EID(database);
            } else if (selection == 3) {
                findEmployee_name(database);
            } else if (selection == 4) {
                deleteEmployee(database);
            } else if (selection == 5) {
                displayStat(database);
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

    public static void addEmployee(Map<Integer, Map <String, Object>> database ) {
        String EID;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter an employee ID or QUIT to stop:");
        EID = keyboard.nextLine();

        //input validation
        if (EID.equalsIgnoreCase("quit")) {
            return;
        }
        while (Integer.parseInt(EID) <= 0) {
            System.out.println("Invalid ID. Employee ID should be a positive integer number.");
            System.out.println("Enter an employee ID or QUIT to stop:");
            EID = keyboard.nextLine();

            if (EID.equalsIgnoreCase("quit")) {
                return;
            }
        }

        while (database.containsKey(Integer.parseInt(EID))) {
            System.out.println("Employee ID: "+ EID+ " already exists in the database.");
            System.out.println("Enter an employee ID or QUIT to stop:");

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
        salary = Double.parseDouble(keyboard.nextLine());

        while (salary <= 0) {
            System.out.println("Invalid salary. Salary should be a positive number.");
            System.out.println("Enter employee salary:");
            salary = Double.parseDouble(keyboard.nextLine());
        }

        Map <String, Object> employee = new HashMap<>();
        //associate the EID with database and employee stats
        employee.put("EID", Integer.parseInt(EID));
        employee.put("Name", name);
        employee.put("Department", department);
        employee.put("Title", title);
        employee.put("Salary", salary);

        database.put(Integer.valueOf(EID), employee);

    }

    public static void findEmployee_EID (Map<Integer, Map <String, Object>> database ){

        int EID;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter an employee ID or QUIT to stop:");
        EID = keyboard.nextInt();

        //input validation
        while (EID <= 0) {
            System.out.println("Invalid ID. Employee ID should be a positive integer number.");
            System.out.println("Enter an employee ID or QUIT to stop:");
            EID = keyboard.nextInt();

            if (String.valueOf(EID).equalsIgnoreCase("quit")) {
                break;
            }
        }

        if (database.containsKey(EID)) {
            System.out.println("Employee ID: "+ EID);
            System.out.println("\tName: " + database.get(EID).get("Name"));
            System.out.println("\tDepartment: " + database.get(EID).get("Department"));
            System.out.println("\tTitle: " + database.get(EID).get("Title"));
            System.out.printf("\tSalary: %,.2f\n",database.get(EID).get("Salary"));

        }
    }

    public static void findEmployee_name(Map<Integer, Map <String, Object>> database){
        System.out.println("Enter an employee name or QUIT to stop:");
        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.nextLine();

        int numFound = 0;
        ArrayList<Integer>  EIDs = new ArrayList<>();
        if (input.equalsIgnoreCase("quit")){
            return;
        }
        else {
            for (Map <String, Object> employee: database.values()){
                if (employee.get("Name").toString().equalsIgnoreCase(input)){
                    ++numFound;
                    EIDs.add((Integer.parseInt((String) employee.get("EID"))));
                }
            }

            if (numFound==1){
                System.out.println("Found "+numFound+" employee with that name.");
            }
            else {
                System.out.println("Found " + numFound + " employees with that name.");
            }

            if (numFound!=0){
                for (int i : EIDs){
                    System.out.println("Employee ID: " + i);
                    System.out.println("\tName: " + database.get(i).get("Name"));
                    System.out.println("\tDepartment: " + database.get(i).get("Department"));
                    System.out.println("\tTitle: " + database.get(i).get("Title"));
                    System.out.printf("\tSalary: %,.2f\n",database.get(i).get("Salary"));
                }
            }
        }
    }

    public static void deleteEmployee(Map<Integer, Map <String, Object>> database){
        int EID;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter an employee ID or QUIT to stop:");
        EID = Integer.parseInt(keyboard.nextLine());

        //input validation
        while (EID <= 0) {
            System.out.println("Invalid ID. Employee ID should be a positive integer number.");
            System.out.println("Enter an employee ID or QUIT to stop:");
            EID = keyboard.nextInt();

            if (String.valueOf(EID).equalsIgnoreCase("quit")) {
                break;
            }
        }

        if (database.containsKey(EID)) {
            System.out.println("Employee ID: "+EID+" will be deleted from the database. Are you sure (y/n)?");

            String choice = keyboard.nextLine();

            if (choice.equalsIgnoreCase("y")) {
                database.remove(EID);
                System.out.println("Employee ID: "+ EID +" was deleted from the database.");
            }
            else
            {
                System.out.println("Employee deletion was cancelled.");
            }
        }
        else{
            System.out.println("None existing key!");
        }
    }

    public static void displayStat(Map<Integer, Map <String, Object>> database){


        //create a HashMap to hold all the data
        HashMap<String, Integer> stats = new HashMap<String, Integer>();

        //save all into a temp Map
        for (Map <String, Object> employee :database.values()){
            if(!stats.containsKey(employee.get("Department"))){

                stats.put((String) employee.get("Department"), 1);
            }
            else {
                stats.put((String) employee.get("Department"), stats.get(employee.get("Department"))+1);
            }
        }

        //sort according to key
        TreeMap <String, Integer> stats_sorted = new TreeMap<>();
        stats_sorted.putAll(stats);

        //if it's empty
        if (stats.size() == 0){
            System.out.println("There are no departments in the database.");
            System.out.println("Employee database is empty.");
            return;
        }


        //display all the stats
        int num=0;
        System.out.println("Department Statistics:");

        if (stats.size()==1){
            for (String department: stats_sorted.keySet()) {
                System.out.println("\tDepartment: " + department + " - " + stats.get(department) + " employee");
                System.out.printf("There is %d department in the database.\n", stats.size());
                System.out.printf("There is %d employee in the database.\n", stats.get(department));
                return;
            }
        }
        for (String department: stats_sorted.keySet()){
            System.out.println("\tDepartment: " + department + " - " + stats.get(department) + " employees");
            num= num + stats.get(department);
        }

        System.out.printf("There are %d departments in the database.\n", stats.size());
        System.out.printf("There are %d employees in the database.\n", num);
    }

    public static void displayEmployee(Map<Integer, Map <String, Object>> database){

        int counter = 0;
        for (int eid : database.keySet()){
            System.out.println("Employee ID: " + eid);
            System.out.println("\tName: " + database.get(eid).get("Name"));
            System.out.println("\tDepartment: " + database.get(eid).get("Department"));
            System.out.println("\tTitle: " + database.get(eid).get("Title"));
            System.out.printf("\tSalary: %,.2f\n",database.get(eid).get("Salary"));
            ++counter;
        }

        if (counter ==0){
            System.out.println("Employee database is empty.");
        }
        else {

            if (counter > 1) {
                System.out.println("There are " + counter + " employees in the database.");
            }
            else {
                System.out.println("There is " + counter + " employee in the database.");
            }
        }
    }

    public static Map<Integer, Map<String, Object>> load(String fileName) throws IOException {

        //create the en empty Map to contain the data and for future return
        Map<Integer, Map<String, Object>> dataBase = new HashMap<>();

        File inputFile = new File(fileName);
        String [] token;
        String lineToRead;

        if (inputFile.exists()){
            Scanner fileToRead = new Scanner(inputFile);
            while (fileToRead.hasNext()){
                Map<String, Object> employee = new HashMap<>();
                lineToRead = fileToRead.nextLine();
                token = lineToRead.split("[,\"\']+");
                employee.put("EID", token[0]);
                employee.put("Name", token[1]);
                employee.put("Department", token[2]);
                employee.put("Title", token[3]);
                employee.put("Salary", Double.parseDouble(token[4]));

                dataBase.put(Integer.valueOf(token[0]), employee);
            }
            fileToRead.close();
        }
        else {
            System.out.println("Database input file " + fileName +" not found.");
            System.out.println("Creating an empty database.");
        }


        return dataBase;
    }

    public static void save(String fileName, Map<Integer, Map<String, Object>> dataBase){

    }
}
