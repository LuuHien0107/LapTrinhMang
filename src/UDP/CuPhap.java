package UDP;
import java.io.* ;
import java.net.* ;
import java.util.* ;
public class CuPhap {
    public static void main(String[] args) throws Exception{

        // gui
        DatagramSocket socket = new DatagramSocket() ;
        InetAddress sA = InetAddress.getByName("203.162.10.109") ;
        int sP = 2207 ;

        String code = "B22DCCN290"  ;
        DatagramPacket gui1 = new DatagramPacket(code.getBytes() , code.length() , sA , sP) ;
        socket.send(gui1);

        // nhan

        byte [] buffer = new byte[1024] ;
        DatagramPacket dpNhan = new DatagramPacket(buffer , buffer.length ) ;
        socket.receive(dpNhan);

        String s = new String(dpNhan.getData())  ;


    }
}
