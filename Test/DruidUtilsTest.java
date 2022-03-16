package Test;

import Utils.DruidUtils;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author ziangliang
 * @Date 2022-03-15
 * @Version 1.0
 */
class DruidUtilsTest {

    /**
     * Just read from the list 1:5
     * @Test Testing druid connection to the mysql server
     */

    @org.junit.jupiter.api.Test
    void getConnection() {
        Connection connection = DruidUtils.getConnection();
        System.out.println("connection success");
        DruidUtils.close(null,null,connection);


    }
}