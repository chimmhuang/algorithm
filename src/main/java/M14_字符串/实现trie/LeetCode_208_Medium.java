package M14_字符串.实现trie;

import java.util.HashMap;
import java.util.Map;

/**
 * 力扣208. 实现Trie（前缀树） [中等]
 *
 * @author Chimm.Huang
 * @date 2024/2/22
 */
public class LeetCode_208_Medium {

    class Trie {

        class TreeNode {

            /**
             * 当前字符
             */
            char c;

            /**
             * 单词子字符
             */
            Map<Character, TreeNode> charTreeNodeMap = new HashMap<>();

            /**
             * 遍历到当前字符是否可以组成一个单词
             */
            boolean hasWord;
        }

        public Trie() {
            root = new TreeNode();
        }

        TreeNode root;

        public void insert(String word) {
            char[] charArray = word.toCharArray();
            TreeNode currentTreeNode = root;
            for (int i = 0; i < charArray.length; i++) {
                char c = charArray[i];
                if (currentTreeNode.charTreeNodeMap.containsKey(c)) {
                    currentTreeNode = currentTreeNode.charTreeNodeMap.get(c);
                } else {
                    TreeNode node = new TreeNode();
                    node.c = c;
                    currentTreeNode.charTreeNodeMap.put(c, node);
                    currentTreeNode = node;
                }

                if (i == charArray.length - 1) {
                    currentTreeNode.hasWord = true;
                }
            }
        }

        public boolean search(String word) {
            char[] charArray = word.toCharArray();
            TreeNode currentNode = root;
            for (int i = 0; i < charArray.length; i++) {
                char c = charArray[i];
                if (currentNode.charTreeNodeMap.containsKey(c)) {
                    currentNode = currentNode.charTreeNodeMap.get(c);
                } else {
                    return false;
                }
            }
            return currentNode.hasWord;
        }

        public boolean startsWith(String prefix) {
            char[] charArray = prefix.toCharArray();
            TreeNode currentNode = root;
            for (int i = 0; i < charArray.length; i++) {
                char c = charArray[i];
                if (currentNode.charTreeNodeMap.containsKey(c)) {
                    currentNode = currentNode.charTreeNodeMap.get(c);
                } else {
                    return false;
                }
            }
            return true;
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
}
