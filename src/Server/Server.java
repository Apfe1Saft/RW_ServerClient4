package Server;

import collection.Collection;
import collection.loader.DifficultyExeption;

import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import functions.*;

public class Server implements byteToString{


    public static void main(String[] args)  {
        try{

            SocketAddress a =
                    new InetSocketAddress(InetAddress.getByName("localhost"), 7689);
            DatagramChannel s =
                    DatagramChannel.open();
            s.bind(a);
                CommandWorker comwor = new CommandWorker();
                Collection collection = new Collection("data.txt");
                collection.fileSaving();
                while (true) {
                    System.out.println("Связь с клиентом устанавливается...");
                    if (comwor.connectionChecker(s, a, new byte[100])) {
                        do {
                            ByteBuffer mess = ByteBuffer.wrap(new byte[1000*1000]);
                            a = s.receive(mess);
                            if(mess.array().length>2){
                                comwor.comWork(mess, s, a, collection);
                            }
                        } while (comwor.isFlag());
                        System.out.println("Отключение от клиента завершено\n");
                    }
                }
        }
        catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        }
        catch (IOException | DifficultyExeption e) {
            System.out.println("IO: " + e.getMessage());
        }
    }

}