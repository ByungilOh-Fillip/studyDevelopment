
<img src = "/img/BeakJoon/백준.png" width=1000px alt="백준_qorwns_Beakjoon"></img>
백준 11724번 문제 : https://www.acmicpc.net/problem/11724

<meta name="keywords" content="코딩테스트">
<meta name=“description” content = “백준 11724번 문제”>

</br></br>

백준 11724문제 해설
=============
</br>

### 문제
방향 없는 그래프가 주어졌을 때, 연결 요소 (Connected Component)의 개수를 구하는 프로그램을 작성하시오.

### 입력
첫째 줄에 정점의 개수 N과 간선의 개수 M이 주어진다. (1 ≤ N ≤ 1,000, 0 ≤ M ≤ N×(N-1)/2) 둘째 줄부터 M개의 줄에 간선의 양 끝점 u와 v가 주어진다. (1 ≤ u, v ≤ N, u ≠ v) 같은 간선은 한 번만 주어진다.

### 출력
첫째 줄에 연결 요소의 개수를 출력한다.

</br></br></br>
<img src = "/img/BeakJoon/No.11724/No.11724.png" width=1000px alt="연결된 그룹의 수"></img>
</br></br>

## 문제의 이해
방향 없는 그래프가 주어졌을 때 연결요소의 개수를 구하는 프로그램
여기서 연결요소는 서로 연결되어있는 그룹이 몇개인지 물어보는 것이다. 

위 문제의 1번에 해당하는 연결요소는 다음 그림과 같다. 
</br></br>
<img src = "/img/BeakJoon/No.11724/example No.11724.png" width=500px alt="연결된 그룹의 수"></img>
</br></br>

그림을 통해 연결된 요소수가 2개임을 알 수 있다.

### DFS Stack 
이번 문제는 DFS 혹은 BFS로 풀어낼 수 있는 문제다.
우선 DFS로 접근한 과정을 먼저 서술해보자

DFS는 깊이를 우선해서 탐색하는 트리탐색 방법 중 하나이다. 
탐색 과정자체에서 객체가 복잡해지거나 조건이 까다로워지는 경우가 있기 때문에 필요한 데이터를 분리해서 초기화해두면 좋다.

이 방법은 gpt와 Do it! 알고리즘을 통해서 알게되었다.

#### 1. 데이터 초기화 (예제입력 1을 기준으로 설명)
- 노드별 연결노드 번호 list-up (list)
- 노드별 방문여부 list-up 
- dfs 탐색을 위한 stack 초기화
<img src = "/img/BeakJoon/No.11724/List_No.11724.png" width=1000px alt="연결된 그룹의 수"></img>

위 그림의 표로 나타낸 데이터들을 순차적으로 코드 사이클을 설명하면
1.시작 노드의 **연결 노드**를 stack에 최근 노드 하나를 빼고 집어넣는다.

<img src = "/img/BeakJoon/No.11724/List_cycle2.png" width=1000px alt="연결된 그룹의 수"></img>
2.집어넣은 노드들의 방문여부를 true로 변경하고 top 노드를 빼면서 top 노드에 해당하는 **연결 노드**가 ```isVisited==false```라면 데이터를 집어넣고 이를 반복한다.

<img src = "/img/BeakJoon/No.11724/List_cycle3.png" width=1000px alt="연결된 그룹의 수"></img>
<img src = "/img/BeakJoon/No.11724/List_cycle4.png" width=1000px alt="연결된 그룹의 수"></img>
3.계속 반복하다 모든 노드를 확인하지 않았는데 stack은 빈 상태가 된다면 ```isVisited==false```라면 데이터를 집어넣는다 
<img src = "/img/BeakJoon/No.11724/List_cycle5.png" width=1000px alt="연결된 그룹의 수"></img>
4. 1번부터 순차적으로 다시 실행한다.
<img src = "/img/BeakJoon/No.11724/List_cycle6.png" width=1000px alt="연결된 그룹의 수"></img>
<img src = "/img/BeakJoon/No.11724/List_cycle7.png" width=1000px alt="연결된 그룹의 수"></img>
<img src = "/img/BeakJoon/No.11724/List_cycle8.png" width=1000px alt="연결된 그룹의 수"></img>

위 내용대로 데이터의 사이클이 돌아가고 stack이 비어서 새로운 노드를 추가하는 타이밍에 연결 요소의 갯수를 늘려주면 문제에서 요구하는 답을 도출할 수 있다. 

---

### 알고리즘


```java
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
        int popItem = 99; // popItem 판단 숫자 99.

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

```
