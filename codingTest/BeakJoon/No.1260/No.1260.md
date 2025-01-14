<img src = "/img/BeakJoon/백준.png" width=1000px alt="백준_qorwns_Beakjoon"></img>
백준 1260번 문제 : https://www.acmicpc.net/problem/1260 

<meta name="keywords" content="코딩테스트, DFS, BFS, 그래프, 깊이탐색, 너비탐색">
<meta name=“description” content = “백준 1260번 문제”>

</br></br>

백준 1260문제 해설
=============
</br>

### 문제
그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오. 단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 더 이상 방문할 수 있는 점이 없는 경우 종료한다. 정점 번호는 1번부터 N번까지이다.

### 입력
첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V가 주어진다. 다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다. 어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다. 입력으로 주어지는 간선은 양방향이다.

### 출력
첫째 줄에 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다. V부터 방문된 점을 순서대로 출력하면 된다.
</br></br></br>
<img src = "/img/BeakJoon/No.1260/No.1260.png" width=1000px alt="DFS와 BFS"></img>
</br></br>

## 문제의 이해
이 문제는 DFS와 BFS의 기본적인 이해가 있어야 접근이 가능한 문제다. 가장 기초적인 문제로 보일 수 있지만 개념자체에 난이도가 있어 공부가 필요하다.

### DFS란?
DFS는 **깊이 우선 탐색(Depth1-First Search)** 이라고 한다. 트리와 그래프 구조에서 탐색을 진행하는 알고리즘 중 하나로 같은 조상(라인)을 가진 노드 순서대로 계속 내려가며 탐색하고 다시 그 다음 조상(라인)을 가진 노드 순서대로 탐색하는 방식이다.  

### BFS란?
BFS는 **너비 우선 탐색(Breadth2-First Search)** 이라고 하며 DFS와 마찬가지로 트리와 그래프 구조에서 탐색을 진행하는 알고리즘 중 하나로 노드의 root 노드에서 leaf노드까지 같은 level상에 있는 모든 노드들을 순차적으로 탐색하는 방식이다.

> 자세한 내용과 예시는 다음 블로그를 참고했을 때 눈에 잘들어오고 이해가 됐어서 참고하면 좋다.
<br/>
**Huey님의 밸로그** - <a href="https://velog.io/@wkdgus7113/%EA%B7%B8%EB%9E%98%ED%94%84%EC%99%80-DFS-BFS-%EC%A0%95%EB%A6%AC"> 그래프와 DFS, BFS </a>
**veggie님의 티스토리** - <a href="https://veggie-garden.tistory.com/42"> DFS, BFS란? </a>

---
### 문제풀이 접근
처음에는 위 밸로그의 그림을 보고 stack과 queue로 구현하는 방법으로 진행했다. 진행하다보니..백준에서 제공된 문제는 그래프인데 반해 내가 풀고 보니 트리의 경우만 생각하고 풀어버려 문제가 됐었다.. 접근 방식은 stack과 queue를 이용해서 순차적으로 하나씩 빼서 연결되어있는 노드를 찾는 방식이었다.

이후 다른 방식은 없을까 모색 도중 배열에 데이터를 격자로 적다가 힌트를 얻었다. 

---

### 예제 1번.
1. 배열 list 정의 (초기화)
<img src = "/img/BeakJoon/No.1260/1260_example01.png" width=1000px alt="list"></img>
<br>
2. 시작노드를 인덱스로 검색하며 지난 노드를 체크하는 배열에 함수 체크
<br>
    **DFS의 경우**
    <img src = "/img/BeakJoon/No.1260/example01_DFS.png" width=1000px alt="list"></img>
<br>
    **BFS의 경우**
     <img src = "/img/BeakJoon/No.1260/example01_BFS.png" width=1000px alt="list"></img>
<br>
3. check 값이 false 이면서 현재 가르키는 인덱스에 해당하는 값이 list의 1이면
<br>
    **DFS** 
    자기 자신을 해당 인덱스로 호출 (재귀)
<br>
    **BFS** 
    Queue 값에 추가 후 담긴 순서대로 하나씩 빼서 (poll) 처리


위 내용으로 예제값과 list를 비교하며 데이터를 연결해보면 기대했던 결과 값이 나오는걸 확인할 수 있다.

### 예제 2번.
1. 배열 list 정의 (초기화)
<img src = "/img/BeakJoon/No.1260/1260_example02.png" width=1000px alt="list"></img>
<br>
2. 시작노드를 인덱스로 검색하며 지난 노드를 체크하는 배열에 함수 체크
<br>
    **DFS의 경우**
    <img src = "/img/BeakJoon/No.1260/example02_DFS.png" width=1000px alt="list"></img>
<br>
    **BFS의 경우**
     <img src = "/img/BeakJoon/No.1260/example02_BFS.png" width=1000px alt="list"></img>
<br>
3. check 값이 false 이면서 현재 가르키는 인덱스에 해당하는 값이 list의 1이면
<br>
    **DFS** 
    자기 자신을 해당 인덱스로 호출 (재귀)
<br>
    **BFS** 
    Queue 값에 추가 후 담긴 순서대로 하나씩 빼서 (poll) 처리


위 내용으로 예제값과 list를 비교하며 데이터를 연결해보면 기대했던 결과 값이 나오는걸 확인할 수 있다.


### 알고리즘
#### 1. 포인터의 자료를 list로 정의하여 문제풀기.
<img src = "/img/BeakJoon/No.1260/1260_example01.png" width=1000px alt="list"></img>

위 그림은 주어진 포인터 배열을 리스트에 담은 것이다.
예제 1번 데이터를 기준으로 담은 것이고 list로 담는 규칙은 
1 4 라는 데이터가 있을 사 1,4 / 4,1 에 해당하는 데이터에 담는다.

-> 위 처럼 담는 이유는 데이터가 정렬되어 들어오지 않는 상황에서 데이터를 정리하기 위해 이렇게 정리하였다.

#### 2. 데이터 정렬 후 배열의 앞 값을 인덱스로 활용하기 

```java

public static void dfs(int start) {
		
		sb.append(start + " ");
		
		for(int i = 1 ; i <= node ; i++) {
			if(mapArr[start][i] == 1 && )
				dfs(i);
		}
		
	}
	
	public static void bfs(int start) {

		q.add(start);
		
		while(!q.isEmpty()) {
			
			start = q.poll();
			sb.append(start + " ");
			
			for(int i = 1 ; i <= node ; i++) {
				if(mapArr[start][i] == 1 ) {
					q.add(i);
				}
			}
		}

	}

```

위 코드는 배열의 앞의 값을 인덱스로 start(시작노드)에 해당하는 값들을 검색하는 내용이다. 하지만 이렇게 풀고보니 지나왔던 노드에 대해서 지나왔는지 명확한 정의가 없어 체크했던 포인터도 계속 찾게되는 문제가 생겼다.

#### 3. 거쳐간 노드를 확인하는 배열 확인

```java
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
```
해서 위 코드와 같이 check라는 배열을 추가해서 데이터를 확인하는 방식을 추가했다. 

#### 4. 최종 코드
```java
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
   
```
