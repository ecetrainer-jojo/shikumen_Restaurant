package Utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @Author ziangliang
 * @Date 2022-03-13
 * @Version 1.0
 */
public class DruidUtils {
    private static DataSource ds;
    //initialize ds in the static block
    static{
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File cannot be located");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("errors in the properties file");
        }

    }

    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection =  ds.getConnection();
        } catch (SQLException e) {
            System.out.println("Cannot connect with SQL Server");
            return null;
        }
        return connection;
    }

    public static void close(ResultSet resultSet, Statement statement, Connection connection){
        try {
                if(resultSet!=null)  resultSet.close();
                if(statement!=null) statement.close();
                if(connection!=null) connection.close();
        }
        catch (SQLException throwables) {
            System.out.println("Resources cannot be closed");
            throw new RuntimeException(throwables);
        }

    }
}
