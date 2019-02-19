package fileOutPutStream;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class SocketStream {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket(InetAddress.getByName("localhost"),12345);
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    socket.getOutputStream())));
            out.println("Hello");
//            若不刷新缓存，服务端不会显示 hello
            out.flush();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));

            String line;
            while ((line=reader.readLine())!=null) {
                System.out.println(line);
            }
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
