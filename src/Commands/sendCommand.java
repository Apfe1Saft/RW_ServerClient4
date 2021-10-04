package Commands;

import SerializedThings.SerializedCommand;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import functions.*;

public class sendCommand implements byteToString{
    private boolean flag ;
    private String message = "false";

    public boolean isFlag() {
        return flag;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public sendCommand() {
        flag = true;
    }

    public void serverjob(DatagramChannel s, SocketAddress a) throws IOException {
        new SerializedCommand(new Acommand(message), s, a);
    }

    public void clientjob(DatagramChannel s, SocketAddress a) {
        String line;
        try {
            ByteBuffer mess = ByteBuffer.wrap(new byte[1000 * 1000]);
            do {s.receive(mess);
            line = working(mess.array());
            }while(line.length()<1);

                try (ByteArrayInputStream bais = new ByteArrayInputStream(mess.array(), 0, mess.position());
                     ObjectInputStream ois = new ObjectInputStream(bais)) {

                    Acommand acommand = (Acommand) ois.readObject();
                    if (!acommand.getComname().equals(message)) {
                        if(acommand.getComname().startsWith("!")){
                            System.out.println(acommand.getComname().replaceFirst("!",""));
                            clientjob(s,a);
                        }
                        else
                        System.out.println(acommand.getComname());
                    }
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("мимо");
                }


        }catch (Exception e){
            System.out.println("Не удалось получить сообщение от сервера");
            flag = false;
        }
    }
}
