package domain;

/**
 * @Author ziangliang
 * @Date 2022-03-17
 * @Version 1.0
 */
public class Dishes {
    public Integer dishID;
    public String dishName;
    public String dishType;
    public double price;
    public String Mandarin_Name;

    //default constructor for reflection
    public Dishes(){

    }

    public Dishes(Integer dishID, String dishName, String dishType, double price, String mandarin_Name) {
        this.dishID = dishID;
        this.dishName = dishName;
        this.dishType = dishType;
        this.price = price;
        Mandarin_Name = mandarin_Name;
    }

    public String getMandarin_Name() {
        return Mandarin_Name;
    }

    public void setMandarin_Name(String mandarin_Name) {
        Mandarin_Name = mandarin_Name;
    }

    public Integer getDishID() {
        return dishID;
    }

    public void setDishID(Integer dishID) {
        this.dishID = dishID;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDishType() {
        return dishType;
    }

    public void setDishType(String dishType) {
        this.dishType = dishType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return  String.format("%-10s", dishID) +
                String.format("%-40s", dishName)+
                String.format("%-15s", dishType)+
                String.format("%-2f", price)+
                "\t\t"+Mandarin_Name;

    }
}
