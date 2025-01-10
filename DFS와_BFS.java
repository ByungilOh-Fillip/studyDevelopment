// Beakjoon No.1260 - DFS와 BFS

import java.util.*;
import java.io.*;

public class DFS와_BFS {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        
        List<Integer> input = new ArrayList<>();

        String[] splitArr = br.readLine().split(" ");
        for(String s : splitArr){
            n = Integer.parseInt(s);
        }
        
        out.println(n * 2);

        out.close();
    }
}
