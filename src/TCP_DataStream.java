/*
[Mã câu hỏi (qCode): R9Wer7uf].  Một chương trình máy chủ cho phép kết nối qua TCP tại cổng 2207 (hỗ trợ thời gian liên lạc tối đa cho mỗi yêu cầu là 5s), yêu cầu xây dựng chương trình (tạm gọi là client) thực hiện kết nối tới server tại cổng 2207, sử dụng luồng byte dữ liệu (DataInputStream/DataOutputStream) để trao đổi thông tin theo thứ tự:
a.	Gửi chuỗi là mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;1D25ED92"
b.	Nhận lần lượt hai số nguyên a và b từ server
c.	Thực hiện tính toán tổng, tích và gửi lần lượt từng giá trị theo đúng thứ tự trên lên server
d.	Đóng kết nối và kết thúc
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class TCP_DataStream {
    public static void main(String[] args) throws  Exception{
        Socket socket = new Socket("203.162.10.109",2207) ;
        DataInputStream dis = new DataInputStream(socket.getInputStream()) ;
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream()) ;

        String ma = "B22DCCN290;R9Wer7uf" ;
        dos.writeUTF(ma);
        dos.flush();

        System.out.println("da gui duoc ma");


        int a = dis.readInt() ;
        int b = dis.readInt() ;

        System.out.println("Nhan duoc 2 so a va b :" + a + "va" + b);
        int tong = a + b ;
        int tich = a * b ;

        dos.writeInt(tong);
        dos.flush();

        System.out.println("Da gui tong = " + tong);

        dos.writeInt(tich);
        dos.flush();

        System.out.println("Da gui tich = " + tich );

    }
}
