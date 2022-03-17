package dao;

import domain.Dishes;
import domain.Seat;

import java.util.List;

/**
 * @Author ziangliang
 * @Date 2022-03-17
 * @Version 1.0
 */
public class DishesDao extends BasicDao<Dishes>{

    //function to display all the dishes using select SQL
    public List<Dishes> displaySeats(){
        String selectSql = "Select * From dishes";
        List<Dishes> dishes = multiSelect(selectSql,Dishes.class);
        return dishes;
    }
}
