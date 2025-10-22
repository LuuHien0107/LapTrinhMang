
package UDP;
/*
[Mã câu hỏi (qCode): Z8Uuuj39].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2209. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản sau:

Đối tượng trao đổi là thể hiện của lớp UDP.Book được mô tả:

    Tên đầy đủ lớp: UDP.Book
    Các thuộc tính: id (String), title (String), author (String), isbn (String), publishDate (String)
    Hàm khởi tạo:
        public Book(String id, String title, String author, String isbn, String publishDate)
    Trường dữ liệu: private static final long serialVersionUID = 20251107L

Thực hiện:

a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B23DCCN005;eQkvAeId"

b. Nhận thông điệp chứa: 08 byte đầu chứa chuỗi requestId, các byte còn lại chứa một đối tượng là thể hiện của lớp Book từ server. Trong đó, các thuộc tính id, title, author, isbn, và publishDate đã được thiết lập sẵn.

c. Thực hiện:
        Chuẩn hóa title: viết hoa chữ cái đầu của mỗi từ, các chữ còn lại viết thường
        Chuẩn hóa author theo định dạng "Họ, Tên", Họ viết hoa tất cả, tên viết hoa chu cai dau con lai viet thuong
        Chuẩn hóa mã ISBN theo định dạng "978-3-16-148410-0"
        Chuyển đổi publishDate từ yyyy-mm-dd sang mm/yyyy.
d. Gửi lại đối tượng đã được chuẩn hóa về server với cấu trúc: 08 byte đầu chứa chuỗi requestId và các byte còn lại chứa đối tượng Book đã được sửa đổi.

Đóng socket và kết thúc chương trình.
 */
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP_Object4 {
    public static String chuanhoatitle(String s) {
        String[] ss = s.trim().split("\\s+");
        for (int i = 0; i < ss.length; i++) {
            ss[i] = ss[i].substring(0, 1).toUpperCase() + ss[i].substring(1).toLowerCase();
        }
        return String.join(" ", ss);
    }

    public static String chuanhoaauthor(String s) {
        String ss[] = s.trim().split("\\s+") ;
        String ten = "" ;
        for(int i = 0 ; i < ss.length ; i++) {
            ten += ss[i].substring(0,1).toUpperCase() + ss[i].substring(1).toLowerCase() ;
            if(i == 0) {
                ten = ten.toUpperCase() ;
                ten += "," ;
            }
            ten += " " ;
        }
        ten = ten.substring(0,ten.length() - 1) ;
        return ten ;
    }
    public static String chuanhoama(String s) {
        String ma = s.substring(0,3)
                + "-" + s.substring(3,4) +
                "-" + s.substring(4,6) +
                "-" + s.substring(6,12) +
                "-" + s.substring(12) ;
        return ma ;
    }
    public static String chuanhoangay (String s) {
        String ngay = s.substring(5,7) + "/" + s.substring(0,4) ;
        return ngay ;
    }


    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket()  ;
        InetAddress sA = InetAddress.getByName("203.162.10.109") ;
        int sP = 2209 ;
        String ma = ";B22DCCN290;Z8Uuuj39"  ;
        DatagramPacket gui = new DatagramPacket(ma.getBytes() , ma.length() , sA , sP) ;
        socket.send(gui);
        System.out.println("Da gui len server " + ma);

        byte [] buffer = new byte[1024]  ;
        DatagramPacket nhan = new DatagramPacket(buffer , buffer.length)  ;
        socket.receive(nhan);

        String requestID = new String(nhan.getData() , 0 , 8) ;
        System.out.println("Da nhan request " + requestID);

        ByteArrayInputStream input = new ByteArrayInputStream(nhan.getData() , 8 , nhan.getLength() - 8 ) ;
        ObjectInputStream in = new ObjectInputStream(input)  ;
        Book book = (Book) in.readObject() ;
        System.out.println(book);
        book.title = chuanhoatitle(book.title) ;
        book.author = chuanhoaauthor(book.author) ;
        book.isbn = chuanhoama(book.isbn) ;
        book.publishDate = chuanhoangay(book.publishDate) ;




        ByteArrayOutputStream output = new ByteArrayOutputStream()  ;
        ObjectOutputStream out = new ObjectOutputStream(output) ;
        out.writeObject(book);
        out.flush();

        byte [] gui2 = new byte[1024] ;
        System.arraycopy(requestID.getBytes() , 0 ,gui2 , 0 , 8) ;
        System.arraycopy(output.toByteArray() , 0 , gui2 , 8 , output.size());

        DatagramPacket guilai = new DatagramPacket(gui2 , gui2.length , sA ,sP)  ;
        socket.send(guilai);

        socket.close();
        System.out.println(book);




    }
}
class Book  implements Serializable {
    String id , title , author , isbn , publishDate  ;
    private static final long serialVersionUID = 20251107L ;

    public Book(String id, String title, String author, String isbn, String publishDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publishDate='" + publishDate + '\'' +
                '}';
    }
}