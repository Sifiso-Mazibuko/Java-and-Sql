
package employeeapp;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import za.ac.tut.bl.EmployeesManagerDB;
import za.ac.tut.entity.Employee;

/**
 *
 * @author sifiso
 */
public class EmployeeApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ParseException {
        // TODO code application logic here
        
        int choice, empID;
        
        Employee emp;
        
        EmployeesManagerDB db = 
        new EmployeesManagerDB("jdbc:derby://localhost:1527/EmployeesDB;create=true", "app", "123");
        
        choice = displayChoices();
        
        while(choice !=6){
        
            switch(choice){
            
                case 1:
                    emp = getEmployee();
                    
                    db.add(emp);
                    break;
                
                case 2:
                    //delete
                    empID = getEmployeeID();
                    
                    db.delete(empID);
                    break;
                    
                case 3:
                    //update
                    emp = getEmployeeToUpdate();
                    
                    db.update(emp);
                    break;
                 
                case 4:
                    //get
                    empID = getEmployeeID();
                    emp =  db.get(empID);
                    displayGet(emp);
                    break;
                    
                case 5:
                    //get all
                    List<Employee> emps = db.getAll();

                    displayGetAll(emps);
                    break;
                    
                default:
                    System.out.println(choice + " is invalid.");
            }
            
            choice = displayChoices();
        }
        
    }
    
    private static int displayChoices(){
        
        int choice;
        
        Scanner sc = new Scanner(System.in);
                
        System.out.print("\nPlease select one of the following options: " + "\n" +
        "1 - add employee" + "\n" +
        "2 - delete employee" + "\n" +
        "3 - update employee" + "\n" +
        "4 - get employee" + "\n" +
        "5 - get all employee" + "\n" +
        "6 - exit" + "\n\n" +
        "Your choice: ");
        choice = sc.nextInt();
        
        return choice;
    } 
    
    private static Employee getEmployee() throws ParseException{
    
        Integer empID;
        
        String name, surname,dob;
        
        Character gender;
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Please enter employee id: ");
        empID = sc.nextInt();
        
        System.out.print("Please enter employee name: ");
        name = sc.next();
        
        System.out.print("Please enter employee surname: ");
        surname = sc.next();
        
        System.out.print("Please enter employee gender: ");
        gender = sc.next().charAt(0);

        System.out.print("Please enter employee date of birth (yyyy-mm-dd): ");
        dob = sc.next();
        Date date = Date.valueOf(dob);
        
        Employee emp = new Employee(empID, name, surname, gender, date);

        return emp;
    }
    
    private static Integer getEmployeeID(){
    
        int empID;
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Please enter employee id: ");
        empID = sc.nextInt();
        
        return empID;
        
    }
    
    private static Employee getEmployeeToUpdate(){
    
        String name;
        Integer idNo;
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("Please enter employee name: ");
        name = input.nextLine();
        
        System.out.println("Please enter employee ID:");
        idNo = input.nextInt();
        
        Employee emp = new Employee();
        
        emp.setIdNo(idNo);
        emp.setName(name);
        
        return emp;
    }
    
    private static void displayGet(Employee emp){
    
        System.out.println(emp.toString());
    }
    
    private static void displayGetAll(List<Employee> emps){
    
        System.out.println(emps +"\n");

    }
}
