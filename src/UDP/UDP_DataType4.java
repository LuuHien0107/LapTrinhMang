package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP_DataType4 {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket() ;
        InetAddress sA = InetAddress.getByName("203.162.10.109") ;
        int sP = 2207 ;
        String ma = ";B22DCCN290;oQFCLzJs" ;
        DatagramPacket gui = new DatagramPacket(ma.getBytes() , ma.length() , sA , sP) ;
        socket.send(gui);

        System.out.println("Da gui len server " + ma);

        byte [] buffer = new byte[1024] ;
        DatagramPacket nhan = new DatagramPacket(buffer , buffer.length) ;
        socket.receive(nhan);


        String s = new String(nhan.getData() , 0 , nhan.getLength()) ;
        System.out.println("Da nhan duoc chuoi " + s);

        s = s.replace(";" , " ") ;
        String ss[] = s.trim().split("\\s+") ;
        String requestID = ss[0] ;
        String num = ss[1]  ;
        int tong = 0 ;
        for(int i = 0 ; i < num.length() ; i++) {
            tong += Integer.parseInt(String.valueOf(num.charAt(i))) ;
        }
        String kq = requestID + ";" + tong ;

        DatagramPacket guilai = new DatagramPacket(kq.getBytes() , kq.length() , sA , sP) ;
        socket.send(guilai);

        System.out.println("Da gui kq " + kq);

        socket.close();



    }
}
