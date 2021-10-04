package Commands;

import collection.Collection;
import data.Coordinates;
import data.Difficulty;
import data.Discipline;
import data.LabWork;

import java.io.Serializable;
import java.util.Scanner;

/*
Краткая информация:
На сервер передается несколько пакетов:
1. Команда remove_by_id
2. Элемент String id
 */
public class removeByIdCommand implements Serializable {
    private LabWork labWork;

    public LabWork getLabWork() {
        return labWork;
    }
    public boolean clientjob(Scanner in)  {
        try {
            long D;
            String line = in.next();
            D = Long.parseLong(line);
            labWork = new LabWork("", D, new Coordinates(0.0,0), 0L, 0, Difficulty.VERY_EASY, new Discipline("",0L));
             return true;
        }catch (Exception e){
            System.out.println("Ошибка при вводе значения - не является Long " );
            return false;
        }

    }
    public void serverjob(LabWork labWork, Collection collection){
        collection.getVector().removeIf(a -> a.getId().equals(labWork.getId()));
    }
}
//remove_by_id