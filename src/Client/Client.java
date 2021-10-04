package Client;


import collection.loader.DifficultyExeption;

import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Подключение к серверу...");
        try {
            SocketAddress a =
                    new InetSocketAddress(InetAddress.getByName("localhost"), 7689);
            DatagramChannel s =
                    DatagramChannel.open();
            s.configureBlocking(false);
            s.connect(a);
            int i = 0;
            String MESSAGE = "Подключиться к серверу не удалось\nПовторное подключение...";
            do {
                i++;
                if (new CommandReader().connectionChecker(s, a)) {
                    System.out.println("Подключение к серверу завершено");
                    String command ;// чтение первой строки и команды
                    //команду в поток байт
                    CommandReader comrade = new CommandReader();
                    boolean flag = true;
                    do{
                        System.out.println("->");
                        command = in.next();
                        comrade.work(command, s, a, in, 0);
                        if(command.equals("exit"))flag = false;
                        if(!comrade.isFlag())flag = false;
                    }while (flag);
                    comrade.work("exit", s, a, in, 0);
                    System.out.println("Отключение от сервера...");
                    ByteBuffer mess = ByteBuffer.wrap("0".getBytes());
                    s.send(ByteBuffer.wrap(mess.array()),a);
                    i = 20;

                } else {
                    System.out.println(MESSAGE);
                    Thread.sleep(1000);
                    MESSAGE = "...";
                }

            }while(i<10);
            if (i==10)System.out.println("Подключение к серверу не удалось");
            else System.out.println("Отключение от сервера завершено");
        } catch (SocketException | InterruptedException e) {
            // если не получилось создать сокет
            System.out.println("Сервер недоступен");
        } catch (IOException e) {
            // ошибка при приеме
            System.out.println("IO: " + e.getMessage());
        } catch (DifficultyExeption difficultyExeption) {
            difficultyExeption.printStackTrace();
        }
    }

}
