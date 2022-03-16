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
    //default constructor
    public Seat(){}

    public Seat(Integer seatID, String status, String diningTime, String servant) {
        this.seatID = seatID;
        this.status = status;
        this.diningTime = diningTime;
        this.servant = servant;
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

    public void setServant(String servant) {
        this.servant = servant;
    }

    @Override
    public String toString() {
        if(diningTime==null) diningTime = "Not Occupied ";
        if(servant.equals("")) servant = "Not Served ";
        return  "\t\t"+seatID +
                "\t\t" + status +
                "\t\t" + diningTime +
                "\t\t" + servant;
    }
}
