// Beakjoon No.1260 - DFS와 BFS

import java.util.*;
import java.io.*;

public class DFS와_BFS {
    static StringBuilder sb = new StringBuilder();
	static boolean[] check;
	static int[][] mapArr;
	
	static int node, line, start;
	
	static Queue<Integer> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		node = Integer.parseInt(st.nextToken());
		line = Integer.parseInt(st.nextToken());
		start= Integer.parseInt(st.nextToken());
		
		mapArr = new int[node+1][node+1];
		check = new boolean[node+1];
		

		for(int i = 0 ; i < line ; i ++) {
			StringTokenizer str = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(str.nextToken());
			int b = Integer.parseInt(str.nextToken());
			
			mapArr[a][b] = mapArr[b][a] =  1;	
		}


        //sb.append("\n");
        dfs(start);
        sb.append("\n");
        check = new boolean[node+1];

        bfs(start);
        
        System.out.println(sb);
    
    }

	public static void dfs(int start) {
		
		check[start] = true;
		sb.append(start + " ");
		
		for(int i = 1 ; i <= node ; i++) {
			if(mapArr[start][i] == 1 && !check[i])
				dfs(i);
		}
		
	}
	
	public static void bfs(int start) {

		q.add(start);
		check[start] = true;
		
		while(!q.isEmpty()) {
			
			start = q.poll();
			sb.append(start + " ");
			
			for(int i = 1 ; i <= node ; i++) {
				if(mapArr[start][i] == 1 && !check[i]) {
					q.add(i);
					check[i] = true;
				}
			}
		}
		

	}

}
