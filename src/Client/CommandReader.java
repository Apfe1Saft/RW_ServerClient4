package Client;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;
import Commands.*;
import SerializedThings.*;
import collection.loader.DifficultyExeption;

public class CommandReader {
    public CommandReader(){}
    private boolean Flag;

    public boolean isFlag() {
        return Flag;
    }

    public void work(String command, DatagramChannel s, SocketAddress a, Scanner in, int way ) throws DifficultyExeption {
        sendCommand send = new sendCommand();
        switch (command){

            case"exit":{ new SerializedCommand(new Acommand("exit"),s,a);}break;
            case"help":{new SerializedCommand(new Acommand("help"),s,a);send.clientjob(s,a);}break;
            case"show":{new SerializedCommand(new Acommand("show"),s,a);send.clientjob(s,a);}break;
            case"info":{new SerializedCommand(new Acommand("info"),s,a);send.clientjob(s,a);}break;
            case"clear":{new SerializedCommand(new Acommand("clear"),s,a);send.clientjob(s,a);}break;
            case"sort":{new SerializedCommand(new Acommand("sort"),s,a);send.clientjob(s,a);}break;
            case"add":{
                addCommand A = new addCommand();
                if(A.clientjob(in)){
                    new SerializedCommand(new Acommand("add",A.getLabWork()),s,a);send.clientjob(s,a);
                }
            }break;
            case"remove_by_id":{
                removeByIdCommand A = new removeByIdCommand();
                if(A.clientjob(in)){
                    new SerializedCommand(new Acommand("remove_by_id",A.getLabWork()),s,a);send.clientjob(s,a);
                }
            }break;
            case"execute_script":{new executeScriptCommand(s,a,in,way);}break;
            case"remove_first":{new SerializedCommand(new Acommand("remove_first"),s,a);send.clientjob(s,a);}break;
            case"update":{
                updateIdCommand A = new updateIdCommand();
                if(A.clientjob(in)){
                    new SerializedCommand(new Acommand("update",A.getLabWork()),s,a);send.clientjob(s,a);
                }
            }break;
            case"add_if_max":{
                addIfMaxCommand A = new addIfMaxCommand();
                if(A.clientjob(in)){
                    new SerializedCommand(new Acommand("add_if_max",A.getLabWork()),s,a);send.clientjob(s,a);
                }
            }break;
            case"remove_all_by_difficulty":{
                removeAllByDifficultyCommand A = new removeAllByDifficultyCommand();
                if(A.clientjob(in)){
                    new SerializedCommand(new Acommand("remove_all_by_difficulty",A.getLabWork()),s,a);send.clientjob(s,a);
                }
            }break;
            case"filter_by_difficulty":{
                filterByDifficultyCommand A = new filterByDifficultyCommand();
                if(A.clientjob(in)){
                    new SerializedCommand(new Acommand("filter_by_difficulty",A.getLabWork()),s,a);send.clientjob(s,a);
                }
            }break;
            case"filter_starts_with_name":{
                filterStartsWithNameCommand A = new filterStartsWithNameCommand();
                if(A.clientjob(in)){
                    new SerializedCommand(new Acommand("filter_starts_with_name",A.getLabWork()),s,a);send.clientjob(s,a);
                }
            }break;
            default:{ System.out.println("Такой команды не существует, обратитесь к команде help для вывода справки по командам");}break;
        }
        this.Flag = send.isFlag();
    }
    public boolean connectionChecker(DatagramChannel s, SocketAddress a){
        try {
            byte[] message = "1".getBytes();
            ByteBuffer mess = ByteBuffer.wrap(message);
            s.send(mess,a);
            mess.clear();
            s.receive(mess);
            String kString = new String(mess.array());
            return kString.startsWith("1");
        }catch (Exception e){
            return false;
        }
    }

}