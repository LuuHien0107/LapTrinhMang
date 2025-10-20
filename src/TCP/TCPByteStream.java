package TCP;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class TCPByteStream {
    public static void main(String[] args) throws  Exception{
        Socket socket = new Socket("203.162.10.109" , 2206) ;
        InputStream in = socket.getInputStream() ;
        OutputStream out = socket.getOutputStream() ;

        String ma = "B22DCCN290;XOyuJjHa" ;
        out.write(ma.getBytes()) ;
        out.flush();
        System.out.println("Da gui di chuoi " + ma);

        byte [] buffer = new byte[1024]  ;
        int bytereads = in.read(buffer) ;
        String s = new String(buffer , 0 , bytereads) ;

        System.out.println("Da nhan duoc chuoi " + s);

        s = s.replace("|" , " ") ;
        String ss[] = s.trim().split("\\s+") ;
        ArrayList <Integer> arr = new ArrayList<>() ;
        int tong = 0 ;
        for(String x : ss) {
            tong += Integer.parseInt(x) ;
        }

        String kq = Integer.toString(tong) ;
        out.write(kq.getBytes()) ;
        out.flush();
        System.out.println("Da gui len server " + kq);

        in.close();
        out.close();
        socket.close();
    }
}
