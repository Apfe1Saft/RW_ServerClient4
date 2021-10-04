package data;

import java.io.Serializable;

/**
 * {@param enum which is a part of LabWork class
 */
public class Discipline implements Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long practiceHours; //Поле не может быть null
    public Discipline(String name, Long practiceHours){
        this.name = name;
        this.practiceHours = practiceHours;
    }
    public Discipline(){}
    public void setName(String name){this.name =name;}
    public void setPracticeHours(Long practiceHours){this.practiceHours = practiceHours; }
    public String getName(){return name;}
    public Long getPracticeHours(){return practiceHours;}

    @Override
    public String toString() {
        return "Discipline{" +
                "name = " + name +
                ", practiceHours = " + practiceHours +
                '}';
    }
}