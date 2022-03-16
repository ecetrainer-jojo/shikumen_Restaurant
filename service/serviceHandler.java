package service;

import dao.EmployeeDao;
import dao.SeatDao;
import dao.adminDao;
import domain.Admin;
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
     * Function: link to the database to see the availability to login
     *            and also write the timestamp into the database
     */
    public void seatsDisplay(){
        //create a seatDao
        SeatDao seatDao = new SeatDao();
        List<Seat> seats = seatDao.displaySeats();
        System.out.println("\t"+"SeatID" +
                        "\t\t"+"Status" +
                        "\t\t"+"Dining Time"+
                "\t\t\t"+"Servant");
        System.out.println("--------------------------------------------------------------");

        //print out the seat status
        for(Seat seat:seats){
            System.out.println(seat);
        }
        System.out.println();
    }


}
