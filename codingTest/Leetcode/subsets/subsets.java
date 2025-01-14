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