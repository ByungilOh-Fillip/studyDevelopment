import java.util.*;

public class main
{
    public static void main(String[] args)
    {
        int[] arr = {5,4,2,1,3};
        int[] answer = new int[5];
        Stack<Integer> st = new Stack<>();
        st.push(0);

        for(int i=1;i<arr.length;i++){
            while(!st.isEmpty() && arr[st.peek()]<arr[i]){
                answer[st.pop()]=arr[i];
            }
            st.push(i);
        }

        while(!st.isEmpty()){
            answer[st.pop()]=-1;
        }
        
        for(int i : answer){
            System.out.println(i+", ");
        }
    }

}
