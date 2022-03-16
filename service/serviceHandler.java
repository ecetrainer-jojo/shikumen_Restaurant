package service;

import dao.EmployeeDao;
import dao.adminDao;

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
    public boolean loginCheck(adminDao admin, String username, String password){
        String selectSql = "Select count(*) From admin where accountno = ? AND password = ?";
        int res = admin.checkRecord(username,password);
        if(res==1){
            int affectRows =  admin.stampTime(username, password);
            //output the login time info
            if(affectRows==0) System.out.println("Login Timestamp updated failed");
            else System.out.println("Login Timestamp updated successfully");
            return true;
        }
        return false;
    }
}
