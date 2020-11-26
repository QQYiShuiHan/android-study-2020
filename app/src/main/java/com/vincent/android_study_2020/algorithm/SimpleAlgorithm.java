package com.vincent.android_study_2020.algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class SimpleAlgorithm {

	/**
	 * 0001.two-sum
	 * <p>
	 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
	 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
	 * <p>
	 * 给定 nums = [2, 7, 11, 15], target = 9
	 * 因为 nums[0] + nums[1] = 2 + 7 = 9
	 * 所以返回 [0, 1]
	 * <p>
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/two-sum
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * @param nums
	 * @param target
	 *
	 * @return
	 */

	public static int[] twoSumMe(int[] nums, int target) {
		int lenght = nums.length;
		for (int i = 0; i < lenght - 1; i++) {
			for (int j = i + 1; j < lenght; j++) {
				if (nums[i] + nums[j] == target) {
					return new int[]{i, j};
				}
			}
		}
		return null;
	}

	public static int[] twoSum(int[] nums, int target) {
		Map map = new HashMap();
		for (int i = 0; i < nums.length; i++) {
			int temp = target - nums[i];
			if (map.containsKey(temp)) {
				return new int[]{(int) map.get(temp), i};
			}
			map.put(nums[i], i);
		}
		return null;
	}

	/**
	 * 0020.Valid Parentheses
	 * <p>
	 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
	 * <p>
	 * 有效字符串需满足：
	 * <p>
	 * 左括号必须用相同类型的右括号闭合。
	 * 左括号必须以正确的顺序闭合。
	 * 注意空字符串可被认为是有效字符串。
	 * <p>
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/valid-parentheses
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * @param s
	 *
	 * @return
	 */
	public static boolean isValidMe(String s) {
		LinkedList<String> stack = new LinkedList();
		Map<String, String> relation = new HashMap<>();
		relation.put("(", ")");
		relation.put("[", "]");
		relation.put("{", "}");
		int length = s.length();
		for (int i = 0; i < length; i++) {
			String str = s.substring(i, i + 1);
			if (relation.containsKey(str)) {
				stack.push(str);
			} else if (relation.containsValue(str)) {
				if (stack.isEmpty()) {
					return false;
				}
				String stackTop = stack.pop();
				if (!str.equals(relation.get(stackTop))) {
					return false;
				}
			}
		}
		if (stack.size() <= 0) {
			return true;
		}
		return false;
	}

	public static boolean isValid(String s) {
		Stack<Character> stack = new Stack<>();
		for (char ch : s.toCharArray()) {
			if (ch == '{') {
				stack.push('}');
			} else if (ch == '[') {
				stack.push(']');
			} else if (ch == '(') {
				stack.push(')');
			} else if (stack.isEmpty() || stack.pop() != ch) {
				return false;
			}
		}
		return stack.isEmpty();
	}


	/**
	 * 0021.MergeTwoSortedLists
	 * <p>
	 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
	 * <p>
	 * 示例：
	 * <p>
	 * 输入：1->2->4, 1->3->4
	 * 输出：1->1->2->3->4->4
	 * <p>
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 */

	public static class ListNode {

		int val;
		ListNode next;

		public ListNode() {
		}

		public ListNode(int val) {
			this.val = val;
		}

		public ListNode(ListNode node, int val) {
			this.next = node;
			this.val = val;
		}


	}

	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(-1);
		ListNode head = dummy;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				head.next = l1;
				l1 = l1.next;
			} else {
				head.next = l2;
				l2 = l2.next;
			}
			head = head.next;
		}
		if (l1 != null) {
			head.next = l1;
		}
		if (l2 != null) {
			head.next = l2;
		}
		return dummy.next;
	}


	public static ListNode mergeTwoLists_2(ListNode l1, ListNode l2) {
		if (l1 == null) {
			return l2;
		} else if (l2 == null) {
			return l1;
		} else if (l1.val < l2.val) {
			l1.next = mergeTwoLists_2(l1.next, l2);
			return l1;
		} else {
			l2.next = mergeTwoLists_2(l1, l2.next);
			return l2;
		}
	}

	/**
	 * 0026.remove-duplicates-from-sorted-array
	 * <p>
	 * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
	 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
	 * <p>
	 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
	 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
	 * <p>
	 * 示例 1:
	 * <p>
	 * 给定数组 nums = [1,1,2],
	 * <p>
	 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
	 * <p>
	 * 你不需要考虑数组中超出新长度后面的元素。
	 * 示例 2:
	 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
	 * <p>
	 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
	 * 你不需要考虑数组中超出新长度后面的元素。
	 * <p>
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * @param nums
	 *
	 * @return
	 */

	public static int removeDuplicates(int[] nums) {
		int length = nums.length;
		int fastP = 0;
		int slowP = 0;
		while (fastP < length) {
			if (nums[slowP] == nums[fastP]) {
				fastP++;
			} else {
				slowP++;
				nums[slowP] = nums[fastP];
				fastP++;
			}
		}
		return slowP + 1;
	}


	/**
	 * 0053.maximum-sum-subarray
	 * <p>
	 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
	 * <p>
	 * Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
	 * <p>
	 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
	 * <p>
	 * 示例:
	 * <p>
	 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
	 * 输出: 6
	 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
	 * 进阶:
	 * <p>
	 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
	 * <p>
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/maximum-subarray
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * @param nums
	 *
	 * @return
	 */

	// 暴力解


	// 前缀和 + 暴力
	public static int maxSubArray_1(int[] nums) {
		int length = nums.length;
		int maxSum = Integer.MIN_VALUE;
		int sum = 0;
		for (int i = 0; i < length; i++) {
			sum = 0;
			for (int j = i; j < length; j++) {
				sum += nums[j];
				maxSum = Math.max(maxSum, sum);
			}
		}
		return maxSum;
	}

	// 前缀和 + 暴力  优化
	public int maxSubArray3(int[] nums) {
		int maxSum = nums[0];
		int sum = 0;
		int minSum = 0;
		for (int num : nums) {
			// prefix Sum
			sum += num;
			// update maxSum
			maxSum = Math.max(maxSum, sum - minSum);
			// update minSum
			minSum = Math.min(minSum, sum);
		}
		return maxSum;
	}

	// 动态规划
	public static int MaximumSubarrayDP(int[] nums) {
		int length = nums.length;
		if (length == 0) {
			return 0;
		}
		int[] dp = new int[length];
		dp[0] = nums[0];
		for (int i = 1; i < length; i++) {
			if (dp[i - 1] >= 0) {
				dp[i] = dp[i - 1] + nums[i];
			} else {
				dp[i] = nums[i];
			}
		}
		int max = dp[0];
		for (int i = 1; i < length; i++) {
			max = Math.max(dp[i], max);
		}
		return max;
	}

	// 动态规划的简化
	public static int MaximumSubarrayDPSimplify(int[] nums) {
		int currMaxSum = nums[0];
		int maxSum = nums[0];
		for (int i = 1; i < nums.length; i++) {
			currMaxSum = Math.max(currMaxSum + nums[i], nums[i]);
			maxSum = Math.max(maxSum, currMaxSum);
		}
		return maxSum;
	}

	// 分治法
	public int maxSubArrayDividConquer(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		return helper(nums, 0, nums.length - 1);
	}

	private int helper(int[] nums, int l, int r) {
		if (l > r) return Integer.MIN_VALUE;
		int mid = (l + r) >>> 1;
		int left = helper(nums, l, mid - 1);
		int right = helper(nums, mid + 1, r);
		int leftMaxSum = 0;
		int sum = 0;
		// left surfix maxSum start from index mid - 1 to l
		for (int i = mid - 1; i >= l; i--) {
			sum += nums[i];
			leftMaxSum = Math.max(leftMaxSum, sum);
		}
		int rightMaxSum = 0;
		sum = 0;
		// right prefix maxSum start from index mid + 1 to r
		for (int i = mid + 1; i <= r; i++) {
			sum += nums[i];
			rightMaxSum = Math.max(sum, rightMaxSum);
		}
		// max(left, right, crossSum)
		return Math.max(leftMaxSum + rightMaxSum + nums[mid], Math.max(left, right));
	}

	/**
	 * 0088.merge-sorted-array
	 * <p>
	 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
	 * <p>
	 * 说明:
	 * <p>
	 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
	 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
	 *  
	 * <p>
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * @param nums1
	 * @param m
	 * @param nums2
	 * @param n
	 */
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int i = m - 1;
		int j = n - 1;
		int curr = m + n - 1;
		while (i >= 0 && j >= 0) {
			if (nums1[i] < nums2[j]) {
				nums1[curr] = nums2[j];
				j--;
			} else {
				nums1[curr] = nums1[i];
				i--;
			}
			curr--;
		}
		while (j >= 0) {
			nums1[curr--] = nums2[j--];
		}
	}


	/**
	 * Definition for a binary tree node.
	 */

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public boolean isSymmetric(TreeNode root) {



		return false;
	}


}