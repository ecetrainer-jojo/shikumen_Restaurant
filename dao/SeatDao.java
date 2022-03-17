package dao;

import domain.Seat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author ziangliang
 * @Date 2022-03-16
 * @Version 1.0
 */
public class SeatDao extends BasicDao<Seat>{

    //function to display seating using select SQL
    public List<Seat> displaySeats(){
        String selectSql = "Select * From seat";
        List<Seat> seats = multiSelect(selectSql,Seat.class);
        return seats;
    }

    //function to return a seat object upon the given ID
    public Seat checkSeat(int seatID){
        String selectSql = "Select * From seat where seatID=?";
        Seat seat = singleSelect(selectSql,Seat.class,seatID);
        return seat;
    }

    //function to update the status of the Seat in database
    public int bookSeat(int seatID,String customerName){
        String nameSql = "Update seat SET customerName=? where seatID=?";
        int nameUpdate = update(nameSql,customerName,seatID);
        String statusSql = "Update seat SET status = 'Booked' where seatID=?";
        int statusUpdate = update(statusSql,seatID);
        //return the rows affected through the update
        return nameUpdate + statusUpdate;
    }

    //function to update the status of the Seat in database
    public int cancelSeat(int seatID){
        String statusSql = "Update seat SET status = 'Empty' where seatID=?";
        String nameSql = "Update seat SET customerName = '' where seatID=?";
        int statusUpdate = update(statusSql,seatID);
        int nameUpdate = update(nameSql,seatID);
        //return the rows affected through the update
        return nameUpdate + statusUpdate;
    }



}
