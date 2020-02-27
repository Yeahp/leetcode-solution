package solution;

import data.structure.TreeNode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeEnhanced {


    // 0 概述
    //   二叉树相关问题，主要分为：判断类、构建类、存储类、查找类、距离类、混合类这6类大问题

    // 1 判断类问题：判断二叉树是否是二叉搜索树、二叉完全树，以及两棵二叉树是否同构这3个问题
    // 1.1 判断一棵二叉树是否是二叉搜索树(BST)
    //  一种错误解法：假定当前结点值为 k，对于二叉树中每个结点，判断其左孩子的值是否小于 k，其右孩子的值是否大于 k。如果所有结点都满足该条件，则该二叉树是一棵二叉搜索树
    //  上面的错误是因为判断不完整导致，可以这样来判断：
    //  判断结点左子树最大值是否大于等于结点的值，如果是，则该二叉树不是二叉搜索树，否则继续下一步判断
    //  判断右子树最小值是否小于或等于结点的值，如果是，则不是二叉搜索树，否则继续下一步判断

    // 解1：递归判断左右子树是否是二叉搜索树，bstMax 和 bstMin 分别返回二叉树中的最大值和最小值结点
    public boolean isBSTUnefficient(TreeNode<Integer> root) {
        if (root == null) return true;
        if (root.left != null && bstMax(root.left).value >= root.value) return false;
        if (root.right != null && bstMin(root.right).value < root.value) return false;
        return isBSTUnefficient(root.left) && isBSTUnefficient(root.right);
    }
    private TreeNode<Integer> bstMax(TreeNode<Integer> node) {
        if (node == null) return null;
        return bstMaxHelper(node, node);
    }
    private TreeNode<Integer> bstMaxHelper(TreeNode<Integer> node, TreeNode<Integer> max) {
        if (node == null) return max;
        if (node.value < max.value) max = node;
        TreeNode<Integer> leftMax = bstMaxHelper(node.left, max);
        TreeNode<Integer> rightMax = bstMaxHelper(node.right, max);
        return leftMax.value > rightMax.value ? leftMax : rightMax;
    }
    private TreeNode<Integer> bstMin(TreeNode<Integer> node) {
        if (node == null) return null;
        return bstMinHelper(node, node);
    }
    private TreeNode<Integer> bstMinHelper(TreeNode<Integer> node, TreeNode<Integer> min) {
        if (node == null) return min;
        if (node.value > min.value) min = node;
        TreeNode<Integer> leftMin = bstMaxHelper(node.left, min);
        TreeNode<Integer> rightMin = bstMaxHelper(node.right, min);
        return leftMin.value < rightMin.value ? leftMin : rightMin;
    }

    // 解2：一次遍历法
    // 核心思想：存储根节点值，对于左子树，除常规判断外，附加判断右节点值是否小于根节点值，
    // 对于右子树，除常规判断外，附加判断左节点值是否大于根节点值
    public boolean isBSTEfficient(TreeNode<Integer> root) {
        if (root == null) return true;
        return isBSTEfficientLeftHelper(root, root.left, root.value) && isBSTEfficientRightHelper(root, root.right, root.value);
    }
    private boolean isBSTEfficientLeftHelper(TreeNode<Integer> root, TreeNode<Integer> left, int rootValue) {
        if (left == null) return true;
        if (root.value <= left.value) return false;
        if (left.left != null && left.left.value >= left.value) return false;
        if (left.right != null && (left.right.value <= left.value || left.right.value >= rootValue)) return false;
        return isBSTEfficientLeftHelper(left, left.left, rootValue) && isBSTEfficientLeftHelper(left, left.right, rootValue);
    }
    private boolean isBSTEfficientRightHelper(TreeNode<Integer> root, TreeNode<Integer> right, int rootValue) {
        if (right == null) return true;
        if (root.value >= right.value) return false;
        if (right.right != null && right.right.value <= right.value) return false;
        if (right.left != null && (right.left.value >= right.value || right.left.value <= rootValue)) return false;
        return isBSTEfficientRightHelper(right, right.left, rootValue) && isBSTEfficientRightHelper(right, right.right, rootValue);
    }

    // 解3：中序遍历解法 o(2n)
    // 中序遍历来判断BST，将中序遍历的结果存到一个辅助数组，然后判断数组是否有序即可判断是否是BST
    public boolean isBST(TreeNode<Integer> root) {
        if (root == null) return true;
        List<Integer> values = new ArrayList<>();
        isBSTHelper(root, values);
        for (int i = 0; i < values.size() - 1; i++) {
            if (values.get(i + 1) <= values.get(i)) return false;
        }
        return true;
    }
    private void isBSTHelper(TreeNode<Integer> node, List<Integer> values) {
        if (node.left != null) isBSTHelper(node.left, values);
        values.add(node.value);
        if (node.right != null) isBSTHelper(node.right, values);
    }


    // 1.2 判断二叉树是否是完全二叉树
    // 层次遍历
    public boolean isCompleteBTLevelTraversal(TreeNode<Integer> root) {
        if (root == null) return true;
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);
        boolean hasNoChild = false;
        while (!queue.isEmpty()) {
            TreeNode<Integer> tmp = queue.poll();
            if (hasNoChild) {
                if (tmp.left != null || tmp.right != null ) return false;
            }
            if (tmp.left != null) {
                queue.add(tmp.left);
                if (tmp.right == null) {
                    hasNoChild = true;
                } else {
                    queue.add(tmp.right);
                }
            } else {
                hasNoChild = true;
                if (tmp.right != null) return false;
            }
        }
        return true;
    }


    // 1.3 判断平衡二叉树
    // 解1：自顶向下方法
    // 判断一棵二叉树是否是平衡的，对每个结点计算左右子树高度差是否大于1即可，时间复杂度为O(N^2)
    // 解2：自底向上方法: 自底向上来判断左右子树的高度差，这样时间复杂度为 O(N)
    public boolean isBalancedTree(TreeNode root) {
        int[] depth = {0};
        return isBalancedTreeHelper(root, depth);
    }
    private boolean isBalancedTreeHelper(TreeNode root, int[] depth) {
        if(root == null) {
            depth[0] = 0;
            return true;
        }
        // 数组传址参数，用于传递函数调用后的数据，参与运算
        int[] leftDepth = {0};
        int[] rightDepth = {0};
        // 后续遍历左右根，判断每个节点是否是平衡节点，当遍历到一个根节点时，先遍历该根节点的左右子树，计算左右子树的深度并通过传址的方式进行向上传递
        // 如果该根节点是平衡节点，则向上遍历该节点的父亲节点，父亲节点的深度在之前传递的深度基础上加1即可，因此避免了深度计算中的节点重复遍历，提高了效率
        if(isBalancedTreeHelper(root.left, leftDepth) && isBalancedTreeHelper(root.right, rightDepth)) {
            int diffDepth = leftDepth[0] - rightDepth[0];
            if(diffDepth >= - 1 && diffDepth <= 1) {
                int tmpDepth = (leftDepth[0] > rightDepth[0]) ? leftDepth[0] : rightDepth[0];
                depth[0] = 1 + tmpDepth;
                return true;
            }
        }
        return false;
    }

    // 1.4 判断两棵二叉树是否同构
    public boolean isSameStructure(TreeNode<Integer> root1, TreeNode<Integer> root2) {
        if (root1 == null || root2 == null) return root1 == null && root2 == null;
        return root1.value.intValue() == root2.value.intValue()
                && isSameStructure(root1.left, root2.left)
                && isSameStructure(root1.right, root2.right);
    }

    // 2 构建类问题: 主要使用二叉树的两种遍历顺序来确定二叉树的另外一种遍历顺序问题
    //   在没有重复值的二叉树中, 根据先序遍历和后序遍历无法唯一确定一棵二叉树，而根据先序、中序或者中序、后序遍历是可以唯一确定一棵二叉树
    // 2.1 先序和中序遍历构建二叉树
    // 2.2 中序和后序遍历构建二叉树

    // 3 存储类问题
    // 3.1 二叉搜索树存储和恢复
    //  设计一个算法，将一棵二叉搜索树（BST）保存到文件中，需要能够从文件中恢复原来的二叉搜索树，注意算法的时空复杂度
    //          30
    //        /   \
    //      20    40
    //     /      / \
    //   10     35  50
    //
    //  二叉树遍历算法有先序遍历、中序遍历、后序遍历算法等。但是它们中间哪一种能够用于保存BST到文件中并从文件中恢复原来的BST，这是个要考虑的问题
    //  若采用中序遍历，为 10 20 30 35 40 50，下面的结构满足条件，但非原二叉树，故中序遍历不符合要求
    //                 50
    //                /
    //              40
    //              /
    //            35
    //            /
    //          30
    //          /
    //        20
    //        /
    //      10
    //  若采用后序遍历，为 10 20 35 50 40 30，但复原二叉树是个难题，因为构造二叉树需要先构造父结点再插入孩子结点
    //  综上，需采用先序遍历，为 30 20 10 40 35 50
    public void BSTSaver(TreeNode<Integer> root, BufferedWriter bf) throws Exception {
        if (root != null) {
            bf.write(root.value + "\n");
            BSTSaver(root.left, bf);
            BSTSaver(root.right, bf);
        }
    }
