package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List ;

public class UDP_DataType {
    public static void main(String[] args) throws  Exception{
        DatagramSocket  socket = new DatagramSocket()  ;
        InetAddress sA =    InetAddress.getByName("203.162.10.109") ;
        int sP = 2207 ;

        String code = ";B22DCCN290;TpUJqmZT" ;

        DatagramPacket dpGui = new DatagramPacket(code.getBytes() , code.length() , sA , sP) ;
        socket.send(dpGui);

        System.out.println("Gui len server " + code);

        byte [] buffer = new byte[1024] ;

        DatagramPacket dpNhan = new DatagramPacket(buffer , buffer.length) ;
        socket.receive(dpNhan);

        String s = new String(dpNhan.getData())  ;
        System.out.println("Nhan duoc tu server " + s);

        s = s.replace(";" , " ") ;
        s = s.replace("," , " ") ;

        String ss [] = s.trim().split("\\s+") ;

        String requestId = ss[0]  ;

        List<Integer> list = new ArrayList<>() ;

        for(int i = 1 ; i < ss.length ; i++) {
            list.add(Integer.parseInt(ss[i])) ;
        }

        list.sort((a,b) -> Integer.compare(a,b));

        int max = list.get(list.size() - 1) ;
        int min = list.get(0) ;

        String kq = requestId + ";" + max + "," + min ;

       DatagramPacket dpgui1 = new DatagramPacket(kq.getBytes( ) , kq.length() , sA , sP) ;

       socket.send(dpgui1) ;

        System.out.println("Da gui kq " + kq );



    }
}
