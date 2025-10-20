package TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class TCPDataStream {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("203.162.10.109" , 2207) ;
        DataInputStream dis = new DataInputStream(socket.getInputStream()) ;
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream())  ;

        String ma = "B22DCCN290;EQaJAvlj" ;
        dos.writeUTF(ma);

        int x = dis.readInt() ;
        System.out.println("Da nhan duoc so " + x);
        String henhiphan = Integer.toBinaryString(x) ;
        String hethaplucphan = Integer.toHexString(x).toUpperCase() ;
        String kq = henhiphan + ";" + hethaplucphan ;

        dos.writeUTF(kq);

        System.out.println("Da nhan duoc chuoi " + kq);

        dis.close();
        dos.close();
        socket.close();

    }
}
