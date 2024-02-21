package M12_广度优先搜索.单词接龙;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 力扣127. 单词接龙 [困难]
 * <a href="https://leetcode.cn/problems/word-ladder/description/">https://leetcode.cn/problems/word-ladder/description/</a>
 *
 * @author Chimm.Huang
 * @date 2024/2/21
 */
public class LeetCode_127_Hard {

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");
        long start = System.currentTimeMillis();
        System.out.println(solution.ladderLength("hit", "cog", wordList));
        long end = System.currentTimeMillis();
        System.out.println("用时：" + (end - start) + "ms");

    }

    static class Solution {

        /*
            做法：先将字典列表，转换为图，存储他们之间的关系。
         */
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            wordList.add(beginWord);
            // 将字典列表，转换为图存储
            Map<String, LinkedList<String>> graph = new HashMap<>();
            int size = wordList.size();
            for (int i = 0; i < size; i++) {
                String wordI = wordList.get(i);
                char[] wordICharArray = wordI.toCharArray();
                for (int j = i + 1; j < size; j++) {
                    String wordJ = wordList.get(j);
                    char[] wordJCharArray = wordJ.toCharArray();

                    int diffirenceNum = 0;
                    for (int k = 0; k < wordICharArray.length; k++) {
                        if (wordICharArray[k] != wordJCharArray[k] && ++diffirenceNum > 1) {
                            break;
                        }
                    }

                    // 只有一个字母的差异，则保存其关系
                    if (diffirenceNum == 1) {
                        LinkedList<String> wordIRelationList = graph.getOrDefault(wordI, new LinkedList<>());
                        wordIRelationList.add(wordJ);
                        LinkedList<String> wordJRelationList = graph.getOrDefault(wordJ, new LinkedList<>());
                        wordJRelationList.add(wordI);

                        graph.put(wordI, wordIRelationList);
                        graph.put(wordJ, wordJRelationList);
                    }
                }
            }

            // 图已转换完毕，若图中不包含endWord，则无需进行 bfs 搜索
            if (!graph.containsKey(endWord)) {
                return 0;
            }

            return bfs(beginWord, endWord, graph);
        }

        private int bfs(String beginWord, String endWord, Map<String, LinkedList<String>> graph) {
            Queue<String> currentQueue = new ArrayDeque<>();
            currentQueue.add(beginWord);

            Map<String, Boolean> visitedMap = new HashMap<>();

            int num = 0;
            boolean isFind = false;
            for (; ; ) {
                Queue<String> nextQueue = new ArrayDeque<>();
                while (!currentQueue.isEmpty()) {
                    String word = currentQueue.poll();

                    // 已被访问过，则跳过
                    if (visitedMap.getOrDefault(word, false)) {
                        continue;
                    }
                    visitedMap.put(word, true);

                    if (word.equals(endWord)) {
                        isFind = true;
                        break;
                    }
                    nextQueue.addAll(graph.get(word));
                }

                num++;

                if (isFind) {
                    break;
                }

                // 已经不能访问了，则证明没有找到
                if (nextQueue.isEmpty()) {
                    return 0;
                }

                currentQueue = nextQueue;
            }
            return num;
        }
    }
}
