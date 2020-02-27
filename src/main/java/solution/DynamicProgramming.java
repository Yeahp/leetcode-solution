package solution;

public class DynamicProgramming {

    // Q1: 打家劫舍：求可以盗取的最大数（不能盗取相邻元素）
    public int maxRob(int[] arr) {
        int len = arr.length;
        int[] max = new int[len];
        max[0] = arr[0];
        max[1] = arr[1];
        for (int i = 2; i < len; i++) {
            max[i] = Math.max(arr[i - 1], arr[i] + max[i - 2]);
        }
        return max[len - 1];
    }


    // Q2: 最少的硬币个数
    // 设有n种不同面值的硬币，各硬币的面值存于数组T[1:n]中。现要用这些面值的硬币来找钱。可以使用的各种面值的硬币个数存于数组Coins[1:n]中。
    // 对任意钱数0≤m≤20001，设计一个用最少硬币找钱m的方法。
    // 对于给定的1≤n≤10，硬币面值数组T和可以使用的各种面值的硬币个数数组Coins，以及钱数m，0≤m≤20001，计算找钱m的最少硬币数
    // 输入数据第一行中只有1个整数给出n的值,第2行起每行2个数，分别是T[j]和Coins[j]。最后1行是要找的钱数m
    // 输出数据只有一个整数，表示计算出的最少硬币数。问题无解时输出-1
    // 输入：
    // 3
    // 1 3
    // 2 3
    // 5 3
    // 18
    // 输出：
    // 5


    // Q3: 三角形最小路径和（给定一个三角形，找出最小路径和。每一步只能移动到下一行中相邻的结点上）
    // [2],
    // [3,4],
    // [6,5,7],
    // [4,1,8,3]
    // 输出 2 + 3 + 5 + 1 = 11



    // Q4: 给定一个无序的整数数组，找到其中最长上升子序列的长度
    public int maxAscendSubArr(int[] arr) {
        int max = 1;
        int left = 0;
        for (int right = 1; right < arr.length; right++) {
            if (arr[right] > arr[right - 1]) {
                max = Math.max(max, right - left + 1);
            } else {
                left = right;
            }
        }
        return max;
    }


    // Q5: 国王和金矿【与背包问题类似】
    // 有一个国家发现了5座金矿，每座金矿的黄金储量不同，需要参与挖掘的工人数也不同，参与挖矿工人的总数是10人。
    // 每座金矿要么全挖，要么不挖，不能派出一半人挖取一半金矿。
    // 要求用程序求解出，要想得到尽可能多的黄金，应该选择挖取哪几座金矿？
    // 500金5人，200金3人，300金4人，350金3人，400金5人


    // 给定一个二叉树，根节点为第1层，深度为 1。在其第 d 层追加一行值为 v 的节点
    //添加规则：给定一个深度值 d （正整数），针对深度为 d-1 层的每一非空节点 N，为 N 创建两个值为 v 的左子树和右子树。
    //
    //将 N 原先的左子树，连接为新节点 v 的左子树；
    //
    //将 N 原先的右子树，连接为新节点 v 的右子树。
    //
    //如果 d 的值为 1，深度 d - 1 不存在，则创建一个新的根节点 v，原先的整棵树将作为 v 的左子树。



    // 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词
    // 说明：1.拆分时可以重复使用字典中的单词；2.你可以假设字典中没有重复的单词。

    public static void main(String[] args) {
        DynamicProgramming dp = new DynamicProgramming();
        System.out.println(dp.maxAscendSubArr(new int[]{1,2,3,4,5,6,7,8,5,6,7,8,9,10,11}));
    }
}
