package TCP_CACDANG;
import java.io.* ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.* ;
import java.lang.* ;
import java.net.* ;
public class TCPBuffered {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("203.162.10.109" , 2208) ;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())) ;
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream())) ;

        //gui ma
        String ma = "B22DCCN290;abc" ;
        bw.write(ma);
        bw.newLine();
        bw.flush();

        //nhan du lieu tu server
        String s = br.readLine() ;
        //gui lai du lieu lên server
        bw.write(s);
        bw.newLine();
        bw.flush();



    }
}
