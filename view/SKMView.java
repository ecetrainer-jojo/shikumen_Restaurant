package view;

import Utils.IOUtils;
import dao.adminDao;
import domain.Admin;
import service.ServiceHandler;
import dao.EmployeeDao;

/**
 * @Author ziangliang
 * @Date 2022-03-15
 * @Version 1.0
 * This is main view of the SHIKUMEN ordering system
 */
public class SKMView {

    //Parameters we need to set up
    public static ServiceHandler handler = new ServiceHandler();


    public static void main(String[] args) {
        SKMView.mainMenu();
    }

    /**
     * Function: The primary menu of the ShiKuMen Management System
     * It will lead to either login into the employee login page or exist
     */
    public static void mainMenu(){

        IOUtils.printFormattedTitle("Welcome to SHIKUMEN System");
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
            loginMenu();
        }
        //ultimately will reach here
        System.out.println("Exist the SHIKUMEN System");

    }

    /**
     * Function: The login menu will ask the employee to login and do the management
     * The employee number is an integer, but stored as the String (Max length 10)
     * The password is a combination of numbers and letters (Max length 10)
     * Allow user to press "ENTER_KEY" exist in the login page
     * It will lead to a listMenu to enjoy more inner-functions
     */

    public static void loginMenu(){
        String empNo;
        String password;
        boolean loginSuccess = false;
        boolean exitControl = false;
        Admin loginUser = null;
        while(!loginSuccess && !exitControl) {
            IOUtils.printFormattedTitle("Welcome to the Employee Login Page");
            System.out.println("(TIPS: If you want to exist, please press enter in employee number)");
            System.out.println();
            System.out.print("Please enter your employee number: ");
            empNo = IOUtils.readString(10,"");
            if(empNo=="") break; //User select to exist in this case
            System.out.print("Please enter your password: ");
            password = IOUtils.readString(10);
            loginUser = handler.loginCheck(empNo,password);
            loginSuccess = (loginUser==null)?false:true;
            if(loginSuccess){
                System.out.println(loginUser);
                System.out.println();
                IOUtils.printFormattedInfo("login succeeded!");
                System.out.println("\t"+loginUser.getName()+"\t\t\t\tLast login: "+loginUser.getLastlogin());
                System.out.println();
                //going to the secondary Menu and collect the feedback
                exitControl = listMenu();
                //if existControl is false then must return as a logout
                if(!exitControl) loginSuccess=false;
            }
            else{
                IOUtils.printFormattedInfo("login failed!");
                System.out.println();
                System.out.println("Would you like to try again? ");
                char answer = IOUtils.readConfirmSelection();
                if(answer=='N') exitControl = true;
            }

        }
    }

    /**
     * Function: secondary level list to give the user lists of operation
     * @return a boolean represent whether the user wants to exist whole system
     *          if true: wants to exit
     */

    public static boolean listMenu(){
        boolean logoutControl = false;
        boolean exitControl = false;
        while(!logoutControl && !exitControl){
            IOUtils.printFormattedTitle("Welcome to SHIKUMEN operation list");
            System.out.println("\t\t\t\t 1  Monitor Table Status");
            System.out.println("\t\t\t\t 2  Book a meal");
            System.out.println("\t\t\t\t 3  Show all the dishes");
            System.out.println("\t\t\t\t 4  Take order");
            System.out.println("\t\t\t\t 5  Display the bill");
            System.out.println("\t\t\t\t 6  Cancel a booking");
            System.out.println("\t\t\t\t 7  Checkout a table");
            System.out.println("\t\t\t\t 8  Logout");
            System.out.println("\t\t\t\t 9  Exit SHIKUMEN Management System");
            System.out.print("Enter your choice: ");
            int choice = IOUtils.readInt();
            System.out.println();

            //start the branching cases
            switch(choice){
                case 1:{
                    IOUtils.printFormattedTitle("SHIKUMEN seats status");
                    handler.seatsDisplay();
                    break;
                }
                case 2:{
                    //initialize two boolean to control the loop
                    boolean reSelect = true;
                    while(reSelect) {
                        IOUtils.printFormattedTitle("SHIKUMEN seats booking");
                        System.out.print("Please enter the seat you want to book (-1 to exit): ");
                        int seatNum = handler.seatSelection();
                        System.out.println();
                        //if select -1 then exit
                        if (seatNum == -1) reSelect = false;
                        else if(handler.checkSeatAvailable(seatNum)){
                            System.out.println("Please enter your name for booking: ");
                            String customerName = IOUtils.readString(10);

                            //let the user confirm the booking
                            System.out.println("Are you sure you want to make the booking?");
                            char readConfirm = IOUtils.readConfirmSelection();
                            if(readConfirm=='N') break;

                            handler.bookSeat(seatNum,customerName);
                            reSelect = false;
                            IOUtils.printFormattedInfo("Booking succeeded!");
                        }
                        else{
                            System.out.println("Sorry the seat is either booked or current in use!");
                            IOUtils.printFormattedInfo("Booking failed!");
                        }
                        System.out.println();
                    }

                    break;
                }
                case 3:{
                    break;
                }
                case 4:{
                    break;
                }
                case 5:{
                    break;
                }
                case 6:{
                    break;

                }
                case 7:{
                    break;

                }
                case 8:{
                    logoutControl = true;
                    break;
                }
                case 9:{
                    exitControl = true;
                    break;
                }
                default:
                    System.out.println("Wrong input, please try again");
                    System.out.println();
            }
        }
        //return whether the user want to exit
        return exitControl;
    }
}
