import java.util.*;
import java.lang.Math;

public class main
{
    public static void main(String[] args)
    {
        int[] arr = {1,-1,0,0,0,1,1,-1,-1,2,-2,0,0,0,0,0,0,0};
        String answer = "";

        PriorityQueue<Integer> pQue = new PriorityQueue<>((o1,o2) -> {
            int f_abs = Math.abs(o1);
            int s_abs = Math.abs(o2);

            if(f_abs==s_abs){
                return o1 > o2 ? 1 : -1;
            }else{
                return f_abs-s_abs;
            }
            
        });

        for(int i=0;i<arr.length;i++){
            int request = arr[i];

            if(request==0){
                if(!pQue.isEmpty()){
                     answer += pQue.poll()+", ";
                }else{
                    answer += 0+", ";
                }
            }else{
                pQue.add(request);
            }
        }

        System.out.println(answer);
    }
}
