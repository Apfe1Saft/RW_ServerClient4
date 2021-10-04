package SerializedThings;

import Commands.*;
import data.LabWork;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class SerializedCommand {
    public SerializedCommand(Acommand command, DatagramChannel s, SocketAddress a){
        convert(command,s, a);
    }
    public void convert(Acommand acommand,DatagramChannel s, SocketAddress a) {
        try {
            ByteArrayOutputStream one = new ByteArrayOutputStream();
            ObjectOutputStream fos = new ObjectOutputStream(one);
            fos.writeObject(acommand);
            byte[] data = one.toByteArray();
            s.send(ByteBuffer.wrap(data), a);
        } catch (IOException e) {
            System.out.println("ERROR");
            //  }
        }
    }
}
