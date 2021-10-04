package Commands;

import collection.Collection;
import data.LabWork;
import functions.*;
import java.io.Serializable;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

/*
На сервер передается несколКраткая информация:
ько пакетов:
1. Команда show
 */
public class showCommand implements byteToString , Serializable {

    public void serverjob(DatagramChannel s, SocketAddress a,Collection collection,sendCommand send) {
        Long Max = 0L;
        for(LabWork c: collection.getVector()){
            if(c.getId()>Max)Max = c.getId();
        }
        StringBuilder answer = new StringBuilder("VectorDate:" + collection.getVectorDate() + "\nCollection{\nvector{" + collection.getVector().toString().replace("[", "").replace("]", "") + "}\n}" +
                "\nfileName = " + collection.getFileName() + ", VectorDate = " + collection.getVectorDate().toString() + ", MaxId = " + Max + "}");

        if(Max>299){
            System.out.println(Max);
            answer = new StringBuilder("VectorDate:" + collection.getVectorDate() + "\nCollection{\nvector{");
            int checker = 300;
            try {
                for (LabWork b : collection.getVector()){
                    System.out.println(b.getId()+" "+b.toString());
                    if (b.getId() == checker){
                        send.setMessage("!"+answer);
                        send.serverjob(s, a);
                        //System.out.println("Before: "+send.getMessage());
                        answer.delete(0,answer.length());
                        //System.out.println("After: "+send.getMessage());
                        checker += 300;
                    }
                    answer.append("\n").append(b.toString());
                }
                send.setMessage(answer+"}\n}" + "\nfileName = " + collection.getFileName() + ", VectorDate = " + collection.getVectorDate().toString() + ", MaxId = " + 0 + "}");

            }catch(Exception e){
                send.setMessage("Коллеция содержит слишком много LabWork, команда clear зачистила все ");
            }

        }
        else {
            send.setMessage(answer.toString());
        }
    }
}