package Commands;

import Client.CommandReader;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
/*
Краткая информация:
На сервер передается несколько пакетов:
1. Команда execute_script
2. Имя файла со скриптом
 */
public class executeScriptCommand {
    public executeScriptCommand(DatagramChannel s, SocketAddress a, Scanner in , int way) {
        String message = "Указанный файл не найден или недоступен, укажите полный путь";
        try {
            String file = in.next();
            Path path = Paths.get(file);
            Scanner scanner = new Scanner(path);
            String command = scanner.next();
            CommandReader comrade = new CommandReader();
            while (!command.equals("exit") | scanner.hasNext()) {
                comrade.work(command, s , a , scanner, way + 1);
                if (way > 50) {
                    break;
                }
                command = scanner.next();
            }

        }catch (Exception e){
            System.out.println(message);
        }
    }
}
// execute_script C:\\users\\foraa\\Desktop\\PROGRAMS\\ServerClient6\\code.TXT