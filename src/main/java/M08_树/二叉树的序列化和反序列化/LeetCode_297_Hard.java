package M08_树.二叉树的序列化和反序列化;

import java.util.ArrayList;
import java.util.List;

/**
 * 力扣297. 二叉树的序列化和反序列化 [困难]
 *
 * @author Chimm Huang
 * @date 2024/1/14
 */
public class LeetCode_297_Hard {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;

        node3.left = node4;
        node3.right = node5;

        node4.left = node6;
        node4.right = node7;

        Codec codec = new Codec();
        String serialize = codec.serialize(node1);
        System.out.println(serialize);
        TreeNode treeNode = codec.deserialize(serialize);
        System.out.println(treeNode);
    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    public static class Codec {

        /*
            树结构：
                1
            2       3
                  4   5

            将树结构平铺为数组：
                初始化：list.add(root)   [1]   i=0  list.size = 1
            接着开始遍历赋值子节点的值
                for(int i = 0 ; i < list.size() ; i++) {
                    TreeNode node = list.get(i);
                    list.add(node.left);
                    list.add(node.right);
                }
            第1次遍历，先获取node左边的值：[1,2]，再获取node右边的值：[1,2,3] i=0 list.size = 3
            第2次遍历，先获取node左边的值：[1,2,3,null]，再获取node右边的值：[1,2,3,null,null] i=1 list.size = 5
            第3次遍历，先获取node左边的值：[1,2,3,null,null,4]，再获取node右边的值：[1,2,3,null,null,4,5] i=2 list.size = 7
            第4次遍历，获取到的值为 null，直接开启下一次遍历 i=3 list.size = 7
            第5次遍历，获取到的值为 null，直接开启下一次遍历 i=4 list.size = 7
            第6次遍历，先获取node左边的值：[1,2,3,null,null,4,5,null]，再获取node右边的值：[1,2,3,null,null,4,5,null,null] i=5 list.size = 9
            第7次遍历，先获取node左边的值：[1,2,3,null,null,4,5,null,null,null]，再获取node右边的值：[1,2,3,null,null,4,5,null,null,null,null] i=6 list.size = 11
            第8次遍历，获取到的值为 null，直接开启下一次遍历 i=7 list.size = 11
            第9次遍历，获取到的值为 null，直接开启下一次遍历 i=8 list.size = 11
            第10次遍历，获取到的值为 null，直接开启下一次遍历 i=9 list.size = 11
            第11次遍历，获取到的值为 null，直接开启下一次遍历 i=10 list.size = 11
            这种方式会导致数组后面有很多的 null。属于正常的。

            接着将数组后面的 null 值给删掉，将中间的值给序列化为字符串。最终结果为
            {1,2,3,null,null,4,5}
         */
        public String serialize(TreeNode root) {
            if (root == null) {
                return "{}";
            }

            List<TreeNode> nodeList = new ArrayList<>();
            // 初始化根节点
            nodeList.add(root);

            for (int i = 0; i < nodeList.size(); i++) {
                // 补全该节点的左右子节点
                TreeNode node = nodeList.get(i);
                if (node == null) {
                    continue;
                }
                nodeList.add(node.left);
                nodeList.add(node.right);
            }

            // 将该数组的后续 null 值给删除掉。
            for (int i = nodeList.size() - 1; i > 0; i--) {
                TreeNode node = nodeList.get(i);
                if (node != null) {
                    break;
                }
                nodeList.remove(i);
            }

            // 将数组转换为字符串
            StringBuilder sb = new StringBuilder("{");
            for (int i = 0; i < nodeList.size(); i++) {
                TreeNode node = nodeList.get(i);
                sb.append(node == null ? "null" : node.val);
                if (i != nodeList.size() - 1) {
                    sb.append(",");
                }
            }
            sb.append("}");
            return sb.toString();
        }


        /*
            输入：{1,2,3,null,null,4,5}

            先取出树的根节点，并将父节点存入 list：
                    树： 1            list：[1]
            第1次遍历，先判断左边节点，再判断左边节点，并节点存入 list，此时，左节点不为空，将其添加到父节点的 left，更改添加节点为右节点  list.index = 0 父节点：1
               字符串：{1,2,3,null,null,4,5}   树： 1  list:[1,2]
                         ↑                     2

            第2次遍历，此时，右节点不为空，将其添加到父节点的 right，并将节点存入 list。右节点遍历完毕，开始完善下一个父节点  list.index = 0  父节点：1
               字符串：{1,2,3,null,null,4,5}   树： 1  list:[1,2,3]
                           ↑                   2     3

            第3次遍历，此时，左节点为空，判断右节点  list.index = 1  父节点：2
               字符串：{1,2,3,null,null,4,5}   树： 1  list:[1,2,3]
                              ↑                2     3

            第4次遍历，此时，右节点为空， 开始完善下一个父节点 list.index = 1 父节点：2
               字符串：{1,2,3,null,null,4,5}   树： 1  list:[1,2,3]
                                   ↑           2     3

            第5次遍历，此时，左节点不为空，将其添加到父节点的 left，并将节点存入 list， 开始完善右节点 list.index = 2 父节点：3
               字符串：{1,2,3,null,null,4,5}   树： 1  list:[1,2,3,4]
                                       ↑       2     3
                                                    4

            第6次遍历，此时，右节点不为空，将其添加到父节点的 right，并将节点存入 list， 遍历结束 list.index = 2 父节点：3
               字符串：{1,2,3,null,null,4,5}   树： 1  list:[1,2,3,4]
                                         ↑     2     3
                                                    4 5

         */
        public TreeNode deserialize(String data) {
            if (data == null || "".equals(data) || "{}".equals(data)) {
                return null;
            }

            // 将括号给去掉
            data = data.substring(1, data.length() - 1);

            // 将字符串转为数组
            String[] split = data.split(",");

            // 将数组完善为树结构
            TreeNode root = new TreeNode(Integer.parseInt(split[0]));
            List<TreeNode> nodeList = new ArrayList<>();
            nodeList.add(root);

            int parentIndex = 0;
            boolean isLeft = true;
            for (int i = 1; i < split.length; i++) {
                String value = split[i];

                if (value.equals("null")) {
                    // 如果右节点也为空，则开始完善下一个父节点
                    if (!isLeft) {
                        parentIndex++;
                    }
                } else {
                    TreeNode node = new TreeNode(Integer.parseInt(value));
                    nodeList.add(node);
                    TreeNode parentNode = nodeList.get(parentIndex);
                    if (isLeft) {
                        parentNode.left = node;
                    } else {
                        parentNode.right = node;
                        // 右节点完善后，开始完善下一个节点
                        parentIndex++;
                    }
                }

                // 交换左右节点
                isLeft = !isLeft;
            }
            return root;
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
