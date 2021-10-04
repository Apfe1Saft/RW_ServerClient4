package Commands;

import collection.Collection;
import data.LabWork;

import java.io.IOException;
import java.io.Serializable;
import java.util.Comparator;

/*
Краткая информация:
На сервер передается несколько пакетов:
1. Команда sort
 */
public class sortCommand implements Serializable {

    public void serverjob( Collection collection,sendCommand send) throws IOException {
        collection.getVector().sort(new MaximumPointComparator());
    }
}
class MaximumPointComparator  implements Comparator<LabWork> {
    public int compare(LabWork labWork1, LabWork labWork2){
        if(labWork1.getMaximumPoint().equals(labWork2.getMaximumPoint())) return 0;
        if(labWork1.getMaximumPoint() > labWork2.getMaximumPoint()) return 1;
        else  return -1;
    }
}