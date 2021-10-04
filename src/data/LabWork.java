package data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * {@param class of which vector consist
 */
public class LabWork implements Serializable {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long minimalPoint; //Поле не может быть null, Значение поля должно быть больше 0
    private Integer maximumPoint; //Поле не может быть null, Значение поля должно быть больше 0
    private Difficulty difficulty; //Поле не может быть null
    private Discipline discipline; //Поле может быть null

    public LabWork(Long id , String name
             , Coordinates coordinates , LocalDate creationDate,
                   Long minimalPoint , Integer maximumPoint, Difficulty difficulty , Discipline discipline){
        this.id = id;
        this.name =name;
        this.coordinates = coordinates;
        this.creationDate =creationDate;
        this.minimalPoint  =minimalPoint;
        this.maximumPoint = maximumPoint;
        this.difficulty = difficulty;
        this.discipline  =discipline;
     }
    public LabWork(String name ,Long id ,  Coordinates coordinates ,
                   Long minimalPoint , Integer maximumPoint, Difficulty difficulty , Discipline discipline){
        this.id = id;
        this.name =name;
        this.coordinates = coordinates;
        this.creationDate =LocalDate.now();
        this.minimalPoint  =minimalPoint;
        this.maximumPoint = maximumPoint;
        this.difficulty = difficulty;
        this.discipline  =discipline;
    }

    public LabWork(Long time, String mass0, Coordinates coordinates, Object mass3, Object mass4, Difficulty difficulty, Discipline discipline) {
        this.id = time;
        this.name = mass0;
        this.coordinates = coordinates;
        this.creationDate = (LocalDate.now());
        this.minimalPoint = (long)mass3;
        this.maximumPoint = (Integer) mass4;
        this.difficulty = difficulty;
        this.discipline = discipline;

    }
    public LabWork(LabWork labWork, Long id) {
        this.id = id;
        this.name = labWork.getName();
        this.coordinates = labWork.getCoordinates();
        this.discipline = labWork.getDiscipline();
        this.difficulty = labWork.getDifficulty();
        this.maximumPoint = labWork.maximumPoint;
        this.minimalPoint = labWork.minimalPoint;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public void setMaximumPoint(Integer maximumPoint) {
        this.maximumPoint = maximumPoint;
    }

    public void setMinimalPoint(Long minimalPoint) {
        this.minimalPoint = minimalPoint;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
     public String toString() {
         return "LabWork{" +
                 "id = " + id +
                 ", name = " + name +
                 ", coordinates = " + coordinates +
                 ", creationDate = " + creationDate +
                 ", minimalPoint = " + minimalPoint +
                 ", maximumPoint = " + maximumPoint +
                 ", difficulty = " + difficulty +
                 ", discipline = " + discipline.toString() +
                 '}';
     }

     public LabWork(){
         this.name = "";
         this.coordinates = new Coordinates(0.0,0);
         this.discipline = new Discipline("",0L);
         this.difficulty = Difficulty.VERY_EASY;
         this.maximumPoint = 0;
         this.minimalPoint = 0L;
     }
     public Long getId(){return id;}
     public String getName(){return name;}

     public Coordinates getCoordinates() {
        return coordinates;
    }

    public Long getMinimalPoint() {
        return minimalPoint;
    }

    public Integer getMaximumPoint() {
        return maximumPoint;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
