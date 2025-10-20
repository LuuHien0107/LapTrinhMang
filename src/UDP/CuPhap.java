    package UDP;
    import java.io.* ;
    import java.net.* ;
    import java.util.* ;
    public class CuPhap {
        public static void main(String[] args) throws Exception{

           DatagramSocket socket = new DatagramSocket()  ;
           InetAddress sA = InetAddress.getByName("203.162.10.109")  ;
           int sP = 2208 ;
           String ma = "B22DCCN290"  ;
           DatagramPacket gui = new DatagramPacket(ma.getBytes() , ma.length() , sA , sP)  ;

           socket.send(gui);


           byte [] buffer = new byte[1024] ;

           DatagramPacket nhan = new DatagramPacket(buffer , buffer.length)  ;

           socket.receive(nhan);
           String s = new String(nhan.getData() , 0 , nhan.getLength())  ;

           socket.close();


        }
    }
