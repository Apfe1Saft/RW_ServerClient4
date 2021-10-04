package Server;

import collection.Collection;
import collection.loader.DifficultyExeption;
import Commands.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import functions.*;

public class  CommandWorker implements byteToString{
    private  boolean Flag ;
    public void comWork(ByteBuffer mess , DatagramChannel s, SocketAddress a, Collection collection) throws IOException, DifficultyExeption {
        this.Flag = true;
        try(ByteArrayInputStream bais = new ByteArrayInputStream(mess.array(), 0, mess.position());
            ObjectInputStream ois = new ObjectInputStream(bais)) {

            Acommand acommand = (Acommand) ois.readObject();
            run(s,a,acommand,collection);
        } catch(IOException | ClassNotFoundException ignored) {}

    }

    public boolean isFlag() {
        return Flag;
    }

    public void run(DatagramChannel s, SocketAddress a, Acommand acommand, Collection collection) throws IOException {
            String command =acommand.getComname();
            sendCommand send = new sendCommand();
            if (command.startsWith("help")) {new helpCommand().serverjob(send);}
        if (command.startsWith("exit")) {this.Flag = false;}
            if (command.startsWith("show")){new showCommand().serverjob(s,a,collection,send);}
            if (command.startsWith("sort")){new sortCommand().serverjob(collection,send);}
            if (command.startsWith("info")){new infoCommand().serverjob(collection,send);}
            if (command.startsWith("add") & command.length()<="add".length()){new addCommand().serverjob(acommand.getLabWork(),collection);}
            if (command.startsWith("filter_by_difficulty")){new filterByDifficultyCommand().serverjob(s,a,acommand.getLabWork(), collection,send);}
            if (command.startsWith("clear")){new clearCommand().serverjob(collection,send);}
            if (command.startsWith("filter_starts_with_name")){new filterStartsWithNameCommand().serverjob(s,a,acommand.getLabWork(), collection,send);}
            if (command.startsWith("remove_all_by_difficulty")){new removeAllByDifficultyCommand().serverjob(acommand.getLabWork(), collection);}
            if (command.startsWith("remove_by_id")){new removeByIdCommand().serverjob(acommand.getLabWork(),collection);}
            if (command.startsWith("remove_first")){new removeFirstCommand().serverjob(acommand.getLabWork(), collection);}
            if (command.startsWith("update")){new updateIdCommand().serverjob(acommand.getLabWork(),collection);}
            if (command.startsWith("add_if_max") ){new addIfMaxCommand().serverjob(acommand.getLabWork(),collection);}
            send.serverjob(s,a);
            collection.fileSaving();
    }
    public boolean connectionChecker(DatagramChannel s, SocketAddress a,byte[] buffer) {
        try {
            this.Flag = true;
            // резервируем дейтаграмму под пакет клиента
            ByteBuffer mess = ByteBuffer.wrap(buffer);
            // принимаем пакет клиента
            s.receive(mess);
            //System.out.println(new String(mess.array()));
            String line = working(mess.array());
            if (!line.equals("1"))return false;
            byte[] message = line.getBytes();
            mess.clear();
            mess = ByteBuffer.wrap(message);
            s.send(mess,a);
            System.out.println("Связь с клиентом установлена");
            return true;
        }catch (Exception e){
            return false;
        }
    }
}

