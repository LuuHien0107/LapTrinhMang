package UDP;
import java.io.* ;
import java.util.* ;
import java.net.* ;
public class UDP_String {
    public static void main(String[] args) throws Exception{
       DatagramSocket socket = new DatagramSocket() ;
       InetAddress sA = InetAddress.getByName("203.162.10.109") ;
       int sP = 2208 ;
       String ma = ";B22DCCN290;1sYue4TQ" ;
       DatagramPacket gui = new DatagramPacket(ma.getBytes() , ma.length() , sA , sP) ;
       socket.send(gui);
        System.out.println("Da gui ma " + ma);

        byte [] buffer = new byte[1024] ;
        DatagramPacket nhan = new DatagramPacket(buffer , buffer.length) ;
        socket.receive(nhan);

        String s = new String(nhan.getData() , 0 , nhan.getLength()) ;
        System.out.println("Da nhan chuoi " + s);
        s = s.replace(";" , " ") ;
        String ss[] = s.split("\\s+") ;
        String rq = ss[0] ;
        String data = "" ;
        for(int i = 1 ; i < ss.length ; i ++) {
            data += ss[i].substring(0,1).toUpperCase() + ss[i].substring(1).toLowerCase() ;
            if ( i != ss.length - 1) data += " " ;
        }
        String kq = rq + ";" +  data ;
        DatagramPacket guilai = new DatagramPacket(kq.getBytes() , kq.length() , sA , sP) ;
        socket.send(guilai);
        System.out.println("Da gui lai chuoi " + kq);
        socket.close();

     }
}
