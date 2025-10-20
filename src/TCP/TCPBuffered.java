package TCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TCPBuffered {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("203.162.10.109" , 2208) ;
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream())) ;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))  ;

        String ma = "B22DCCN290;4V6e1sjp" ;
        bw.write(ma);
        bw.newLine();
        bw.flush();
        System.out.println("Da gui chuoi len server " + ma);

        String s = br.readLine() ;

        System.out.println("Da nhan duoc chuoi " + s);

        int cnt[] = new int[1000005] ;
        for(char x : s.toCharArray()) {
            if(Character.isAlphabetic(x)) cnt[x] ++ ;
        }

        String ans = "" ;
        for(char x : s.toCharArray()) {
            if(cnt[x] > 0) ans += x ;
            cnt[x] = 0 ;
        }

        bw.write(ans);
        bw.newLine();
        bw.flush();
        System.out.println("Da gui chuoi " + ans);

    }
}
