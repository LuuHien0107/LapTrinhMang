package TCP_CACDANG;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPCuPhapFull {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("203.162.10.109" , 2209) ;
        InputStream ip = socket.getInputStream() ;
        OutputStream op = socket.getOutputStream() ;
        String ma = "B22DCCN290" ;
        op.write(ma.getBytes()) ;
        op.flush();

        byte [] buffer = new byte[1024] ;
        int bytereads = ip.read(buffer) ;
        String s = new String(buffer , 0 , bytereads) ;
    }
}
