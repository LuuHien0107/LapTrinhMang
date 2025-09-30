package TCP;
import java.io.*;
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
    public static void main(String[] args) {
        try (Socket socket = new Socket("203.162.10.109", 2209);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

            // a. Gửi mã sinh viên + mã câu hỏi
            String request = "B22DCCN290;rrlDoUAB";
            oos.writeObject(request);
            oos.flush();
            System.out.println("Đã gửi: " + request);

            // b. Nhận Product từ server
            Product product = (Product) ois.readObject();
            System.out.println("Nhận Product: " + product.getName() + ", price = " + product.getPrice());

            // c. Tính tổng chữ số phần nguyên của price
            int sumDigits = 0;
            long integerPart = (long) product.getPrice();
            while (integerPart > 0) {
                sumDigits += integerPart % 10;
                integerPart /= 10;
            }
            product.setDiscount(sumDigits);
            System.out.println("Tổng chữ số phần nguyên = " + sumDigits);

            // d. Gửi lại Product với discount mới
            oos.writeObject(product);
            oos.flush();
            System.out.println("Đã gửi Product với discount mới.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// Class Product KHÔNG khai báo public
class Product implements Serializable {
    private static final long serialVersionUID = 20231107;
    private int id;
    private String name;
    private double price;
    private int discount;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getDiscount() { return discount; }
    public void setDiscount(int discount) { this.discount = discount; }
}
