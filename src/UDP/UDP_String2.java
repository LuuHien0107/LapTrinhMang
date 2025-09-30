package UDP;
import java.io.* ;
import java.net.* ;
import java.util.* ;
public class UDP_String2 {
    public static void main(String[] args)throws Exception {
        DatagramSocket socket = new DatagramSocket() ;
        InetAddress sA = InetAddress.getByName("203.162.10.109") ;
        int sP = 2208 ;
        String code = ";B22DCCN290;m0RA1bsY"  ;
        DatagramPacket gui = new DatagramPacket(code.getBytes() ,code.length() , sA , sP)  ;
        socket.send(gui);

        System.out.println("DA GUI LEN SERVER " + code);

        byte [] buffer = new byte[1024]  ;
        DatagramPacket nhan = new DatagramPacket(buffer , buffer.length)  ;

        socket.receive(nhan);

        String s = new String(nhan.getData() , 0 , nhan.getLength()) ;
        System.out.println("DA NHAN TU SERVER " + s);

        s = s.replace(";" , " ") ;
        String ss[]  = s.trim().split("\\s+")  ;

        String requestID = ss[0]  ;
        ArrayList<String> arr = new ArrayList<>()  ;
        for(int i = 1 ; i < ss.length ; i++) {
            arr.add(ss[i]) ;
        }
        arr.sort((a , b) -> b.compareTo(a));

        for(String x : arr) {
            requestID += x + ";" ;
        }
        requestID = requestID.substring(0 , requestID.length() - 1) ;

        DatagramPacket gui2 = new DatagramPacket(requestID.getBytes() , requestID.length() , sA , sP) ;

        socket.send(gui2);

        System.out.println("DA GUI LEN SERVER " + requestID);
        socket.close();
    }
}
