package UDP;
import java.io.* ;
import java.math.BigInteger;
import java.net.* ;
import java.util.* ;
public class UDP_DataType3 {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket() ;
        InetAddress sA = InetAddress.getByName("203.162.10.109")  ;
        int sP = 2207 ;
        String code = ";B22DCCN290;D0VI66w5" ;
        DatagramPacket gui = new DatagramPacket(code.getBytes() , code.length() , sA , sP) ;
        socket.send(gui);

        System.out.println("DA GUI LEN SV " + code);

        byte [] buffer = new byte[1024]  ;
        DatagramPacket nhan = new DatagramPacket(buffer , buffer.length)  ;
        socket.receive(nhan);

        String s = new String(nhan.getData() , 0 , nhan.getLength()) ;
        System.out.println("Da nhan tu sv " + s);
        s = s.replace(";" , " ") ;
        s = s.replace("," , " ") ;

        String ss [] = s.trim().split("\\s+") ;
        String rq = ss[0] ;
        BigInteger a = new BigInteger(ss[1]) ;
        BigInteger b = new BigInteger(ss[2]) ;

        BigInteger tong = a.add(b) ;
        BigInteger hieu = a.subtract(b) ;
        String kq = rq + ";" + tong + "," + hieu ;
        DatagramPacket gui2 = new DatagramPacket(kq.getBytes() , kq.length() , sA , sP)  ;

        socket.send(gui2);

        System.out.println("Da gui len server " + kq);

        socket.close();


    }
}
