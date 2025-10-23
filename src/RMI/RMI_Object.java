package RMI;

import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMI_Object {
    public static int tinhtong (String s ){
        int sum = 0 ;
        for(int i = 0 ; i < s.length() ; i++) {
            if(Character.isDigit(s.charAt(i))) {
                sum += Integer.parseInt(s.substring(i,i+1)) ;
            }
        }
        return sum ;
    }
    public static void main(String[] args) throws Exception{
        Registry rq = LocateRegistry.getRegistry("203.162.10.109" , 1099) ;
        ObjectService sv = (ObjectService) rq.lookup("RMIObjectService") ;

        ProductX productX = (ProductX) sv.requestObject("B22DCCN290" , "u2kHt4i3") ;

        System.out.println(productX);
        productX.discount = tinhtong(productX.discountCode)  ;


        sv.submitObject("B22DCCN290" , "u2kHt4i3" , productX);
        System.out.println(productX);


    }
}
class ProductX implements Serializable {
    private static final long serialVersionUID = 20171107;
    String id , code , discountCode ;
    int discount ;

    public ProductX(String id, String code, String discountCode, int discount) {
        this.id = id;
        this.code = code;
        this.discountCode = discountCode;
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "ProductX{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", discountCode='" + discountCode + '\'' +
                ", discount=" + discount +
                '}';
    }
}