//    public TreeNode<Integer> BSTRestore(BufferedReader fp) {
//        TreeNode<Integer> root = null;
//        char[] s = new char[30];
//        char[] buf = new char[30];
//        while ((s = fgets(buf, 30, fp))) {
//            int nodeValue = Integer.parseInt(s);
//            root = bstInsert(root, nodeValue);
//        }
//        return root;
//    }
    // 3.2 二叉树存储和恢复
    //  设计一个算法能够实现二叉树的存储和恢复。
    //  此处针对二叉树，不限于BST，所以不能用前面的方式。不过，我们可以采用先序遍历的思想，只是在这里需要改动。为了能够在重构二叉树时结点能够插入到正确的位置，在使用先序遍历保存二叉树到文件中的时候需要把 NULL 结点也保存起来（可以使用特殊符号如 # 来标识 NULL 结点）。
    //        30
    //      /    \
    //     10    20
    //    /     /  \
    //   50    45  35
    //  如上面这棵二叉树，保存到文件中则为 30 10 50 # # # 20 45 # # 35 # #
    public void btSave(TreeNode<Integer> root, BufferedWriter fp) throws IOException {
        if (root == null) {
            fp.write("#\n");
        } else {
            fp.write(root.value);
            btSave(root.left, fp);
            btSave(root.right, fp);
        }
    }
    //BTNode *btRestore(BTNode *root, FILE *fp){    char buf[30];    char *s = fgets(buf, 30, fp);    if (!s || strcmp(s, "#\n") == 0)        return NULL;     int nodeValue = atoi(s);    root = btNewNode(nodeValue);    root->left = btRestore(root->left, fp);    root->right = btRestore(root->right, fp);    return root;}

    // 4 查找类问题
    //  查找类问题主要包括：查找二叉树/二叉搜索树的最低公共祖先结点，或者是二叉树中的最大的子树且该子树为二叉搜索树
    //
    // 4.1 二叉搜索树最低公共祖先结点
    // 4.2 二叉树(不一定是BST）最低公共祖先结点
    // 解1：自顶向下方法，时间复杂度 o(N^2)
    // 解2：自底向上方法，时间复杂度为O(N)
    // 自底向上遍历节点，一旦遇到节点等于 p 或者 q，则将其向上传递给它的父节点，父结点会判断它的左右子树是否都包含其中一个结点，
    // 如果是，则父结点一定是这两个结点 p 和 q 的 LCA。
    // 如果不是，我们向上传递其中的包含结点 p 或者 q 的子结点，或者 NULL(如果左右子树都没有结点p或q)
    public TreeNode<Integer> btLCADown2Top(TreeNode<Integer> root, TreeNode<Integer> p, TreeNode<Integer> q) {
        if (root == null) return null;
        if (root == p || root == q) return root;
        TreeNode<Integer> left = btLCADown2Top(root.left, p, q);
        TreeNode<Integer> right = btLCADown2Top(root.right, p, q);
        if (left != null && right != null) return root;  // 如果p和q位于不同的子树
        return left != null ? left: right;  // p和q在相同的子树，或者p和q不在子树中
    }
    // 4.3 二叉树的最大二叉搜索子树: 找出二叉树中最大的子树，该子树为二叉搜索树, 所谓最大的子树就是指结点数目最多的子树
    // 解1：自顶向下解法 - 时间复杂度为 o(N^2)
    // 解2：自底向上解法
    //  在判断上面结点为根的子树是否是BST之前已经知道底部结点为根的子树是否是BST，因此只要以底部结点为根的子树不是BST，则以它上面结点为根的子树一定不是BST。
    //  我们可以记录子树包含的结点数目，然后跟父结点所在的二叉树比较，来求得最大BST子树。
    //  查找二叉树最大的二叉搜索子树-自底向上方法
    // BTNode *largestSubBSTDown2Top(BTNode *root, int *bstSize){    BTNode *largestBST = NULL;    int min, max, maxNodes=0;    findLargestSubBST(root, &min, &max, &maxNodes, &largestBST);    *bstSize = maxNodes;    return largestBST;}/** * 查找最大二叉搜索子树自底向上方法主体函数 * 如果是BST，则返回BST的结点数目，否则返回-1 */int findLargestSubBST(BTNode *root, int *min, int *max, int *maxNodes, BTNode **largestSubBST){    if (!root) return 0;    int isBST = 1;    int leftNodes = findLargestSubBST(root->left, min, max, maxNodes, largestSubBST);    int currMin = (leftNodes == 0) ? root->value : *min;    if (leftNodes == -1 || (leftNodes != 0 && root->value <= *max))        isBST = 0;    int rightNodes = findLargestSubBST(root->right, min, max, maxNodes, largestSubBST);    int currMax = (rightNodes == 0) ? root->value : *max;    if (rightNodes == -1 || (rightNodes != 0 && root->value > *min))        isBST = 0;    if (!isBST)        return -1;    *min = currMin;    *max = currMax;    int totalNodes = leftNodes + rightNodes + 1;    if (totalNodes > *maxNodes) {        *maxNodes = totalNodes;        *largestSubBST = root;    }    return totalNodes;}

    // 5 距离类问题
    // 5.1 二叉树两个结点之间的最短距离: 已知二叉树中两个结点，求这两个结点之间的最短距离（注：最短距离是指从一个结点到另一个结点需要经过的边的条数）。
    //  两个结点的距离比较好办，先求出两个结点的最低公共祖先结点（LCA），然后计算 LCA 到两个结点的距离之和即可，时间复杂度 O(N) 。
    //  计算二叉树两个结点最短距离 */int distanceOf2BTNodes(BTNode *root, BTNode *p, BTNode *q){    if (!root) return 0;    BTNode *lca = btLCADown2Top(root, p, q);    int d1 = btDistanceFromRoot(lca, p, 0);    int d2 = btDistanceFromRoot(lca, q, 0);    return d1+d2;}/** * 计算二叉树结点node和root的距离 */int btDistanceFromRoot(BTNode *root, BTNode *node, int level){    if (!root) return -1;    if (root == node) return level;    int left = btDistanceFromRoot(root->left, node, level+1);    if (left == -1)        return btDistanceFromRoot(root->right, node, level+1);    return left;}
    // 5.2 二叉搜索树两个结点的最短距离: 求一棵二叉搜索树中的两个结点的最短距离
    // int distanceOf2BSTNodes(BTNode *root, BTNode *p, BTNode *q){    if (!root) return 0;    if (root->value > p->value && root->value > q->value) {        return distanceOf2BSTNodes(root->left, p, q);    } else if(root->value <= p->value && root->value <= q->value){        return distanceOf2BSTNodes(root->right, p, q);    } else {        return bstDistanceFromRoot(root, p) + bstDistanceFromRoot(root, q);    }}/** * 计算BST结点node和root的距离 */int bstDistanceFromRoot(BTNode *root, BTNode *node){    if (root->value == node->value)        return 0;    else if (root->value > node->value)        return 1 + bstDistanceFromRoot(root->left, node);    else        return 1 + bstDistanceFromRoot(root->right, node);}
    // 5.3 二叉树中结点的最大距离: 求一棵二叉树中相距最远的两个结点之间的距离
    // 5.4 二叉树最大宽度
    // 解1：层序遍历法
    // 解2：先序遍历法
    //  先创建一个大小为二叉树高度 h 的辅助数组来存储每一层的宽度，初始化为 0。通过先序遍历的方式来遍历二叉树，并设置好每层的宽度。最后，从这个辅助数组中求最大值即是二叉树最大宽度
    //  int btMaxWidthPreOrder(BTNode *root){    int h = btHeight(root);    int *count = (int *)calloc(sizeof(int), h);    btLevelWidthCount(root, 0, count);    int i, maxWidth = 0;    for (i = 0; i < h; i++) {        if (count[i] > maxWidth)            maxWidth = count[i];    }    return maxWidth;}/** * 计算二叉树从 level 开始的每层宽度，并存储到数组 count 中。 */void btLevelWidthCount(BTNode *root, int level, int count[]){    if (!root) return;    count[level]++;    btLevelWidthCount(root->left, level+1, count);    btLevelWidthCount(root->right, level+1, count);}

    // 6 混合类问题
    // 6.1 根据有序数组构建平衡二叉搜索树
    //  给定一个有序数组，数组元素升序排列，试根据该数组元素构建一棵平衡二叉搜索树
    //              3
    //           /    \
    //          1      5
    //           \    / \
    //            2  4   6
    //解：如果要从一个有序数组中选择一个元素作为根结点，应该选择哪个元素呢？我们应该选择有序数组的中间元素作为根结点。选择了中间元素作为根结点并创建后，剩下的元素分为两部分，可以看作是两个数组。这样剩下的元素在根结点左边的作为左子树，右边的作为右子树。
    //
    //BTNode *sortedArray2BST(int a[], int start, int end){    if (start > end) return NULL;    int mid = start + (end-start)/2;    BTNode *root = btNewNode(a[mid]);    root->left = sortedArray2BST(a, start, mid-1);    root->right = sortedArray2BST(a, mid+1, end);    return root;}

    // 6.2 有序单向链表构建平衡二叉搜索树: 给定一个有序的单向链表，构建一棵平衡二叉搜索树。
    //
    //解：最自然的想法是先将链表中的结点的值保存在数组中，然后采用 6.1 中方法实现，时间复杂度为 O(N)。我们还可以采用自底向上的方法，在这里我们不再需要每次查找中间元素。
    //
    //下面代码依旧需要链表长度作为参数，计算链表长度时间复杂度为O(N)，算法时间复杂度也为O(N)，所以总的时间复杂度为O(N)。
    //
    //代码中需要注意的是每次调用 sortedList2BST 函数时，list 位置都会变化，调用完函数后 list 总是指向 mid+1 的位置 （如果满足返回条件，则 list 位置不变）。
    //
    //BTNode *sortedList2BST(ListNode **pList, int start, int end){    if (start > end) return NULL;    int mid = start + (end-start)/2;    BTNode *left = sortedList2BST(pList, start, mid-1);    BTNode *parent = btNewNode((*pList)->value);    parent->left = left;    *pList = (*pList)->next;    parent->right = sortedList2BST(pList, mid+1, end);    return parent;}
    //例如链表只有2个节点 3->5->NULL，则初始 start=0, end=1, mid=0，继而递归调用 sortedList2BST(pList, start,mid-1)，此时直接返回 NULL。即左孩子为NULL， 根结点为 3，而后链表指向 5，再调用 sortedList2BST(pList, mid+1, end)，而这次调用返回结点 5，将其赋给根结点 3 的右孩子。这次调用的 mid=1，调用完成后 list 已经指向链表末尾。
    //
    // 6.3 二叉搜索树转换为有序循环链表 : 给定一棵二叉搜索树(BST)，将其转换为双向的有序循环链表。
}
