package dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author ziangliang
 * @Date 2022-03-16
 * @Version 1.0
 */
public class adminDao<admin> extends BasicDao{

    //default constructor
    public adminDao() {
    }

    public int checkRecord(String username, String password){
        String selectSql = "Select count(*) From admin where accountno = ? AND password = ?";
        long res = (long) itemSelect(selectSql,username,password);
        //casting to be an integer
        return (int)res;
    }

    public int stampTime(String username,String password){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String newTimeStamp = LocalDateTime.now().format(formatter);
        String updateSql = "Update admin set lastlogin=? where accountno = ? AND password = ?";
        return  update(updateSql,newTimeStamp,username,password);
    }

}
