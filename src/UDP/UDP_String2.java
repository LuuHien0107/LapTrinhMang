package UDP;
import java.io.* ;
import java.net.* ;
import java.util.* ;
public class UDP_String2 {
    public static void main(String[] args)throws Exception {
        DatagramSocket socket = new DatagramSocket() ;
        InetAddress sA = InetAddress.getByName("203.162.10.109") ;
        int sP = 2208 ;
        String ma = ";B22DCCN290;m0RA1bsY" ;
        DatagramPacket gui = new DatagramPacket(ma.getBytes() , ma.length() , sA , sP) ;
        socket.send(gui);
        System.out.println("Da gui chuoi " + ma);

        byte [] buffer = new byte[1024] ;
        DatagramPacket nhan = new DatagramPacket(buffer , buffer.length) ;
        socket.receive(nhan);

        String s = new String(nhan.getData() , 0 , nhan.getLength()) ;
        System.out.println("Da nhan duoc chuoi " + s);
        s = s.replace(";" , " ") ;
        String ss[] = s.trim().split("\\s+") ;
        String rq = ss[0] ;
        ArrayList<String> arr = new ArrayList<>() ;
        for(int i = 1 ; i < ss.length ; i++) {
            arr.add(ss[i]) ;
        }
        arr.sort((a , b) -> b.toLowerCase().compareTo(a.toLowerCase())) ;
        rq += ";" ;
        for(int i = 0 ; i < arr.size() ; i++) {

            rq += arr.get(i) ;
            rq += "," ;
        }
        rq = rq.substring(0 , rq.length() - 1) ;
        DatagramPacket guilai = new DatagramPacket(rq.getBytes() ,rq.length() , sA , sP) ;
        socket.send(guilai);
        System.out.println("Da gui len server " + rq );
        socket.close();
    }
}
