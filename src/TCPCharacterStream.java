import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/*
[Mã câu hỏi (qCode): 1P21CLTZ].  Một chương trình server cho phép kết nối qua giao thức TCP
tại cổng 2208 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5 giây).
Yêu cầu là xây dựng một chương trình client tương tác với server sử dụng các luồng ký tự
(BufferedReader/BufferedWriter) theo kịch bản sau:

a. Gửi một chuỗi chứa mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;C1234567"

b. Nhận từ server một chuỗi chứa nhiều từ, các từ được phân tách bởi khoảng trắng.
 Ví dụ: "hello world this is a test example"

c. Sắp xếp các từ trong chuỗi theo độ dài, thứ tự xuất hiện.
 Gửi danh sách các từ theo từng nhóm về server theo định dạng: "a, is, this, test, hello, world, example".

d. Đóng kết nối và kết thúc chương trình.
 */
public class TCPCharacterStream {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("203.162.10.109" , 2208) ;
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream())) ;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())) ;

        String ma = "B22DCCN290;1P21CLTZ" ;
        bw.write(ma);
        bw.newLine();
        bw.flush();
        System.out.println("Da gui" + ma);

        //Nhan du lieu tu server
        String receiveData = br.readLine() ;
        System.out.println("Nhan duoc du lieu tu server" + receiveData);

        String ss[] = receiveData.trim().split("\\s+") ;

        Arrays.sort(ss , Comparator.comparingInt(String :: length));
        String result = "" ;
        for(String x : ss) {
            result += x + ", " ;
        }
        result = result.substring(0,result.length() - 2) ;

        bw.write(result);
        System.out.println("Da gui chuoi " + result);

        bw.close();
        br.close();
        socket.close();



    }
}
