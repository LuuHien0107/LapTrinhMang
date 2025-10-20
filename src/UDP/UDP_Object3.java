package UDP;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP_Object3 {
    public static String chuanhoaten (String s ){
        String ss [] = s.trim().toLowerCase().split("\\s+") ;
        String ten = ss[ss.length - 1].toUpperCase() + ", " ;
        for(int i = 0 ; i < ss.length - 1 ; i++) {
            ten += ss[i].substring(0,1).toUpperCase() + ss[i].substring(1).toLowerCase() ;
            if(i != ss.length - 2) ten += " " ;
        }

        return  ten ;
    }
    public static String chuanhoangaysinh(String s) {
        s = s.replace("-"," ") ;
        String ss []  = s.split("\\s+") ;
        String tmp = ss[1] +"/" +  ss[0] + "/" + ss[2] ;
        return  tmp ;
    }
    public static String taotaikhoan(String s) {
        String ss[] = s.toLowerCase().split("\\s+") ;
        String tk = "" ;
        for(int i = 0 ; i < ss.length - 1 ; i++) {
            tk += ss[i].substring(0,1) ;

        }
        tk += ss[ss.length - 1] ;
        return tk ;
    }
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket() ;
        InetAddress sA = InetAddress.getByName("203.162.10.109") ;
        int sP = 2209 ;
        String ma = ";B22DCCN290;8mnKQiIR" ;
        DatagramPacket gui = new DatagramPacket(ma.getBytes() , ma.length() , sA , sP) ;
        socket.send(gui);

        System.out.println("Da gui ma len sv " + ma);

        byte [] buffer = new byte[1024] ;
        DatagramPacket nhan = new DatagramPacket(buffer , buffer.length) ;
        socket.receive(nhan);

        String rq = new String(nhan.getData() , 0 , 8) ;
        System.out.println("Da nhan rqID" + rq);

        ByteArrayInputStream input = new ByteArrayInputStream(nhan.getData() , 8 , nhan.getLength() - 8) ;
        ObjectInputStream in = new ObjectInputStream(input)  ;
        Customer customer = (Customer) in.readObject() ;
        System.out.println(customer);
        String ten = customer.name ;
        customer.name = chuanhoaten(customer.name) ;
        customer.dayOfBirth = chuanhoangaysinh(customer.dayOfBirth) ;
        customer.userName = taotaikhoan(ten) ;

        ByteArrayOutputStream output = new ByteArrayOutputStream() ;
        ObjectOutputStream out = new ObjectOutputStream(output)  ;
        out.writeObject(customer);
        out.flush();

        byte [] gui2 = new byte[8 + output.size()] ;
        System.arraycopy(rq.getBytes() , 0 , gui2 ,0 , 8 );
        System.arraycopy(output.toByteArray() , 0 , gui2 , 8 , output.size());
        DatagramPacket guilai = new DatagramPacket(gui2 , gui2.length , sA , sP) ;
        socket.send(guilai);

        System.out.println(customer);
        socket.close();


    }
}
class Customer implements Serializable{
    String id ,  code , name ,  dayOfBirth , userName ;
    private static final long serialVersionUID = 20151107;

    public Customer(String id, String code, String name, String dayOfBirth, String userName) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.dayOfBirth = dayOfBirth;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", dayOfBirth='" + dayOfBirth + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
