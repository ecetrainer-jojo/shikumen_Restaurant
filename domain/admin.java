package domain;

/**
 * @Author ziangliang
 * @Date 2022-03-15
 * @Version 1.0
 */
public class Admin {
    public Integer empno;
    public String name;
    public String accountNo;
    public String password;
    public String lastlogin;

    public Admin(){

    }

    public Admin(Integer empno, String name, String accountNo, String password, String lastlogin) {
        this.empno = empno;
        this.name = name;
        this.accountNo = accountNo;
        this.password = password;
        this.lastlogin = lastlogin;
    }

    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
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

    public String getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(String lastlogin) {
        this.lastlogin = lastlogin;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "empno=" + empno +
                ", name='" + name + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", password='" + password + '\'' +
                ", lastlogin='" + lastlogin + '\'' +
                '}';
    }
}
