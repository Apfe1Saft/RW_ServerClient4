package Commands;

import collection.Collection;
import java.io.Serializable;

/*
Краткая информация:
На сервер передается несколько пакетов:
1. Команда clear
 */
public class clearCommand implements Serializable {
    private static final long serialVersionUID = 32L;

    public void serverjob(Collection collection, sendCommand send) {
        try {
            collection.getVector().clear();
            collection.fileSaving();
        }catch (Exception e){
            send.setMessage("Не удалось отчистить файл, проверьте, доступен/существует ли он");
        }
    }
}
//clear
//+