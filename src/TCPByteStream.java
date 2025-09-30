/*
[Mã câu hỏi (qCode): Uc1AKH0c].  Một chương trình server hỗ trợ kết nối qua giao thức TCP tại cổng 2206
(hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s).
Yêu cầu xây dựng chương trình client thực hiện kết nối tới server sử dụng luồng byte dữ liệu (InputStream/OutputStream)
để trao đổi thông tin theo thứ tự:
a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode".
Ví dụ: "B16DCCN999;D45EFA12"
b. Nhận dữ liệu từ server là một chuỗi các số nguyên được phân tách bởi ký tự ",".
Ví dụ: "10,5,15,20,25,30,35"
c. Xác định hai số trong dãy có tổng gần nhất với gấp đôi giá trị trung bình của toàn bộ dãy.
Gửi thông điệp lên server theo định dạng "num1,num2" (với num1 < num2)
Ví dụ: Với dãy "10,5,15,20,25,30,35", gấp đôi giá trị trung bình là 40, h
ai số có tổng gần nhất là 15 và 25. Gửi lên server chuỗi "15,25".
d. Đóng kết nối và kết thúc chương trình.
 */
import java.net.* ;
import java.util.* ;
import java.io.* ;
import java.lang.* ;
public class TCPByteStream {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("203.162.10.109" ,2206) ;
        InputStream is = socket.getInputStream() ;
        OutputStream os = socket.getOutputStream() ;

        String ma = "B22DCCN290;Uc1AKH0c" ;
        os.write(ma.getBytes());
        os.flush();

        System.out.println("Da gui ma thanh cong");


        byte [] buffer = new byte[1024] ;
        int byteReads = is.read(buffer) ;
        String s = new String(buffer , 0 , byteReads) ;

        System.out.println("Da nhan duoc chuoi tu server" + s);

        //tach chuoi

        s = s.replace(","," ") ;
        String ss[] = s.split("\\s+") ;
        ArrayList<Integer> arr = new ArrayList<>() ;
        int tong = 0 ;
        for(String x : ss) {
            arr.add(Integer.parseInt(x)) ;
            tong += Integer.parseInt(x) ;
        }

        int trungbinh = tong * 2/ ss.length ;
        int num1 = 0 , num2 = 0 ;
        int mindiff = Integer.MAX_VALUE ;

        for(int i = 0 ; i < arr.size() ; i++) {
            for(int j = i + 1 ; j < arr.size() ; j++) {
                int sum = arr.get(i) + arr.get(j) ;
                int khoangcach = Math.abs(sum - trungbinh) ;
                if(khoangcach < mindiff) {
                    num1 = Math.min(arr.get(i), arr.get(j) ) ;
                    num2 = Math.max(arr.get(i) , arr.get(j)) ;
                    mindiff = khoangcach ;
                 }

            }
        }

        String result = num1 + "," + num2 ;
        os.write(result.getBytes());
        os.flush();

        System.out.println("Gui ket qua thanh cong : " + result);
        is.close();
        os.close();
        socket.close();



    }
}
