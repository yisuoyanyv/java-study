package leetcode;

public class T226InvertBinaryTree {

    /**
     *
     * 链接：https://leetcode-cn.com/problems/invert-binary-tree/
     *
     * @Class TreeNode
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     *
     *示例：
     * 输入：
     * ```
     *      4
     *    /   \
     *   2     7
     *  / \   / \
     * 1   3 6   9
     * ```
     * 输出：
     *
     * ```
     *      4
     *    /   \
     *   7     2
     *  / \   / \
     * 9   6 3   1
     */

    public static void main(String[] args) {

        //直接在leetcode 页面上进行运行

        long startTime=System.currentTimeMillis();

        System.out.println();

        long endTime=System.currentTimeMillis();

        System.out.println("程序执行共耗时："+ (endTime-startTime)+" 毫秒" );
    }


    public static TreeNode invertTree(TreeNode root) {
        if(root == null ) return null;
        TreeNode right=invertTree(root.left);
        TreeNode left=invertTree(root.right);
        root.left=left;
        root.right=right;
        return root;
    }



}

