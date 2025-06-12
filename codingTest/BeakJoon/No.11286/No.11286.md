
<img src = "/img/BeakJoon/백준.png" width=1000px alt="백준_qorwns_Beakjoon"></img>
백준 11286번 문제 : https://www.acmicpc.net/problem/11286

<meta name="keywords" content="코딩테스트">
<meta name=“description” content = “백준 11286번 문제”>

</br></br>

백준 11286문제 해설
=============
</br>

### 문제
절댓값 힙은 다음과 같은 연산을 지원하는 자료구조이다.

배열에 정수 x (x ≠ 0)를 넣는다.
배열에서 절댓값이 가장 작은 값을 출력하고, 그 값을 배열에서 제거한다. 절댓값이 가장 작은 값이 여러개일 때는, 가장 작은 수를 출력하고, 그 값을 배열에서 제거한다.
프로그램은 처음에 비어있는 배열에서 시작하게 된다.

### 입력
첫째 줄에 연산의 개수 N(1≤N≤100,000)이 주어진다. 다음 N개의 줄에는 연산에 대한 정보를 나타내는 정수 x가 주어진다. 만약 x가 0이 아니라면 배열에 x라는 값을 넣는(추가하는) 연산이고, x가 0이라면 배열에서 절댓값이 가장 작은 값을 출력하고 그 값을 배열에서 제거하는 경우이다. 입력되는 정수는 -231보다 크고, 231보다 작다.

### 출력
입력에서 0이 주어진 회수만큼 답을 출력한다. 만약 배열이 비어 있는 경우인데 절댓값이 가장 작은 값을 출력하라고 한 경우에는 0을 출력하면 된다.

</br></br></br>
<img src = "/img/BeakJoon/No.11286.png" width=1000px alt="절대값 힙"></img>
</br></br>

## 문제의 이해
이 문제는 절댓값 힙을 코드로 구현하는 문제이다.
따라서 절댓값 힙에 대해서 먼저 정리해보고자 한다.

### 정의
1. 가장 작은 절댓값을 가진 수가 먼저 나온다.
2. 절댓값이 같은 경우, 음수인 수가 먼저 나온다.

### 이용할 수 있을까?
사용되는 것은 척도에 기반한 데이터에 사용될 것 같다. 
거리계산 시 직선상의 거리에서 기준점으로 떨어진 거리는 절댓값을 기준으로 판단할 수 있다.

이는 통계에서 척도를 나타내는 부분으로도 사용된다. 
정규분포상에서 최빈, 평균, 중앙값에 해당하는 기준과 떨어진 거리

그외에 이용은 하기 나름일거 같지만 우선 통계에서 계산식을 만들 때와 거리를 구하고 기준점을 기반으로 어떤 처리를 해야할 때 이용할 수 있을거 같다. 

### 문제 풀이과정
처음에는 그냥 큐를 기반으로 기능을 구현하다가 java의 컬렉션 중 PriorityQueue를 이용할 수 있을것 같아 풀이에 사용해보기로 했다. 

#### PriorityQuere란?
- 내부적으로 완전 이진트리를 배열 형태의 힙으로 구현
- java에서는 기본적으로 최소힙 구조이다.
- 우선 순위가 높은 요소가 항상 루트에 위치

#### 우선순위를 정하는 방식
- 기본적으로는 숫자가 작은 요소가 높은 우선순위를 가짐
- compareTo를 이용해서 우선순위의 기준을 변경할 수 있음
  - 아래 코드 참조
  - compareTo(A)가 
    - 음수를 리턴하는 경우 -> 현재 객체가 먼저!
    - 양수를 리턴하는 경우 -> A객체가 먼저!
    - 0을 리턴하는 경우 -> 우선순위 같음!

그래서 정리하자면 
1. 완전 이진트리로 배열 형태의 힙을 구현한 priorityqueue
2. 우선순위는 낮은 인덱스를 높게하지만 compareTo의 조건식을 정의함에 따라 정렬 기준이 달라짐
3. 삽입과 삭제 시 모두 O(logN)의 시간 복잡도를 가지고 완전 이진트리의 조건을 충족한다.

```java 
public class Task implements Comparable<Task> {
    int priority;

    @Override
    public int compareTo(Task other) {
        return this.priority - other.priority;
    }
}
``` 

---

### 알고리즘


```java
import java.util.*;
import java.lang.Math;

public class main
{
    public static void main(String[] args)
    {
        int[] arr = {1,-1,0,0,0,1,1,-1,-1,2,-2,0,0,0,0,0,0,0};
        String answer = "";

        // priorityQuere와 우선순위 설정 
        PriorityQueue<Integer> pQue = new PriorityQueue<>((o1,o2) -> {
            int f_abs = Math.abs(o1);
            int s_abs = Math.abs(o2);

            if(f_abs==s_abs){
                return o1 > o2 ? 1 : -1;
            }else{
                return f_abs-s_abs;
            }
            
        });

        // 
        for(int i=0;i<arr.length;i++){
            int request = arr[i];

            if(request==0){
                if(!pQue.isEmpty()){
                     answer += pQue.poll()+", ";
                }else{
                    answer += 0+", ";
                    System.out.println(answer);
                }
            }else{
                pQue.add(request);
            }
        }

    }
}

// 데이터 출력 결과
//
//
// -1, 
// -1, 1, 
// -1, 1, 0, 
// -1, 1, 0, 
// -1, 1, 0, 
// -1, 1, 0, 
// -1, 1, 0, 
// -1, 1, 0, 
// -1, 1, 0, 
// -1, 1, 0, -1, 
// -1, 1, 0, -1, -1, 
// -1, 1, 0, -1, -1, 1, 
// -1, 1, 0, -1, -1, 1, 1, 
// -1, 1, 0, -1, -1, 1, 1, -2, 
// -1, 1, 0, -1, -1, 1, 1, -2, 2, 
// -1, 1, 0, -1, -1, 1, 1, -2, 2, 0, 
```
