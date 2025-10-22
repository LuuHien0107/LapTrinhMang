package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP_String3 {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket() ;
        InetAddress sA = InetAddress.getByName("203.162.10.109") ;
        int sP = 2208 ;
        String ma = ";B22DCCN290;vjIiiVEs" ;
        DatagramPacket gui = new DatagramPacket(ma.getBytes() , ma.length() , sA , sP) ;
        socket.send(gui);

        System.out.println("Da gui len server " + ma);

        byte [] buffer = new byte[1024] ;
        DatagramPacket nhan = new DatagramPacket(buffer , buffer.length) ;
        socket.receive(nhan);

        String receive = new String(nhan.getData() , 0 , nhan.getLength()) ;
        System.out.println("Da nhan chuoi " + receive);

        receive = receive.replace(";" , " ") ;
        receive = receive.replace("," , " ") ;
        String ss[] = receive.trim().split("\\s+") ;
        String requestID = ss[0] ;
        String num1 = ss[1] ;
        String num2 = ss[2] ;
        int num1to10 = Integer.parseInt(num1 , 2) ;
        int num2to10 = Integer.parseInt(num2 , 2) ;
        int tong = num1to10 + num2to10 ;
        String kq = requestID + ";" + tong ;

        DatagramPacket guilai = new DatagramPacket(kq.getBytes() , kq.length() , sA , sP) ;
        socket.send(guilai);

        socket.close();
        System.out.println("Da gui len server " + kq);


    }
}
