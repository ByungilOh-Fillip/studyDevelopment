// Beakjoon No.1260 - DFS와 BFS

import java.util.*;
import java.io.*;

public class DFS와_BFS {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Stack<Character> dfsStack = new Stack<>();
        Queue<Character> bfsQueue = new LinkedList<>();

        String[] inputArr = br.readLine().split(" ");
        int nodeLen = Integer.parseInt(inputArr[0]); // 노드갯수
        int xfsLen = Integer.parseInt(inputArr[1]); // 간선갯수
        char startNode = inputArr[2].charAt(0); // 시작노드

        List<String> graph = new ArrayList<>(); // 그래프 데이터
        char[][] answer = new char[2][xfsLen]; // 결과 출력


        //데이터 초기화
        dfsStack.push(startNode);
        bfsQueue.add(startNode); 


        for(int i=0; i<nodeLen;i++){
            String node = br.readLine();

            if(startNode==node.charAt(0)){
                dfsStack.push(node.charAt(2));
                bfsQueue.add(node.charAt(2));
                continue;
            }

            if(startNode==node.charAt(2)){
                dfsStack.push(node.charAt(0));
                bfsQueue.add(node.charAt(0));
                continue;
            }

            graph.add(node.charAt(0)+""+node.charAt(2));
        } // 데이터 정리 

        // startNode 제거
        answer[0][0] = dfsStack.pop();
        answer[1][0] = bfsQueue.remove();

        
    }
}
