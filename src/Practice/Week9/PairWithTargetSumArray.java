
package Practice.Week9;

import java.util.HashSet;

public class PairWithTargetSumArray {

    static boolean bruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target)
                    return true;
            }
        }

        return false;
    }

    static boolean hasPairWithSum(int[] nums, int target) {
        HashSet<Integer> seen = new HashSet<>();

        for (int num : nums) {
            if (seen.contains(target - num))
                return true;

            seen.add(num);
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        System.out.println("Brute Force: " +
                bruteForce(nums, target));

        System.out.println("HashSet: " +
                hasPairWithSum(nums, target));
    }
}
