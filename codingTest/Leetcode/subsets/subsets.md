<img src = "/img/Leetcode/leetcode.png" width=1000px alt="leetcode"></img>
leetcode subsets 문제 : https://leetcode.com/problems/subsets/

<meta name="keywords" content="코딩테스트, subsets, bitmask">
<meta name=“description” content = “leetcode_subsets”>

</br></br>

leetcode subsets 문제 해설
=============
</br>

### 문제
Given an integer array nums of unique elements, return all possible 
subsets
 (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

-> 주어진 정수 배열에서 중복이 없이 가능한 모든 부분집합을 구하시오. (순서는 상관없습니다.)

### 입력
\[nums = [1,2,3]\]

### 출력
\[[[ ],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]\]
## 문제의 이해
부분집합을 구하는 기본적인 문제이나 쉽지만은 않다.

처음에는 구해질 부분집합에서 배열의 사이즈로 구하기 시작했다.
size를 전역 변수로 하나 선언해두고 size의 값이 배열 size값보다 크지 않다면 계속 반복하며 값을 넣는 방식으로 문제풀이를 진행했는데 

size순으로 정렬한 경우
\[ [[],[1],[2],[3],[1,2],[1,3],[2,3],[1,2,3]] \]

이런식으로 정렬되어 문제에서 요구한 return 값과 정렬의 순서가 다른 점이 이상했다.
찾아보니 bitmask를 이용하는 방법이 있었고 bitmask의 순서가

000 / 001 / 010 / 011 / 100 / ...

진행되어 문제에서 요구한 정렬 조건과 일치해서 이 방법으로 다시 문제풀이를 진행했다.

---

### 예제 1번.
**입력**
\[nums = [1,2,3]\]
**출력**
\[[[ ],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]\]

bitmask로 순회를 돌며 순회를 도는 bitmask값과 AND연산자로 비교하여 일치하는 지 여부를 판단한다. 

<img src = "/img/Leetcode/subsets_bitmask.png" width=1000px alt="bitmask"></img>

### 다른 코드
leetcode는 문제를 다 풀면 정답으로 작성된 코드도 보여주어 이를 정리하기로 했다.

```java
import java.util.*;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        backtrack(0, nums, new ArrayList<>(), output);
        return output;
    }

    public void backtrack(
        int index, 
        int[] nums,
        List<Integer> subset,
        List<List<Integer>> output
    ) {
        if (index == nums.length) {
            output.add(new ArrayList<>(subset));
        } else {
            subset.add(nums[index]);
            backtrack(index + 1, nums, subset, output);
            subset.remove(subset.size() - 1);
            backtrack(index + 1, nums, subset, output);
        }
    }
}
```

함수 내부를 계속 순회하는 재귀형식으로 짠 코드이고 backtrack이라는 방식을 사용하였다.

순회되는 내용을 예제의 값을 넣어 비교해보면 

subset
[]
[1]
[1, 2]
[1, 2, 3]
-> output.add([1,2,3])

[1, 2]
-> output.add([1,2])

[1]
[1, 3]
-> output.add([1,3])

[1]
-> output.add([1])

[]
[2]
[2, 3]
-> output.add([2,3])

[2]
-> output.add([2])

[]
[3]
-> output.add([3])

[]
-> output.add([])

이런 형식으로 진행된다.

순서가 보장되어야한다고 생각하고 풀었었는데 풀고보니 순서는 상관없는 문제였다..

### 최종 코드

```java
import java.util.*;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        int n = nums.length;
        for (int mask = 0; mask < (1 << n); mask++) { // 0부터 2^n 미만 까지 반복
            List<Integer> subset = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) { // i번째 비트가 켜져있다면
                    subset.add(nums[i]);
                }
            }
            result.add(subset);
        }

        return result;
    }
}
```
