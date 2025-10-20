package UDP;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP_Object2 {
    public  static String chuanhoaten(String s) {
        String ss[] = s.trim().toLowerCase().split("\\s+") ;
        for(int i = 0 ; i < ss.length ; i++) {
            ss[i] = ss[i].substring(0,1).toUpperCase() + ss[i].substring(1) ;
        }
        String tmp = String.join(" " , ss) ;
        return tmp ;

    }
    public static String taoemail(String s) {
        String ss [] = s.toLowerCase().split("\\s+") ;
        String email = ss[ss.length - 1] ;
        for(int i = 0 ; i < ss.length - 1 ; i++) {
            email += ss[i].substring(0,1) ;
        }
        email += "@ptit.edu.vn" ;
        return  email ;

    }
    public static void main(String[] args)throws Exception {
        DatagramSocket socket = new DatagramSocket()  ;
        InetAddress sA = InetAddress.getByName("203.162.10.109") ;
        int sP = 2209 ;
        String ma = ";B22DCCN290;F9GskLhO" ;
        DatagramPacket gui = new DatagramPacket(ma.getBytes() , ma.length() , sA , sP) ;
        socket.send(gui);

        System.out.println("Da gui ma " + ma);
        byte [] buffer = new byte[1024] ;

        DatagramPacket nhan = new DatagramPacket(buffer , buffer.length) ;
        socket.receive(nhan);

        String rq = new String(nhan.getData() , 0 , 8) ;

        System.out.println("Nhan rq " + rq);

        ByteArrayInputStream input = new ByteArrayInputStream(nhan.getData() , 8 , nhan.getLength() - 8) ;
        ObjectInputStream in = new ObjectInputStream(input) ;
        Student student = (Student)  in.readObject() ;
        System.out.println(student);
        student.name = chuanhoaten(student.name) ;
        student.email = taoemail(student.name) ;



        ByteArrayOutputStream output = new ByteArrayOutputStream() ;
        ObjectOutputStream out = new ObjectOutputStream(output) ;

        out.writeObject(student) ;
        out.flush();

        byte [] gui2 = new byte[8 + output.size()] ;
        System.arraycopy(rq.getBytes() , 0 , gui2 , 0 , 8);
        System.arraycopy(output.toByteArray() , 0 , gui2 , 8 , output.size());

        DatagramPacket guilai = new DatagramPacket(gui2 , gui2.length , sA , sP) ;
        socket.send(guilai) ;
        System.out.println(student);

        socket.close();




     }
}
class Student  implements  Serializable{
    String id , code , name , email ;
    private static final long serialVersionUID = 20171107  ;

    public Student(String id, String code, String name, String email) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.email = email;
    }

    public Student(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
