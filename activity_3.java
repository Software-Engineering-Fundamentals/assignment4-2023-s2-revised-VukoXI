package model;

import java.util.ArrayList;
import java.util.List;


public class Department {
    
    private String name;
    
    private ArrayList<String> ID1 = new ArrayList<String>();
    
    private ArrayList<String> temp1 = new ArrayList<String>();

    //private ArrayList<int> employee = new ArrayList<int>(); // cannot use generics with primative eg int should be integer in the array list 
    private ArrayList<Integer> employee = new ArrayList<Integer>();
    
    private int ID2;

    //private String name; //this is a duplicate therefore needs to be removed

    public String getID() {
        return ID2;
    }

    public void setID(int ID) {
        this.ID2 = ID;
    }

    //refractor
 public boolean checkEmployee(int eID){
    //better readability and maintainability
    // Use enhanced for loop for better readability
    for (int employeeID : this.employee) {
        if (employeeID == eID) {
            System.out.println("Employee exists");
            return true;
        }
    }
    // If the loop completes without finding a match, indicate that the employee doesn't exist
    // Moved the "Employee doesn't exist" message outside the loop for clarity
    System.out.println("Employee doesn't exist");
    return false;
}

    

    public void assignNew(String temp2, int ID) { //minor but capitalisation for string to String 
        this.temp1.add(temp2);
        this.ID1.add(String.valueOf(ID)); // Corrected the parameter type and conversion
    }
}