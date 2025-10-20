package UDP;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class CuPhapObject {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket() ;
        InetAddress sA = InetAddress.getByName("203.162.10.109") ;
        int sP = 2209 ;


        byte [] buffer = new byte[1024] ;
        DatagramPacket nhan = new DatagramPacket(buffer , buffer.length)  ;
        socket.receive(nhan);

        //Tach 8 byte dau
        String rq = new String(nhan.getData() , 0 , 8 ) ;

        // Doc Object
        ByteArrayInputStream input = new ByteArrayInputStream(nhan.getData() , 8 , nhan.getLength() - 8) ;
        ObjectInputStream in = new ObjectInputStream(input)  ;
        Product product = (Product) in.readObject() ;
        //chuan hoa


        //Gui lai cho server

        // Serialize lại đối tượng Product
        ByteArrayOutputStream output = new ByteArrayOutputStream() ;
        ObjectOutputStream out = new ObjectOutputStream(output) ;
        out.writeObject(product);
        out.flush();

        //Ghep rq va obj gui len sv

        byte [] gui2 = new byte[8 + output.size()] ;
        System.arraycopy(rq.getBytes() , 0 , gui2 , 0 , 8);
        System.arraycopy(output.toByteArray() , 0 , gui2 , 8 , output.size());

        DatagramPacket guilai = new DatagramPacket(gui2 , gui2.length ,sA , sP) ;
        socket.send(guilai);

        socket.close();
    }
}
