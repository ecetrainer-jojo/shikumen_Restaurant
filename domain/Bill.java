package domain;

/**
 * @Author ziangliang
 * @Date 2022-03-17
 * @Version 1.0
 */
public class Bill {
    public String dishName;
    public Integer amount;
    public double price;

    public Bill() {
    }

    public Bill(String dishName, Integer amount, double price) {
        this.dishName = dishName;
        this.amount = amount;
        this.price = price;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
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

    @Override
    public String toString() {
        return  (String.format("%-20s", amount) +
                "\t\t"+String.format("%-30s", dishName)+
                "\t\t"+String.format("%-20s", price)
        );
    }
}
