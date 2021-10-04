package Commands;

import collection.Collection;
import data.LabWork;
import functions.*;
import java.io.Serializable;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

/*
Краткая информация:
На сервер передается несколько пакетов:
1. Команда filter_by_difficulty
2. Элемент String name
 */
public class filterStartsWithNameCommand  implements byteToString, Serializable {
    private LabWork labWork;

    public LabWork getLabWork() {
        return labWork;
    }


    public boolean clientjob(Scanner in) {
        String name  = in.next();
        this.labWork = new LabWork();
        this.labWork.setName(name);
        return true;
    }
    public void serverjob(DatagramChannel s, SocketAddress a,LabWork labWork, Collection collection, sendCommand send){
        StringBuilder answer = new StringBuilder();
        int checker = 300;
        int i = 0;
        try {
            for (LabWork b : collection.getVector()){
                System.out.println(b.getId()+" \n");
                if (i == checker){
                    send.setMessage("!"+answer);
                    send.serverjob(s, a);
                    //System.out.println("Before: "+send.getMessage());
                    answer.delete(0,answer.length());
                    //System.out.println("After: "+send.getMessage());
                    checker += 300;
                }
                i++;
                if(b.getName().equals(labWork.getName())) {
                    answer.append("\n").append(b.toString());
                }
            }
            send.setMessage(answer+"}\n}" + "\nfileName = " + collection.getFileName() + ", VectorDate = " + collection.getVectorDate().toString() + ", MaxId = " + 0 + "}");

        }catch(Exception e){
            send.setMessage("Коллеция содержит слишком много LabWork, команда clear зачистила все ");
        }

        send.setMessage(answer.toString());
    }
}
//filter_starts_with_name Math