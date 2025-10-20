package UDP;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP_Object {
    public static String chuanhoaten(String s) {
        String ss[] = s.trim().split("\\s+") ;
        String tmp = ss[ss.length - 1] ;
        for (int i = 1 ; i < ss.length - 1 ; i++) {
            tmp += " " ;
            tmp += ss[i] ;
        }
        tmp += " " ;
        tmp += ss[0] ;
        return tmp ;
    }
    public static int chuanhoasoluong(int soluong){
        String tmp = Integer.toString(soluong) ;
        StringBuilder s = new StringBuilder(tmp) ;
        String ss = s.reverse().toString() ;
        return Integer.parseInt(ss) ;
    }


    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket() ;
        InetAddress sA = InetAddress.getByName("203.162.10.109")  ;
        int sP = 2209 ;
        String code = ";B22DCCN290;PG6Itick"  ;
        DatagramPacket gui = new DatagramPacket(code.getBytes() , code.length() , sA , sP)  ;
        socket.send(gui);
        System.out.println("Da gui len server " + code);

        byte [] buffer = new byte[1024] ;
        DatagramPacket nhan = new DatagramPacket(buffer , buffer.length ) ;
        socket.receive(nhan);

        String rq = new String(nhan.getData() , 0 , 8)  ;
        System.out.println("Da nhan rq " + rq);

        ByteArrayInputStream input = new ByteArrayInputStream(nhan.getData() , 8 , nhan.getLength() - 8) ;
        ObjectInputStream in = new ObjectInputStream(input) ;
        Product product = (Product) in.readObject() ;
        System.out.println(product);

        product.name = chuanhoaten(product.name) ;
        product.quantity = chuanhoasoluong(product.quantity) ;

        System.out.println(product);

        ByteArrayOutputStream output = new ByteArrayOutputStream()  ;
        ObjectOutputStream out = new ObjectOutputStream(output)  ;
        out.writeObject(product);
        out.flush();


        // tao mang gui data moi
        byte [] gui2 = new byte[8 + output.size()] ;
        System.arraycopy(rq.getBytes() , 0 , gui2 ,0 , 8);
        System.arraycopy(output.toByteArray() , 0 , gui2 , 8 ,output.size());
        DatagramPacket guilai = new DatagramPacket(gui2 , gui2.length , sA , sP) ;
        socket.send(guilai);

        System.out.print("Da gui ");
        System.out.println(product);
        socket.close();





    }
}
class Product implements Serializable {
    private static final long serialVersionUID = 20161107;
    String id , code ,name  ;
    int quantity ;

    public Product(String id, String code, String name, int quantity) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
