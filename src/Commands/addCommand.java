package Commands;

import java.io.Serializable;
import java.util.Scanner;

import collection.Collection;
import data.*;
import collection.loader.*;
/*
Краткая информация:
На сервер передается несколько пакетов:
1. Команда add
2. - 8. Элементы команды add
 */
public class addCommand  implements Serializable {

    private LabWork labWork;

    public LabWork getLabWork() {
        return labWork;
    }

    public boolean clientjob(Scanner in) {
        String D0 = "";
        double D1 = 0.0;
        int D2 = 0;
        long D3 = 0;
        int D4 = 0;
        String D5 = "";
        String D6 = null;
        long D7 = 0L;
        class Error {
            private String Message;

            public String getMessage() {
                return Message;
            }

            public void setMessage(String message) {
                Message = message;
            }
        }
        Error A = new Error();
        try {

            for (int i = 0; i < 8; i++) {
                String command = in.next();
                switch (i) {
                    case 0: {
                        A.setMessage("Что это такое : " + command);
                        D0 = command;
                    }
                    break;
                    case 1: {
                        A.setMessage("Ошибка при вводе значения - не является double: " + command);
                        D1 = Double.parseDouble(command);

                    }
                    break;
                    case 2: {
                        A.setMessage("Ошибка при вводе значения  - не является int: " + command);
                        D2 = Integer.parseInt(command);
                    }
                    break;
                    case 3: {
                        A.setMessage("Ошибка при вводе значения  - не является long: " + command);
                        D3 = Long.parseLong(command);
                    }
                    break;
                    case 4: {
                        A.setMessage("Ошибка при вводе значения - не является int: " + command );
                        D4 = Integer.parseInt(command);
                    }
                    break;
                    case 5: {
                        D5 = command;
                    }
                    break;
                    case 6: {
                        A.setMessage("Что это такое : " + command);
                        D6 = command;
                    }
                    break;
                    case 7: {
                        A.setMessage("Ошибка при вводе значения -  не является long: " + command);
                        D7 = Long.parseLong(command);
                    }
                    break;
                    default:
                        //До этого не дойдет
                }
            }
            //D0 = "Math";
            A.setMessage("ВВеденное значение difficulty не является Difficulty: " + D5);
            Discipline discipline = new Discipline(D6, D7);
            Difficulty difficulty = StringtoEnum(D5);
            Coordinates coordinates = new Coordinates(D1, D2);
            this.labWork = new LabWork(D0, 0L, coordinates, D3, D4, difficulty, discipline);
            return true;
        } catch (Exception E) {
            System.out.println(A.getMessage());
        }
        return false;
    }

    public void serverjob(LabWork labwork, Collection collection) {
        Long MAX = 0L;
        for(LabWork a: collection.getVector()){
            if(a.getId()>MAX)MAX = a.getId();
        }
        labwork.setId(MAX+1);
        collection.getVector().add(labwork);
    }

    public Difficulty StringtoEnum(String line) throws DifficultyExeption {
        Difficulty difficulty;
        if (line.startsWith("VERY_EASY") && line.length() <= "VERY_EASY".length()) difficulty = Difficulty.VERY_EASY;
        else if (line.startsWith("NORMAL")) difficulty = Difficulty.NORMAL;
        else if (line.startsWith("TERRIBLE")) difficulty = Difficulty.TERRIBLE;
        else if (line.startsWith("IMPOSSIBLE")) difficulty = Difficulty.IMPOSSIBLE;
        else if (line.startsWith("INSANE")) difficulty = Difficulty.INSANE;
        else throw new DifficultyExeption();
        return difficulty;
    }
}

//add Math 0.0 0 0 0 VERY_EASY MATH 8
//+