package TCP;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

/*
[Mã câu hỏi (qCode): rrlDoUAB].  Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2209 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5 giây). Yêu cầu là xây dựng một chương trình client tương tác với server sử dụng các luồng đối tượng (ObjectOutputStream/ObjectInputStream) theo kịch bản dưới đây:

Biết lớp TCP.Product gồm các thuộc tính (id int, name String, price double, int discount) và private static final long serialVersionUID = 20231107;

a. Gửi đối tượng là một chuỗi gồm mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;1E08CA31"

b. Nhận một đối tượng là thể hiện của lớp TCP.Product từ server.

c. Tính toán giá trị giảm giá theo price theo nguyên tắc: Giá trị giảm giá (discount) bằng tổng các chữ số trong phần nguyên của giá sản phẩm (price). Thực hiện gán giá trị cho thuộc tính discount và gửi lên đối tượng nhận được lên server.

d. Đóng kết nối và kết thúc chương trình.
 */
public class TCPObjectClient {
    public static String suaten(String s) {
        String ss[] = s.trim().split("\\s+") ;
        String tmp = ss[ss.length - 1] + " " ;
        for(int i = 1 ; i < ss.length - 1 ; i++) {
            tmp += ss[i] ;
            tmp += " " ;
        }
        tmp += ss[0] ;
        return tmp ;
    }
    public static int suagia(int gia) {
        String s = Integer.toString(gia) ;
        StringBuilder ss = new StringBuilder(s).reverse() ;
        return Integer.parseInt(ss.toString()) ;

    }

    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("203.162.10.109" , 2209)  ;
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()) ;
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())  ;

        String ma = "B22DCCN290;B0kAanAU" ;
        out.writeObject(ma);
        out.flush();

        System.out.println("Da gui ma " + ma);

        Laptop laptop = (Laptop) in.readObject() ;
        System.out.println(laptop);

        laptop.name = suaten(laptop.name) ;
        laptop.quantity = suagia(laptop.quantity)  ;

        out.writeObject(laptop);

        out.flush();

        System.out.println(laptop);



    }
}

class Laptop implements Serializable {
    private static final long serialVersionUID = 20150711L;
    int id ;
    String code , name ;
    int quantity ;

    public Laptop(int id, String code, String name, int quantity) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
