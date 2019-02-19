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

//            Reader/Writer
            PrintWriter out_p = new PrintWriter(
                    new BufferedWriter(
//                            OutputStreamWriter 构建了 Stream 和 Writer 之间的桥梁
                            new OutputStreamWriter(
                                    new FileOutputStream("a.txt"))));
            int k = 123456;
//            文本流中的 print 表示打印到文件中？
            out_p.print(k);
            out_p.close();
//            读文件
            BufferedReader in_r = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream("src/../a.txt")));
            String line;
            while ((line=in_r.readLine()) != null){
                System.out.println(line);
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
