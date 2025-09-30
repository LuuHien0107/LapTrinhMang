package TCP_CACDANG;

import java.io.* ;
import java.util.* ;
import java.lang.* ;
import java.net.* ;
public class TCPInOutputStream {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("203.162.10.109" , 2208) ;
        InputStream in = socket.getInputStream() ;
        OutputStream out = socket.getOutputStream() ;
        //gui ma
        String ma = "B22DCCN290;abc" ;
        out.write(ma.getBytes());
        out.flush();
        //nhan du lieu tu sv
        byte [] buffer = new byte[1024] ;
        int bytereads = in.read(buffer) ;
        String s = new String(buffer , 0 , bytereads) ;
        //gui thong tin
        out.write(s.getBytes()) ;
        out.flush();
        //dong ket noi
        in.close();
        out.close();
        socket.close();
    }
}
