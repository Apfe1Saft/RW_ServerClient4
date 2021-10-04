package Commands;

import collection.Collection;
import functions.*;
import java.io.Serializable;

/*
Краткая информация:
На сервер передается несколько пакетов:
1. Команда info
 */
public class infoCommand implements byteToString, Serializable {

    public void serverjob ( Collection collection,sendCommand send) {
        String line = "Type : Vector; \n"+"    Initialization date : "+collection.getVectorDate()+"\n"+
                "Elements :"+ collection.getVector().size()+"\n"+
                "Type: LabWork;\n"+
                "LabWork's data:" +
                "    private Long id; \n" +
                "    private String name; \n" +
                "    private Coordinates coordinates; \n" +
                "    private java.time.LocalDate creationDate; \n" +
                "    private Long minimalPoint; \n" +
                "    private Integer maximumPoint; \n" +
                "    private Difficulty difficulty; \n" +
                "    private Discipline discipline; \n" +
                "LabWork's Methods :"+
                "     toString();\n" +
                "     LabWork();\n" +
                "     getId();\n" +
                "     getName();\n" +
                "     getCoordinates();\n" +
                "     getMinimalPoint(); \n" +
                "     getMaximumPoint(); \n" +
                "     getCreationDate();\n" +
                "     getDifficulty(); \n" +
                "     getDiscipline(); \n"+
                "Type: Difficulty;\n"+
                "     VERY_EASY;\n"+
                "     NORMAL;\n"+
                "     IMPOSSIBLE;\n"+
                "     INSANE;\n"+
                "     TERRIBLE;";
        send.setMessage(line);
    }
}
