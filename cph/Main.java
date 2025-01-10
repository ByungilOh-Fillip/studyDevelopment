package cph;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        
        // Example for input and output
        int n = Integer.parseInt(br.readLine());
        out.println(n * 2);

        out.close();
    }
}
