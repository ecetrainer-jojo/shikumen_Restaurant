package domain;

/**
 * @Author ziangliang
 * @Date 2022-03-16
 * @Version 1.0
 * CREATE TABLE seat(
 * seatID int PRIMARY KEY,
 * `status` VARCHAR(32) NOT NULL DEFAULT("Empty"),
 * diningTime TIMESTAMP,
 * servant VARCHAR(10) NOT NULL DEFAULT(""));
 */
public class Seat {
    private Integer seatID;
    private String status;
    private String diningTime;
    private String servant;
    private String customerName;
    //default constructor
    public Seat(){}

    public Seat(Integer seatID, String status, String diningTime, String servant, String customerName) {
        this.seatID = seatID;
        this.status = status;
        this.diningTime = diningTime;
        this.servant = servant;
        this.customerName = customerName;
    }


    public Integer getSeatID() {
        return seatID;
    }

    public void setSeatID(Integer seatID) {
        this.seatID = seatID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDiningTime() {
        return diningTime;
    }

    public void setDiningTime(String diningTime) {
        this.diningTime = diningTime;
    }

    public String getServant() {
        return servant;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setServant(String servant) {
        this.servant = servant;
    }

    @Override
    public String toString() {
        if(diningTime==null) diningTime = "Not Occupied ";
        if(servant.equals("")) servant = "Not Served ";
        if(customerName.equals("")) customerName = "Not registered ";
        return  (String.format("%-10s", seatID) +
                "\t\t"+String.format("%-10s", status)+
                "\t\t"+String.format("%-20s", diningTime)+
                "\t\t"+String.format("%-10s", servant)+
                "\t\t"+String.format("%-10s", customerName)
        );
    }
}
