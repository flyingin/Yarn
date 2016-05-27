package Socket;

/**
 * Created by yml on 16-5-16.
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {

    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("172.18.129.201", 15008);
            //向服务器端第一次发送字符串
            OutputStream netOut = socket.getOutputStream();
            DataOutputStream doc = new DataOutputStream(netOut);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            //向服务器端第二次发送字符串
            doc.writeUTF("list");
            String res = in.readUTF();
            System.out.println(res);
            doc.writeUTF("bye");
            res = in.readUTF();
            System.out.println(res);
            doc.close();
            in.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}