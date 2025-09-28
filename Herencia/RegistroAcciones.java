import java.util.*;
public class RegistroAcciones {
    private final String[] cola = new String[3];
    private int idx = 0;
    public void push(String msg){ 
        cola[idx % cola.length] = msg; idx++;
    }
    public java.util.List<String> ultimas(){
        java.util.List<String> out = new ArrayList<>();
        int start = Math.max(0, idx - cola.length);
        for(int i=start;i<idx;i++){
             String s=cola[i%cola.length]; if(s!=null) out.add(s);
            }
        return out;
    }
}