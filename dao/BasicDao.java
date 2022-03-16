package dao;


import Utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author ziangliang
 * @Date 2022-03-14
 * @Version 1.0
 */
//<T> means do not know the type
public class BasicDao<T> {
    private QueryRunner qr;
    private Connection connection;

    public BasicDao(){
        qr = new QueryRunner();
        connection = DruidUtils.getConnection();
    }


    //the general dml methods
    public int update(String sql, Object... parameters) {
        ensureConnected();
        int affectedRow = 0;
        try {
            affectedRow =  qr.update(connection, sql, parameters);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            disconnect();
            return affectedRow;
        }
    }

    //return multi-object ->multi-row queries
    public <T>List<T> multiSelect(String sql, Class<T> daoType, Object... parameters){
        ensureConnected();
        try {
            return qr.query(connection,sql, new BeanListHandler<>(daoType),parameters);
        } catch (SQLException e) {
            throw new RuntimeException("Multi-Select SQL went wrong...");
        }finally {
            disconnect();
        }
    }

    public <T> T singleSelect(String sql, Class<T> daoType, Object... parameters){
        ensureConnected();
        try {
            return qr.query(connection,sql, new BeanHandler<>(daoType),parameters);
        } catch (SQLException e) {
            throw new RuntimeException("Single-Select SQL went wrong...");
        }finally {
            disconnect();
        }
    }

    public Object itemSelect(String sql, Object... parameters){
        ensureConnected();
        try {
            return qr.query(connection,sql, new ScalarHandler(),parameters);
        } catch (SQLException e) {
            throw new RuntimeException("Item-Select SQL went wrong...");
        }finally {
            disconnect();
        }
    }

    public void disconnect(){
        DruidUtils.close(null,null,connection);
    }

    public void ensureConnected(){
        connection = DruidUtils.getConnection();
    }

}
