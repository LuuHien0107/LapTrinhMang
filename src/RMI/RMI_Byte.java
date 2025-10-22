package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMI_Byte {
    public static void main(String[] args) throws Exception{
        Registry rq = LocateRegistry.getRegistry("203.162.10.109" , 1099) ;
        ByteService sv = (ByteService) rq.lookup("RMIByteService") ;
        byte [] a = sv.requestData("B22DCCN290" , "yWFYc695") ;


        for(byte x : a) System.out.print(x + " ");
        System.out.println();

        // Ma hoa
        String tmp = "PTIT" ;
        byte [] khoa = tmp.getBytes()  ;
        for (byte x : khoa ) System.out.print(x + " ");
        System.out.println();
        byte mahoa [] = new byte[a.length] ;
        for(int i = 0 ; i < a.length ; i++) {
            mahoa[i] = (byte) (a[i] ^ khoa[i % khoa.length]) ;
        }

        sv.submitData("B22DCCN290" , "yWFYc695" , mahoa);
        for(byte x : mahoa) System.out.print(x + " ");



    }
}
