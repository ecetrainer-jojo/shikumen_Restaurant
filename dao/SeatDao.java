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

    public List<Seat> displaySeats(){
        String selectSql = "Select * From seat";
        List<Seat> seats = multiSelect(selectSql,Seat.class);
        return seats;
    }
}
