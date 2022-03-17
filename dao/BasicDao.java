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
    private boolean autoDisconnect = true;

    public void setAutoDisconnect(boolean autoDisconnect) {
        this.autoDisconnect = autoDisconnect;
    }

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
            if(autoDisconnect) disconnect();
            return affectedRow;
        }
    }

    //return multi-object ->multi-row queries
    public <T>List<T> multiSelect(String sql, Class<T> daoType, Object... parameters){
        ensureConnected();
        try {
            return qr.query(connection,sql, new BeanListHandler<>(daoType),parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            if(autoDisconnect) disconnect();
        }
    }

    public <T> T singleSelect(String sql, Class<T> daoType, Object... parameters){
        ensureConnected();
        try {
            return qr.query(connection,sql, new BeanHandler<>(daoType),parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            if(autoDisconnect) disconnect();
        }
    }

    public Object itemSelect(String sql, Object... parameters){
        ensureConnected();
        try {
            return qr.query(connection,sql, new ScalarHandler(),parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            if(autoDisconnect) disconnect();
        }
    }

    public void disconnect(){
        DruidUtils.close(null,null,connection);
    }

    public void ensureConnected(){
        try {
            if(connection.isClosed()){
                connection = DruidUtils.getConnection();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //allow the transaction (commit rollback during the process)
    public void allowTransaction(){
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //allow the transaction to be committed
    public void commit(){
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //allow the transaction to rollback
    public void rollback(){
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
