package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedHashMap;
import java.util.Map;

public class RMI_Character {
    public static void main(String[] args)throws Exception {
        Registry rq = LocateRegistry.getRegistry("203.162.10.109" , 1099) ;
        CharacterService sv = (CharacterService) rq.lookup("RMICharacterService") ;
        String  a = sv.requestCharacter("B22DCCN290" , "RqzbSwvC") ;

        System.out.println(a);

        LinkedHashMap<Character , Integer> mp = new LinkedHashMap<>() ;
        for(int i = 0 ; i < a.length() ; i++) {
            if(mp.containsKey(a.charAt(i))){
                mp.put(a.charAt(i) , mp.get(a.charAt(i)) + 1) ;
            }
            else {
                mp.put(a.charAt(i) , 1) ;
            }
        }
        String kq = "{" ;
        for(Map.Entry<Character , Integer> entry : mp.entrySet()) {
                kq += "\"" + entry.getKey() + "\"" +": " +entry.getValue() +", " ;
        }
        kq = kq.substring(0 , kq.length() - 2) ;
        kq += "}" ;
        sv.submitCharacter("B22DCCN290" , "RqzbSwvC" , kq);

        System.out.println("Da gui len server " + kq);
    }
}
