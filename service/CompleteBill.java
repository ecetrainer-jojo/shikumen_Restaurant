package service;

/**
 * @Author ziangliang
 * @Date 2022-03-17
 * @Version 1.0
 */

//Here is the complete bill which is the finished/temporary bill contains hst,tip and totalAmount
public class CompleteBill {
    private double hst;
    private double tip;
    private double totalAmount;
    private int numberOfBills;

    public CompleteBill(double originalPrice, int numberOfBills) {
        tip = originalPrice*0.15;
        hst = (tip+originalPrice)*0.13;
        this.numberOfBills = numberOfBills;
        //update the income into 2 decimal
        String transfer = String.format("%.2f",tip + hst + originalPrice);
        totalAmount = Double.parseDouble(transfer);
    }

    public double getHst() {
        return hst;
    }

    public void setHst(double hst) {
        this.hst = hst;
    }

    public double getTip() {
        return tip;
    }

    public void setTip(double tip) {
        this.tip = tip;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getNumberOfBills() {
        return numberOfBills;
    }

    public void setNumberOfBills(int numberOfBills) {
        this.numberOfBills = numberOfBills;
    }
}
