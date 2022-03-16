package domain;

/**
 * @Author ziangliang
 * @Date 2022-03-15
 * @Version 1.0
 */
public class admin {
    public String employeeNo;
    public String name;
    public String accountNo;
    public String password;
    public String lastLoginTime;

    public void admin(){

    }

    public admin(String employeeNo, String name, String accountNo, String password, String lastLoginTime) {
        this.employeeNo = employeeNo;
        this.name = name;
        this.accountNo = accountNo;
        this.password = password;
        this.lastLoginTime = lastLoginTime;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
