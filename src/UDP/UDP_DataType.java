package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List ;

public class UDP_DataType {
    public static void main(String[] args) throws  Exception{
        DatagramSocket socket = new DatagramSocket() ;
        InetAddress sA = InetAddress.getByName("203.162.10.109") ;
        int sP = 2207 ;
        String code = ";B22DCCN290;TpUJqmZT"  ;
        DatagramPacket gui = new DatagramPacket(code.getBytes() , code.length() , sA , sP) ;
        socket.send(gui);

        byte[] buffer = new byte[1024] ;
        DatagramPacket nhan = new DatagramPacket(buffer , buffer.length) ;

        socket.receive(nhan);
        String s = new String(nhan.getData() , 0 , nhan.getLength()) ;

        System.out.println("Da nhan duoc chuoi" + s);
        s = s.replace(";" , " ")  ;
        s = s.replace("," , " ") ;
        String ss[] = s.split("\\s+") ;
        String rq = ss[0] ;
        ArrayList<Integer> arr = new ArrayList<>() ;
        for(int i = 1 ; i < ss.length ; i++) {
            arr.add(Integer.parseInt(ss[i])) ;
        }
        arr.sort((a , b) -> a.compareTo(b));
        rq += ";" + arr.get(arr.size() - 1) + "," + arr.get(0) ;
        DatagramPacket guilai = new DatagramPacket(rq.getBytes() , rq.length() , sA , sP) ;
        socket.send(guilai);

        System.out.println("Da gui len server " + rq);
        socket.close();

   }
}
