package dao;

import domain.Bill;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @Author ziangliang
 * @Date 2022-03-17
 * @Version 1.0
 */
public class BillDao extends BasicDao<Bill> {

    //insert a record into the table when the user make an order
    public int makeOrder(int seatID, int dishID, int amount, double totalPrice){
        //write the sql to insert the value
        String insertSql = "Insert into bills values(null, ?,?,?,?,'unpaid')";
        //update the database by inserting the record
        return update(insertSql,seatID,dishID,amount,totalPrice);
    }

    //select the required record from the table for a bill with a certain table
    public List<Bill> searchBill(int seatID){
        //write the select bill
        String selectSql = "Select dishName,amount,bills.price FROM bills,dishes where seatID = ? " +
                "and bills.dishID = dishes.dishID and `status` = 'unpaid'";
        return multiSelect(selectSql, Bill.class ,seatID);
    }

    //update the bill into paid
    public int payBill(int seatID){
        //write the update SQL
        String updateSql = "Update bills SET `status`='paid' where (seatID=? AND `status`='unpaid')";
        return update(updateSql,seatID);
    }

    //insert the final bills into the income
    public int insertIncome(double totalAmount,String paymentType){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String billDate = LocalDateTime.now().format(formatter);
        String insertSql = "Insert INTO income values(null,?,?,?)";
        return update(insertSql,totalAmount,billDate,paymentType);
    }
}
