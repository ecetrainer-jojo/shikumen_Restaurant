package service;

import dao.EmployeeDao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * @Author ziangliang
 * @Date 2022-03-15
 * @Version 1.0
 */
public class serviceHandler {

    /**
     * Function: link to the database to see the availability to login
     *            and also write the timestamp into the database
     * @param employeeDao employeeDao object
     * @param username a string contains username
     * @param password a string contains password
     * @return a boolean to reveal if login is successful
     */
    public boolean loginCheck(EmployeeDao employeeDao, String username, String password){
        String selectSql = "Select count(*) From admin where accountno = ? AND password = ?";
        long res = (long) employeeDao.itemSelect(selectSql,username,password);
        if(res==1){
            //grant the current datetime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String newTimeStamp = LocalDateTime.now().format(formatter);
            System.out.println(newTimeStamp);
            String updateSql = "Update admin set lastlogin=? where accountno = ? AND password = ?";
            int affectRows = employeeDao.update(updateSql,newTimeStamp,username,password);

            //output the login time info
            if(affectRows==0) System.out.println("Login Timestamp updated failed");
            else System.out.println("Login Timestamp updated successfully");

            //close the connection
            employeeDao.disconnect();
            return true;
        }
        //close the connection
        employeeDao.disconnect();
        return false;
    }
}
