package Commands;

import collection.Collection;
import data.LabWork;

import java.io.Serializable;

/*
Краткая информация:
На сервер передается несколько пакетов:
1. Команда remove_first
 */
public class removeFirstCommand implements Serializable {
    private LabWork labWork;


   public void serverjob(LabWork labWork, Collection collection){
       collection.getVector().removeElementAt(0);
   }

}
// remove_first
