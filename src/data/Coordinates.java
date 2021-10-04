package data;

import java.io.Serializable;

/**
 * {@param class which is a part of LabWork class
 */
public class Coordinates implements Serializable {
    private Double x ; //Поле не может быть null
    private Integer y ; //Значение поля должно быть больше -957, Поле не может быть null
    public Coordinates(Double x, Integer y){
        this.x = x;
        this.y = y;
    }
    public Double getX(){return this.x;}
    public Integer getY(){return this.y;}
    public void setX(Double x){this.x = x;}
    public void setY(Integer y){this.y =y;}

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}