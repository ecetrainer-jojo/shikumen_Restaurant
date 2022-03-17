package domain;

/**
 * @Author ziangliang
 * @Date 2022-03-17
 * @Version 1.0
 */
public class Bill {
    public Integer dishID;
    public Integer amount;
    public double price;

    public Bill() {
    }

    public Bill(Integer dishID, Integer amount, double price) {
        this.dishID = dishID;
        this.amount = amount;
        this.price = price;
    }

    public Integer getDishID() {
        return dishID;
    }

    public void setDishID(Integer dishID) {
        this.dishID = dishID;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
