package dao;

import domain.Admin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author ziangliang
 * @Date 2022-03-16
 * @Version 1.0
 */
public class adminDao extends BasicDao<Admin>{

    //default constructor
    public adminDao() {
    }

    /**
     * Function: check if the account and password matches any on the database
     *           return the required info for the service handler
     * @param username username input of an admin
     * @param password password input of an admin
     * @return an Admin object contains the info of an admin
     */

    public Admin checkRecord(String username, String password){
        String selectSql = "Select * From admin where accountno = ? AND password = MD5(?)";
        Admin loginUser = singleSelect(selectSql, domain.Admin.class,username,password);
        //casting to be an Admin
        return loginUser;
    }

    /**
     * Function: update the login timestamp for the loginUser
     * @param username username input of an admin
     * @param password password input of an admin
     * @return an integer represent the number of affected rows
     */

    public int stampTime(String username,String password){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String newTimeStamp = LocalDateTime.now().format(formatter);
        String updateSql = "Update admin set lastlogin=? where accountno = ? AND password = MD5(?)";
        return  update(updateSql,newTimeStamp,username,password);
    }


}
