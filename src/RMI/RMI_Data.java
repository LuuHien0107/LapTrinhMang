package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class RMI_Data {
    public static void main(String[] args) throws Exception{
        Registry rq = LocateRegistry.getRegistry("203.162.10.109" , 1099) ;
        DataService sv = (DataService) rq.lookup("RMIDataService") ;

        int amount = (int) sv.requestData("B22DCCN290" , "Ud9BczqG") ;
        System.out.println(amount);

        List <Integer> list = new ArrayList<>() ;
        int a[] = {10,5,2,1} ;

        for(int i = 0 ; i < 4 ; i++) {
            while (amount >= a[i]) {
                list.add(a[i]) ;
                amount -= a[i] ;
            }
        }
        String kq = "" ;
        for(int x : list) {
            kq += x + "," ;
        }
        kq = kq.substring(0, kq.length() - 1) ;
        kq = "" + list.size() +"; " + kq ;
        sv.submitData("B22DCCN290" , "Ud9BczqG" , (Object) kq);

        System.out.println(kq);



    }
}
