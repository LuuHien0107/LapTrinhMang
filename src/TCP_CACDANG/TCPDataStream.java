package TCP_CACDANG;
import java.io.* ;
import java.util.* ;
import java.lang.* ;
import java.net.* ;
public class TCPDataStream {
    public static void main(String[] args)throws Exception {
        Socket socket = new Socket("203.162.10.109" , 2208) ;
        DataInputStream dis = new DataInputStream(socket.getInputStream()) ;
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream()) ;

        String ma = "B22DCCN290;abc" ;
        dos.writeUTF(ma);

        String s = dis.readUTF() ;


        dos.writeUTF(s);
        // đọc ghi với int or string thì có phương thức riêng
        dis.close();
        dos.close();
        socket.close();



    }
}
