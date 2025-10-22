package TCP;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TCPObject {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("203.162.10.109" , 2209) ;
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()) ;
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream()) ;

        String ma = "B22DCCN290" ;

        out.writeObject(ma);
        out.flush();

        Hocsinh hocsinh = (Hocsinh) in.readObject() ;
        System.out.println(hocsinh);


    }
}

class Hocsinh{
    int diem ;

    public Hocsinh(int diem) {
        this.diem = diem;
    }

    @Override
    public String toString() {
        return "Hocsinh{" +
                "diem=" + diem +
                '}';
    }
}