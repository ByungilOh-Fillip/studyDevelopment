//BeakJoon test No.1874

import java.util.*;

public class main
{
    public static void main(String[] args)
    {
        int[] arr = {1,2,5,3,4};
        int[] result = new int[arr.length];
        String answer = "";

        Stack<Integer> st = new Stack<>();
        int num = 1;

        for(int i=0; i<arr.length;i++){

            if(num<=arr[i]){
                while(num<=arr[i]){
                    st.push(num);
                    num++;
                    answer+="+";
                }
                result[i] = st.pop();
                answer+="-";
            }else{
                result[i] = st.pop();
                answer+="-";
            }   

        }

        if(Arrays.equals(arr,result)){
            for(char c : answer.toCharArray()){
                System.out.println(c);
            }
        }else{
            System.out.println("NO");
        }
        
    }

}
