package service;

import Utils.IOUtils;
import dao.*;
import domain.Admin;
import domain.Dishes;
import domain.Seat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Iterator;
import java.util.List;

/**
 * @Author ziangliang
 * @Date 2022-03-15
 * @Version 1.0
 */
public class ServiceHandler {

    /**
     * Function: link to the database to see the availability to login
     *            and also write the timestamp into the database
     * @param username a string contains username
     * @param password a string contains password
     * @return an Admin object to the view handler
     */
    public Admin loginCheck( String username, String password){
        //create a new adminDao
        adminDao admin = new adminDao();
        String selectSql = "Select count(*) From admin where accountno = ? AND password = ?";
        Admin res = (Admin) admin.checkRecord(username,password);
        if(res!=null){
            int affectRows =  admin.stampTime(username, password);
            //output the login time info
            if(affectRows==0) System.out.println("Login Timestamp updated failed");
            else System.out.println("Login Timestamp updated successfully");
            return res;
        }
        return null;
    }

    /**
     * Function: print out the seats and their status
     */
    public void seatsDisplay(){
        //create a seatDao
        SeatDao seatDao = new SeatDao();
        List<Seat> seats = seatDao.displaySeats();
        System.out.println(String.format("%-10s", "SeatID") +
                        "\t\t"+String.format("%-10s", "Status")+
                        "\t\t"+String.format("%-20s", "Dining Time")+
                "\t\t"+String.format("%-10s", "Servant")+
                "\t\t"+String.format("%-10s", "Customer Name"));
        System.out.println("---------------------------------------------------------------------------------------");

        //print out the seat status
        for(Seat seat:seats){
            System.out.println(seat);
        }
        System.out.println();
    }

    /**
     * Function: Prompt to accept user's input
     */
    public int seatSelection(){
        int tableNum = 0;
        System.out.print("Please enter the seatID you want to select (-1 to exit): ");
        tableNum = IOUtils.readInt();
        //The seat number is between 1 and 7
        while((tableNum<1 || tableNum>7) && (tableNum!=-1)){
            System.out.print("Please re-enter a valid seatID (1-7) or -1 to exit: ");
            tableNum = IOUtils.readInt();
        }
        return tableNum;
    }

    /**
     * Function: check if the seat has been occupied
     * @param seatID the number of the seat ready to check
     * @return boolean represents whether the set is available
     */
    public boolean checkSeatAvailable(int seatID){
        //create the SeatDao
        SeatDao seatDao = new SeatDao();
        Seat targetSeat = seatDao.checkSeat(seatID);
        return (targetSeat.getStatus().equals("Empty"))?true:false;
    }

    /**
     * Function: check if the seat has been booked
     * @param seatID the number of the seat ready to check
     * @return boolean represents whether the set is available
     */
    public boolean checkSeatBooked(int seatID){
        //create the SeatDao
        SeatDao seatDao = new SeatDao();
        Seat targetSeat = seatDao.checkSeat(seatID);
        String status = targetSeat.getStatus();
        if(status.equals("Booked")||status.equals("Dining")){
            return true;
        }
        else return false;
    }

    /**
     * Function: update the database through the booking
     * @param seatID the number of the seat ready to check
     * @param customerName the name of the customer
     * @return boolean represents whether the set is available
     */

    public boolean bookSeat(int seatID, String customerName){
        //create the SeatDao
        SeatDao seatDao = new SeatDao();
        if(seatDao.bookSeat(seatID,customerName)==2){
            System.out.println("Your booking is confirmed!");
            return true;
        }
        System.out.println("Some errors occur in the booking process, please try again later");
        return false;
    }

    /**
     * Function: update the database through the ordering
     * @param seatID the number of the seat ready to order
     * @param operatorName the name of the operator (who logs in the system)
     * @return boolean represents whether the order could be made
     */

    public boolean orderSeat(int seatID, String operatorName){
        //create the SeatDao
        SeatDao seatDao = new SeatDao();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String diningTime = LocalDateTime.now().format(formatter);
        //invoke the order seat function from seatDao
        if(seatDao.orderSeat(seatID,diningTime,operatorName)==3){
            System.out.println("Your dining status is confirmed! Now you can start order!");
            return true;
        }
        System.out.println("Some errors occur in the confirming process, please try again later");
        return false;
    }

    /**
     * Function: update the database through the booking cancellation
     * @param seatID the number of the seat ready to check
     * @return boolean represents whether the set is available
     */

    public boolean cancelSeat(int seatID){
        //create the SeatDao
        SeatDao seatDao = new SeatDao();
        if(seatDao.cancelSeat(seatID)==2){
            System.out.println("Your cancellation is confirmed!");
            return true;
        }
        System.out.println("Some errors occur in the cancelling process, please try again later");
        return false;
    }


