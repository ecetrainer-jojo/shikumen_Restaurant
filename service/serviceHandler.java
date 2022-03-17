package service;

import Utils.IOUtils;
import dao.DishesDao;
import dao.EmployeeDao;
import dao.SeatDao;
import dao.adminDao;
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
                        "\t\t"+String.format("%-15s", "Dining Time")+
                "\t\t"+String.format("%-15s", "Servant")+
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
        tableNum = IOUtils.readInt();
        //The seat number is between 1 and 7
        while((tableNum<1 || tableNum>7) && (tableNum!=-1)){
            System.out.print("Please enter a valid seatID (1-7) or -1 to exit: ");
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
        return (targetSeat.getStatus().equals("Booked"))?true:false;
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


}
