package fileOutPutStream;

import java.io.*;

public class File_Stream {
    public static void main(String[] args) {
        System.out.println("hello world");
        byte[] buf = new byte[10];
        for (int i=0; i<buf.length;i++){
            buf[i] = (byte)i;
        }
        try {
            DataOutputStream out = new DataOutputStream(
                    new BufferedOutputStream(
                    new FileOutputStream("b.dat")));
//            FileOutputStream out = new FileOutputStream("a.dat");
//            out.write(buf);
            int i = 0xcafebabe;
            out.writeInt(i);
            out.close();
            DataInputStream in = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream("b.dat")));
            int j = in.readInt();
            System.out.println(j);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
