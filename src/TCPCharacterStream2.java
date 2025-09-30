/*
[Mã câu hỏi (qCode): 2Yf0b7V0].  Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2208 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5 giây). Yêu cầu là xây dựng một chương trình client tương tác với server sử dụng các luồng ký tự (BufferedReader/BufferedWriter) theo kịch bản sau:
a. Gửi một chuỗi chứa mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode".
Ví dụ: "B15DCCN999;1D08FX21"
b. Nhận từ server một chuỗi chứa nhiều từ, các từ được phân tách bởi khoảng trắng.
Ví dụ: "hello world programming is fun"
c. Thực hiện đảo ngược từ và mã hóa RLE để nén chuỗi ("aabb" nén thành "a2b2"). Gửi chuỗi đã được xử lý lên server. Ví dụ: "ol2eh dlrow gnim2argorp si nuf".
d. Đóng kết nối và kết thúc chương trình
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Map;

public class TCPCharacterStream2 {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("203.162.10.109" , 2208) ;
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream())) ;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())) ;

        String ma = "B22DCCN290;2Yf0b7V0" ;
        bw.write(ma);
        bw.newLine();
        bw.flush();

        System.out.println("Da gui ma " + ma);

        String chuoi = br.readLine() ;

        System.out.println(chuoi);

        String s[] = chuoi.trim().split("\\s+") ;


        String tmp = "" ;
        int cnt = 1 ;
        for(int i = 0 ; i < s.length ; i++) {
            String ss = new StringBuilder(s[i]).reverse().toString()  ;
            for(int j = 0 ; j < ss.length() - 1 ; j++) {
                if(ss.charAt(j) == ss.charAt(j+1)) {
                    cnt += 1 ;
                }
                else {
                    if(cnt != 1 ) {
                        tmp += "" + ss.charAt(j) + cnt;
                        cnt = 1;
                    }
                    else tmp += "" + ss.charAt(j) ;
                }
            }

            if(cnt != 1) tmp += "" + ss.charAt(ss.length() - 1) + cnt + " ";
            else tmp += "" + ss.charAt(ss.length() - 1) + " " ;



        }
        tmp = tmp.substring(0 , tmp.length() - 1) ;

        bw.write(tmp);
        bw.newLine();
        bw.flush();
        System.out.println("Da gui chuoi " + tmp);
        br.close();
        bw.close();
        socket.close();


    }
}