    /**
     * Function: prompt to do the last step for order authentication
     * @param seatID the number of the seat ready to order
     * @return boolean shows whether the customer name matches
     */
    public boolean confirmCustomerName(int seatID){
        SeatDao seatDao = new SeatDao();
        String customerName = seatDao.searchCustomerName(seatID);
        System.out.println();
        System.out.print("Please enter the customer name for order confirmation (Press Enter to quit): ");
        String nameEnter = IOUtils.readString(32,"");
        while(!nameEnter.equals(customerName)){
            if(nameEnter.equals("")) return false;
            else{
                System.out.println("The name entered does not match the booking record, please try again!");
                System.out.print("Please enter the customer name for order confirmation (Press Enter to quit): ");
                nameEnter = IOUtils.readString(32,"");
            }
        }
        return true;
    }

    /**
     * Function: print the food lists and their type & price
     */
    public void dishesDisplay(){
        //create a seatDao
        DishesDao dishesDao = new DishesDao();
        List<Dishes> dishes = dishesDao.displaySeats();
        System.out.println(String.format("%-10s", "DishID") +
                String.format("%-40s", "Name")+
                String.format("%-15s", "Type")+
                "Price"+
                "\t\t"+"Mandarin_Name");
        System.out.println("-----------------------------------------------------------------------------------------");

        //print out the dish status
        for(Dishes dish:dishes){
            System.out.println(dish);
        }

        System.out.println();
    }

    /**
     * Function: During the dishes ordering stage handler the interaction with BillDao
     *          Also allow the rolling back to the beginning
     * @param seatID the seat number for ordering the dishes
     */

    public void orderDishes(int seatID){
        //create a new bill dao
        BillDao billDao = new BillDao();
        //allow the transaction for this connection
        billDao.allowTransaction();
        //turn of the auto disconnect function since the transaction status should be maintained
        billDao.setAutoDisconnect(false);
        while(true){
            //Give the user option to quit on all the dishes they have ordered this time
            System.out.println();
            dishesDisplay();
            System.out.println();
            System.out.println("Tips: You can always press 0 when choosing dishID/amount to revert all the orders");
            //obtain the dishID
            System.out.print("Please enter the dishID you want to order (-1 to exit): ");
            int dishID = IOUtils.readInt();
            //handle some exceptions
            while(dishID<1 || dishID>21) {
                if(dishID==-1){
                    commitDishes(billDao);
                    return;
                }
                if(dishID==0){
                    revertDishes(billDao);
                    return;
                }
                System.out.print("Please enter a valid dishID (-1 to exit): ");
                dishID = IOUtils.readInt();
            }
            System.out.println();
            //obtain the dishAmount
            System.out.print("Please enter the amount you want to order (-1 to exit): ");
            int dishAmount = IOUtils.readInt();
            //Handle some exception
            while(dishAmount<1) {
                if(dishAmount==-1){
                    commitDishes(billDao);
                    return;
                }
                if(dishAmount==0){
                    revertDishes(billDao);
                    return;
                }
                System.out.print("Please enter a amount (-1 to exit): ");
                dishAmount = IOUtils.readInt();
            }
            //ask for the confirmation to make the order
            System.out.println("Please confirm to make this order");
            if(IOUtils.readConfirmSelection()=='N'){
                System.out.println("Please reconsider and make the order later");
                //let the user start over again
                break;
            }
            //get the price of the id
            double dishPrice = searchDishPrice(dishID);
            double totalPrice = dishPrice * dishAmount;
            //start the billDao handling
            if (billDao.makeOrder(seatID,dishID,dishAmount,totalPrice)==1){
                IOUtils.printFormattedInfo("Your order has been recorded!");
            }
            else{
                System.out.println("Something went wrong during the process, please again");
            }
            System.out.println();
            //ask if user want to make another order
            System.out.println("Do you want to make another order?");
            if(IOUtils.readConfirmSelection()=='N'){
                commitDishes(billDao);
                return;
            }
            //if not it will just keep on running
        }
    }

    /**
     * Function: helper function when user want to revert the orders
     * @param billDao the billDao object that has been used in the orderDishes process
     */

    public void revertDishes(BillDao billDao){
        IOUtils.printFormattedInfo("You choose to revert all the previous orders");
        System.out.println();
        billDao.rollback();
        billDao.disconnect();
    }

    /**
     * Function: helper function when user want to commit and quit the ordering process
     * @param billDao the billDao object that has been used in the orderDishes process
     */

    public void commitDishes(BillDao billDao){
        IOUtils.printFormattedInfo("Thank you ordering!");
        IOUtils.printFormattedInfo("Your orders are submitted");
        System.out.println();
        billDao.commit();
        billDao.disconnect();
    }

    /**
     * Function: search the price of certain dish
     * @return a double represent the price of certain dish
     */

    public double searchDishPrice(int dishID){
        DishesDao dishesDao = new DishesDao();
        return dishesDao.searchPrice(dishID);
    }




}
