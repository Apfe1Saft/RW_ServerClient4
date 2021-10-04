package Commands;

import collection.Collection;
import collection.loader.DifficultyExeption;
import java.io.Serializable;
import java.util.Scanner;
import data.*;
/*
Краткая информация:
На сервер передается несколько пакетов:
1. Команда remove_all_by_difficulty
2. Элемент Difficulty difficulty
 */
public class removeAllByDifficultyCommand implements Serializable {
    private LabWork labWork;

    public LabWork getLabWork() {
        return labWork;
    }

    public boolean clientjob(Scanner in) {
        try {
            Difficulty difficulty = new addCommand().StringtoEnum(in.next());
             this.labWork = new LabWork();
             this.labWork.setDifficulty(difficulty);
        }catch(DifficultyExeption e){
            return false;
        }
        return true;
    }
    public void serverjob(LabWork labWork,Collection collection ){
        collection.getVector().removeIf(a -> a.getDifficulty() == labWork.getDifficulty());
    }
}
//remove_all_by_difficulty VERY_EASY