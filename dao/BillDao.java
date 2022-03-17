package dao;

import domain.Bill;

/**
 * @Author ziangliang
 * @Date 2022-03-17
 * @Version 1.0
 */
public class BillDao extends BasicDao<Bill> {

    //insert a record into the table when the user make an order
    public int makeOrder(int seatID, int dishID, int amount, double totalPrice){
        //write the sql to insert the value
        String insertSql = "Insert into bills values(null, ?,?,?,?)";
        //update the database by inserting the record
        return update(insertSql,seatID,dishID,amount,totalPrice);
    }
}
