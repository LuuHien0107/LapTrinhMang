package TCP;
/*
[Mã câu hỏi (qCode): qWm4k7Eu].  Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2209 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5 giây). Yêu cầu là xây dựng một chương trình client tương tác với server sử dụng các luồng đối tượng (ObjectOutputStream/ObjectInputStream) để gửi/nhận và chuẩn hóa thông tin địa chỉ của khách hàng.
Biết rằng lớp TCP.Address có các thuộc tính (id int, code String, addressLine String, city String, postalCode String) và trường dữ liệu private static final long serialVersionUID = 20180801L.
a. Gửi đối tượng là một chuỗi gồm mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;A1B2C3D4"
b. Nhận một đối tượng là thể hiện của lớp TCP.Address từ server. Thực hiện chuẩn hóa thông tin addressLine bằng cách:
•	Chuẩn hóa addressLine: Viết hoa chữ cái đầu mỗi từ, in thường các chữ còn lại,
 loại bỏ ký tự đặc biệt và khoảng trắng thừa (ví dụ: "123 nguyen!!! van cu" → "123 Nguyen Van Cu")
•	Chuẩn hóa postalCode: Chỉ giữ lại số và ký tự "-" ví dụ: "123-456"
c. Gửi đối tượng đã được chuẩn hóa thông tin địa chỉ lên server.
d. Đóng kết nối và kết thúc chương trình.
 */
import java.io.*;
import java.net.Socket;

public class TCPObjectClient2 {
    public static void main(String[] args) {
        try (Socket socket = new Socket("203.162.10.109", 2209);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

            // a. Gửi mã sinh viên + mã câu hỏi
            String request = "B22DCCN290;qWm4k7Eu";
            oos.writeObject(request);
            oos.flush();
            System.out.println("Đã gửi: " + request);

            // b. Nhận đối tượng Address từ server
            Address address = (Address) ois.readObject();
            System.out.println("Nhận địa chỉ: " + address.getAddressLine() + ", postalCode = " + address.getPostalCode());

            // Chuẩn hóa addressLine
            String normalizedAddress = normalizeAddressLine(address.getAddressLine());
            address.setAddressLine(normalizedAddress);

            // Chuẩn hóa postalCode
            String normalizedPostal = normalizePostalCode(address.getPostalCode());
            address.setPostalCode(normalizedPostal);

            System.out.println("Sau chuẩn hóa: " + address.getAddressLine() + ", " + address.getPostalCode());

            // c. Gửi lại đối tượng sau khi chuẩn hóa
            oos.writeObject(address);
            oos.flush();
            System.out.println("Đã gửi địa chỉ sau khi chuẩn hóa.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Hàm chuẩn hóa addressLine
    private static String normalizeAddressLine(String input) {
        // Loại bỏ ký tự đặc biệt, chỉ giữ chữ, số, khoảng trắng
        String cleaned = input.replaceAll("[^a-zA-Z0-9\\s]", " ");
        // Loại bỏ khoảng trắng thừa
        cleaned = cleaned.trim().replaceAll("\\s+", " ");
        // Viết hoa chữ cái đầu mỗi từ
        String[] words = cleaned.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                sb.append(Character.toUpperCase(word.charAt(0)));
                if (word.length() > 1) {
                    sb.append(word.substring(1).toLowerCase());
                }
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }

    // Hàm chuẩn hóa postalCode: chỉ giữ số và dấu '-'
    private static String normalizePostalCode(String input) {
        return input.replaceAll("[^0-9\\-]", "");
    }
}

// Class Address
class Address implements Serializable {
    private static final long serialVersionUID = 20180801L;
    private int id;
    private String code;
    private String addressLine;
    private String city;
    private String postalCode;

    // Getter/Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getAddressLine() { return addressLine; }
    public void setAddressLine(String addressLine) { this.addressLine = addressLine; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
}
