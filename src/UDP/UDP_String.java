package UDP;
import java.io.* ;
import java.util.* ;
import java.net.* ;
public class UDP_String {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket() ;
        InetAddress sA = InetAddress.getByName("203.162.10.109") ;
        int sP = 2208 ;
        String code = ";B22DCCN290;1sYue4TQ" ;
        DatagramPacket gui = new DatagramPacket(code.getBytes() ,code.length() , sA , sP )  ;

        socket.send(gui);
        System.out.println("DA GUI CHUOI " + code);

        byte [] buffer = new byte[1024]  ;
        DatagramPacket nhan = new DatagramPacket(buffer , buffer.length)  ;

        socket.receive(nhan);

        String s = new String(nhan.getData()) ;

        System.out.println("Da nhan chuoi " + s);

        s = s.replace(";" , " ") ;
        String ss []  = s.trim().split("\\s+")  ;
        String requestID = ss[0]  ;
        String kq = requestID + ";" ;
        for(int i = 1 ; i < ss.length ; i++) {
            ss[i] = ss[i].substring(0,1).toUpperCase() + ss[i].substring(1).toLowerCase()  ;
            kq += ss[i] + " "  ;
        }
        kq = kq.substring(0,kq.length() - 1) ;
        DatagramPacket gui2 = new DatagramPacket(kq.getBytes() ,kq.length() , sA , sP)  ;

        socket.send(gui2);

        System.out.println("Da gui chuoi " + kq );

     }
}
