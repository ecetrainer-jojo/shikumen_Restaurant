package view;

import Utils.IOUtils;
import service.serviceHandler;
import dao.EmployeeDao;

/**
 * @Author ziangliang
 * @Date 2022-03-15
 * @Version 1.0
 * This is main view of the SHIKUMEN ordering system
 */
public class SKMView {

    //Parameters we need to set up
    public static serviceHandler handler = new serviceHandler();


    public static void main(String[] args) {
        SKMView.mainMenu();
    }

    /**
     * Function: The primary menu of the ShiKuMen Management System
     * It will lead to either login into the employee login page or exist
     */
    public static void mainMenu(){

        System.out.println("==============Welcome to ShiKuMen System==============");
        System.out.println("\t\t 1 Login with your account");
        System.out.println("\t\t 2 Exist the ShiKuMen System");
        System.out.println();
        System.out.print("Please input your choice: ");

        int answer = IOUtils.readInt();
        //exception handle for unaccepted choice
        while(answer!=1 && answer!=2){
            System.out.print("Please enter a correct choice: ");
            answer = IOUtils.readInt();
        }

        System.out.println();
        //fork the choice
        if(answer==1){
            System.out.println("Welcome to the Employee Login Page");
            loginMenu();
        }
        //ultimately will reach here
        System.out.println("Exist the ShiKuMen System");

    }

    /**
     * Function: The login menu will ask the employee to login and do the management
     * The employee number is an integer, but stored as the String (Max length 10)
     * The password is a combination of numbers and letters (Max length 10)
     * It will lead to a
     */

    public static void loginMenu(){
        String empNo;
        String password;
        boolean loginSuccess = false;
        while(!loginSuccess) {
            System.out.print("Please enter your employee number: ");
            empNo = IOUtils.readString(10);
            System.out.print("Please enter your password: ");
            password = IOUtils.readString(10);
            loginSuccess = handler.loginCheck(new EmployeeDao(),empNo,password);
            if(loginSuccess){
                System.out.println("==============login succeeded!==============");
                //going to the secondary Menu
            }
            else{
                System.out.println("==============login failed!==============");
                System.out.println("Would you like to try again? ");
                char answer = IOUtils.readConfirmSelection();
                if(answer=='N') break;
            }

        }
    }

}
