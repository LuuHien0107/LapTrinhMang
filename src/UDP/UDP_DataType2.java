package UDP;
import java.io.* ;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.* ;
import java.util.* ;
public class UDP_DataType2 {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket() ;
        InetAddress sA = InetAddress.getByName("203.162.10.109")  ;
        int sP = 2207 ;
        String code = ";B22DCCN290;Ta7LtfYP" ;

        DatagramPacket gui = new DatagramPacket(code.getBytes() , code.length() , sA , sP)  ;
        socket.send(gui);

        System.out.println("Da gui len server " + code );


        byte [] buffer = new byte[1024] ;

        DatagramPacket nhan = new DatagramPacket(buffer , buffer.length)  ;

        socket.receive(nhan);


        String s = new String(nhan.getData()) ;
        System.out.println("Da nhan tu server " + s );

        s = s.replace(";" , " ") ;
        s = s.replace("," , " ") ;
        String ss [] = s.trim().split("\\s+") ;

        String requestID = ss[0] ;

        List <Integer> list = new ArrayList<>() ;
        for(int i = 1 ; i < ss.length ; i++) {
            list.add(Integer.parseInt(ss[i]))  ;
        }

        list.sort(Comparator.comparingInt(Integer::intValue));

        int secondMax = list.get(list.size() - 2);
        int secondMin = list.get(1)  ;

        String kq = requestID + ";" + secondMax + "," + secondMin  ;
        DatagramPacket gui2 = new DatagramPacket(kq.getBytes() , kq.length() , sA , sP)  ;
        socket.send(gui2);

        System.out.println("Da gui len server " + kq) ;
    }
}
