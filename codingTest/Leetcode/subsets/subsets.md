<img src = "/img/BeakJoon/백준.png" width=1000px alt="백준_qorwns_Beakjoon"></img>
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

-> 주어진 정수 배열에서 중복이 없이 가능한 모든 부분집합을 구하시오.

### 입력
\[nums = [1,2,3]\]

### 출력
\[[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]\]
## 문제의 이해
부분집합을 구하는 기본적인 문제이나 쉽지만은 않다.

처음에는 구해질 부분집합에서 배열의 사이즈로 구하기 시작했다.

---

### 예제 1번.

### 예제 2번.

### 알고리즘

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
