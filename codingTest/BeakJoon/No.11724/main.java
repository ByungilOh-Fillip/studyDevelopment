import java.util.*;
import java.io.*;

public class main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        // 몇개의 트리 or 그래프가 있는가? 
        int result = 0; 

        String firstLine = br.readLine();
        int node = firstLine.charAt(0)-'0';
        int trunk = firstLine.charAt(2)-'0';

        // ## DFS 값 초기화
        @SuppressWarnings("unchecked")
        List<Integer>[] listNode = new ArrayList[node];

        boolean[] isVisited = new boolean[node];

        // ### 방문 노드 초기화 & 그래프 표현 배열 초기화
        for(int i=0; i<node;i++){
            listNode[i] = new ArrayList<>();
            isVisited[i] = false;
        }

        // ### 그래프 표현 초기화
        for(int i=0; i<trunk;i++){
            String line = br.readLine();
            int first = line.charAt(0)-'0';
            int second = line.charAt(2)-'0';

            listNode[first-1].add(second-1);
            listNode[second - 1].add(first - 1);
        }

        // ### Stack 초기화 및 시작 노드 체크
        Stack<Integer> dfs = new Stack<>();
        dfs.push(0);
        isVisited[0]=true;
        int visitedCount = 0;
        result++;
        int popItem = 99;

        // ## dfs 탐색 시작
        while(visitedCount<node-1){

            if(dfs.empty()){
                result++;
                for(int i=0; i<node;i++){
                    if(isVisited[i]==false){
                        dfs.push(i);
                        isVisited[i] = true;
                        visitedCount++;
                        break;
                    }
                }
            }

            if(!dfs.empty()){
                popItem = dfs.pop();

                for(int i=0; i<listNode[popItem].size();i++){
                    int pushItem=listNode[popItem].get(i);
                    if(isVisited[pushItem]==false){
                        dfs.push(pushItem);
                        isVisited[pushItem] = true;
                        visitedCount++;
                    } 
                }
            }

        }

        out.println(result);
        out.close();
    }
}
