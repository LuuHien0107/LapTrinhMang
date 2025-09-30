/*
[Mã câu hỏi (qCode): Lku3UKrA].  Một chương trình server hỗ trợ kết nối qua giao thức TCP
tại cổng 2206 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s).
Yêu cầu xây dựng chương trình client thực hiện kết nối tới server trên sử dụng
luồng byte dữ liệu (InputStream/OutputStream) để trao đổi thông tin theo thứ tự:
a.	Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B16DCCN999;C64967DD"
b.	Nhận dữ liệu từ server là một chuỗi gồm các giá trị nguyên được phân tách với nhau bằng  "|"
Ex: 2|5|9|11
c.	Thực hiện tìm giá trị tổng của các số nguyên trong chuỗi và gửi lên server
Ex: 27
d.	Đóng kết nối và kết thúc
 */

import java.net.* ;
import java.util.* ;
import java.io.* ;
import java.lang.* ;
public class TCP_ByteStream {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("203.162.10.109" , 2206) ;
        InputStream in = socket.getInputStream() ;
        OutputStream out = socket.getOutputStream() ;

        String ma = "B22DCCN290;Lku3UKrA" ;
        out.write(ma.getBytes());
        out.flush();

        System.out.println("gui ma len sv " + ma);

        byte [] buffer = new byte[1024] ;
        int byteread = in.read(buffer) ;
        String s = new String(buffer , 0 , byteread) ;
        System.out.println("da nhan duoc chuoi" + s);
        s = s.replace("|" , " ") ;
        String ss[] = s.trim().split("\\s+") ;

        int sum = 0 ;
        for (String x : ss) {
            sum += Integer.parseInt(x) ;
        }
        String kq = String.valueOf(sum) ;

        out.write(kq.getBytes());
        out.flush();

        System.out.println("đã gửi kq = " + kq);

        in.close();
        out.close();

        socket.close();





    }
}
