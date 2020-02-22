package solution;

import data.structure.TreeNode;

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
    // 以前面提到的 binary tree(1) 为例，当我们遍历到结点 15 时，我们知道右子树结点值肯定都 >=10。当我们遍历到结点 15 的左孩子结点 6 时，我们知道结点 15 的左子树结点值都必须在 10 到 15 之间。显然，结点 6 不符合条件，因此它不是一棵二叉搜索树。
    public boolean isBSTEfficient(TreeNode<Integer> root, TreeNode<Integer> left, TreeNode<Integer> right) {
        if (root == null) return true;
        if (left != null && root.value <= left.value) return false;
        if (right != null && root.value > right.value) return false;
        return isBSTEfficient(root.left, left, root) && isBSTEfficient(root.right, root, right);
    }

    // 解3：中序遍历解法
    // 中序遍历来判断BST，将中序遍历的结果存到一个辅助数组，然后判断数组是否有序即可判断是否是BST
    // 也可以不用辅助数组，在遍历时通过保留前一个指针 prev，据此来实现判断BST的解法，初始时 prev = NULL
    public boolean isBSTInOrder(TreeNode<Integer> root) {
        TreeNode<Integer> prev = null;
        return isBSTInOrder(root, prev);
    }
    private boolean isBSTInOrder(TreeNode<Integer> root, TreeNode<Integer> prev) {
        if (root == null) return true;
        if (!isBSTInOrder(root.left, prev)) return false;
        if (prev != null && root.value < prev.value) return false;
        return isBSTInOrder(root.right, root);
    }


    // 1.2 判断二叉树是否是完全二叉树
    // 解1：常规解法-中序遍历
    // 定义满结点：即一个结点存在左右孩子结点，则该结点为满结点。在代码中定义变量 flag 来标识是否发现非满结点，为1表示该二叉树存在非满结点。完全二叉树如果存在非满结点，则根据层序遍历队列中剩下结点必须是叶子结点，且如果一个结点的左孩子为空，则右孩子结点也必须为空。
    // 解2：更简单的方法: 判断结点序号法
    // 更简单的方法是判断结点序号法，因为完全二叉树的结点序号都是有规律的，如结点 i 的左右子结点序号为 2i+1 和 2i+2，如根结点序号是 0，它的左右子结点序号是 1 和 2(如果都存在的话)
    // 我们可以计算二叉树的结点数目，然后依次判断所有结点的序号，如果不是完全二叉树，那肯定会存在结点它的序号大于等于结点数目的。如前面提到的 binary tree(1) 就不是完全二叉树
    public boolean isCompleteBTIndexMethod(TreeNode<Integer> root, int index, int nodeCount) {
        if (root != null) return true;
        if (index >= nodeCount) return false;
        return isCompleteBTIndexMethod(root.left, 2 * index + 1, nodeCount)
                && isCompleteBTIndexMethod(root.right, 2 * index + 2, nodeCount);
    }


    // 1.3 判断平衡二叉树
    // 解1：自顶向下方法
    // 判断一棵二叉树是否是平衡的，对每个结点计算左右子树高度差是否大于1即可，时间复杂度为O(N^2)
    // 解2：自底向上方法
    // 因为解1会重复的遍历很多结点，为此我们可以采用类似后序遍历的方式，自底向上来判断左右子树的高度差，这样时间复杂度为 O(N)
    public boolean isBalanceBTDown2Top(TreeNode<Integer> root, int height) {
        if (root != null) {
            height = 0;
            return true;
        }
        int leftHeight = 0, rightHeight = 0;
        if (isBalanceBTDown2Top(root.left, leftHeight) && isBalanceBTDown2Top(root.right, rightHeight)) {
            int diff = Math.abs(leftHeight - rightHeight);
            return diff <= 1;
        }
        return false;
    }

    // 1.4 判断两棵二叉树是否同构

    // 2 构建类问题: 主要使用二叉树的两种遍历顺序来确定二叉树的另外一种遍历顺序问题
    //   在没有重复值的二叉树中, 根据先序遍历和后序遍历无法唯一确定一棵二叉树，而根据先序、中序或者中序、后序遍历是可以唯一确定一棵二叉树
    // 2.1 先序和中序遍历构建二叉树
    // 2.2 中序和后序遍历构建二叉树

    // 3 存储类问题
    // 3.1 二叉搜索树存储和恢复
    //  设计一个算法，将一棵二叉搜索树（BST）保存到文件中，需要能够从文件中恢复原来的二叉搜索树，注意算法的时空复杂度。
    //
    //      30     /   \      20    40  /      / \ 10    35  50
    //思路
    //
    //二叉树遍历算法有先序遍历、中序遍历、后序遍历算法等。但是它们中间哪一种能够用于保存BST到文件中并从文件中恢复原来的BST，这是个要考虑的问题。
    //
    //假定用中序遍历，因为这棵BST的中序遍历为 10 20 30 35 40 50，可能的结构是下面这样，因此 中序遍历不符合要求 。
    //
    //         50         /              40        /         35     /    30   /  20 /10
    //既然中序遍历不行，后序遍历如何？后序遍历该BST可以得到：10 20 35 50 40 30 。读取这些结点并构造出原来的BST是个难题，因为在构造二叉树时是先构造父结点再插入孩子结点，而后序遍历序列是先读取到孩子结点然后才是父结点，所以 后续遍历也不符合条件 。
    //
    //综合看来，只有先序遍历满足条件 。该BST的先序遍历是 30 20 10 40 35 50 。我们观察到重要的一点就是：一个结点的父亲结点总是在该结点之前输出 。有了这个观察，我们从文件中读取BST结点序列后，总是可以在构造孩子结点之前构造它们的父结点。将BST写入到文件的代码跟先序遍历一样。
    //
    //那么读取恢复怎么做呢？使用二叉搜索树 bstInsert() 方法执行 N 次插入操作即可，如果二叉搜索树平衡的话每次插入需要时间 O(lgN)，共需要 O(NlgN) 的时间，而最坏情况下为 O(N^2)。
    //
    ///** * 存储二叉树到文件中-使用先序遍历 */void bstSave(BTNode *root, FILE *fp){    if (!root) return;    char temp[30];    sprintf(temp, "%d\n", root->value);    fputs(temp, fp);    bstSave(root->left, fp);    bstSave(root->right, fp);}/** * 从文件中恢复二叉树 */BTNode *bstRestore(FILE *fp){    BTNode *root = NULL;    char *s;    char buf[30];    while ((s = fgets(buf, 30, fp))) {        int nodeValue = atoi(s);        root = bstInsert(root, nodeValue);    }    return root;}
    //3.2 二叉树存储和恢复
    //题：设计一个算法能够实现二叉树（注意，不是二叉搜索树BST）存储和恢复。
    //
    //解：3.1节提到过使用先序遍历可以保存和恢复二叉搜索树，而这个题目是针对二叉树，并不是BST，所以不能用前面的方式。不过，我们可以采用先序遍历的思想，只是在这里需要改动。为了能够在重构二叉树时结点能够插入到正确的位置，在使用先序遍历保存二叉树到文件中的时候需要把 NULL 结点也保存起来（可以使用特殊符号如 # 来标识 NULL 结点）。
    //
    //注意：本题采用 # 保存 NULL 结点的方法存在缺陷，如本方法中二叉树结点值就不能是 #。如果要能保存各种字符，则需要采用其他方法来实现了。
    //
    //     30   /    \     10    20 /     /  \50    45  35
    //如上面这棵二叉树，保存到文件中则为 30 10 50 # # # 20 45 # # 35 # #。于是，保存和恢复实现的代码如下：
    //
    ///** * 存储二叉树到文件中 */void btSave(BTNode *root, FILE *fp){    if (!root) {        fputs("#\n", fp);    } else {        char temp[30];        sprintf(temp, "%d\n", root->value);        fputs(temp, fp);        btSave(root->left, fp);        btSave(root->right, fp);    }}/** * 从文件恢复二叉树 */BTNode *btRestore(BTNode *root, FILE *fp){    char buf[30];    char *s = fgets(buf, 30, fp);    if (!s || strcmp(s, "#\n") == 0)        return NULL;     int nodeValue = atoi(s);    root = btNewNode(nodeValue);    root->left = btRestore(root->left, fp);    root->right = btRestore(root->right, fp);    return root;}
    //4 查找类问题
    //查找类问题主要包括：查找二叉树/二叉搜索树的最低公共祖先结点，或者是二叉树中的最大的子树且该子树为二叉搜索树等。
    //
    //4.1 二叉搜索树最低公共祖先结点
    //题：给定一棵二叉搜索树(BST)，找出树中两个结点的最低公共祖先结点(LCA)。如下面这棵二叉树结点 2 和 结点 8 的 LCA 是 6，而结点 4 和 结点 2 的 LCA 是 2。
    //
    //        ______6______       /              \    __2__            __8__   /     \          /      \   0      4         7       9         /  \         3   5
    //解：我们从顶往下遍历二叉搜索树时，对每个遍历到的结点，待求LCA的两个结点可能有如下四种分布情况：
    //
    //两个结点都在树的左子树中：LCA一定在当前遍历结点的左子树中。
    //两个结点都在树的右子树中：LCA一定在当前遍历结点右子树中。
    //一个结点在树的左边，一个结点在树的右边：LCA就是当前遍历的结点。
    //当前结点等于这两个结点中的一个：LCA也是当前遍历的结点。
    //BTNode *bstLCA(BTNode *root, BTNode *p, BTNode *q){    if (!root || !p || !q) return NULL;    int maxValue = p->value >= q->value ? p->value : q->value;    int minValue = p->value < q->value ? p->value : q->value;    if (maxValue < root->value) {        return bstLCA(root->left, p, q);    } else if (minValue > root->value) {        return bstLCA(root->right, p, q);    } else {        return root;    }}
    //4.2 二叉树(不一定是BST）最低公共祖先结点
    //题：给定二叉树中的两个结点，输出这两个结点的最低公共祖先结点（LCA）。注意，该二叉树不一定是二叉搜索树。
    //
    //        _______3______       /              \    ___5__          ___1__   /      \        /      \   6       2       0       8         /  \         7   4
    //解1：自顶向下方法
    //
    //因为不一定是BST，所以不能根据值大小来判断，不过总体思路是一样的：我们可以从根结点出发，判断当前结点的左右子树是否包含这两个结点。
    //
    //如果左子树包含两个结点，则它们的最低公共祖先结点也一定在左子树中。
    //如果右子树包含两个结点，则它们的最低公共祖先结点也一定在右子树中。
    //如果一个结点在左子树，而另一个结点在右子树中，则当前结点就是它们的最低公共祖先结点。
    //因为对每个结点都要重复判断结点 p 和 q 的位置，总的时间复杂度为 O(N^2)，为此，我们可以考虑找一个效率更高的方法。
    //
    ///** * 二叉树最低公共祖先结点-自顶向下解法 O(N^2) */BTNode *btLCATop2Down(BTNode *root, BTNode *p, BTNode *q){    if (!root || !p || !q) return NULL;    if (btExist(root->left, p) && btExist(root->left, q)) {        return btLCATop2Down(root->left, p, q);    } else if (btExist(root->right, p) && btExist(root->right, q)) {        return btLCATop2Down(root->right, p, q);    } else {        return root;    }}/** * 二叉树结点存在性判断 */int btExist(BTNode *root, BTNode *node){    if (!root) return 0;    if (root == node) return 1;    return btExist(root->left, node) || btExist(root->right, node);}
    //解2：自底向上方法
    //
    //因为自顶向下方法有很多重复的判断，于是有了这个自底向上的方法。自底向上遍历结点，一旦遇到结点等于p 或者 q，则将其向上传递给它的父结点。
    //
    //父结点会判断它的左右子树是否都包含其中一个结点，如果是，则父结点一定是这两个结点 p 和 q 的 LCA。如果不是，我们向上传递其中的包含结点 p 或者 q 的子结点，或者 NULL(如果左右子树都没有结点p或q)。该方法时间复杂度为O(N)。
    //
    ///** * 二叉树最低公共祖先结点-自底向上解法 O(N) */BTNode *btLCADown2Top(BTNode *root, BTNode *p, BTNode *q){    if (!root) return NULL;    if (root == p || root == q) return root;    BTNode *left = btLCADown2Top(root->left, p, q);    BTNode *right = btLCADown2Top(root->right, p, q);    if (left && right)        return root;  // 如果p和q位于不同的子树      return left ? left: right;  //p和q在相同的子树，或者p和q不在子树中}
    //4.3 二叉树的最大二叉搜索子树
    //题：找出二叉树中最大的子树，该子树为二叉搜索树。所谓最大的子树就是指结点数目最多的子树。
    //
    //         ___10___        /         \      _5_         15     /   \          \     1    8          7          ___10____        /         \      _5_         15     -------- subtree (1)     /    \     1     8       _5_     /   \               -------- subtree (2)    1     8
    //根据维基百科对 子树 的定义，一棵二叉树T的子树由T的某个结点和该结点所有的后代构成。也就是说，该题目中，subtree(2) 才是正确的答案，因为 subtree(1) 不包含结点7，不满足子树的定义。
    //
    //解1：自顶向下解法
    //
    //最自然的解法是以根结点开始遍历二叉树所有的结点，判定以当前结点为根的子树是否是BST，如果是，则该结点为根的BST就是最大的BST。如果不是，递归调用左右子树，返回其中包含较多结点的子树。
    //
    ///** * 查找二叉树最大的二叉搜索子树-自顶向下方法 */BTNode *largestSubBSTTop2Down(BTNode *root, int *bstSize){    if (!root) {        *bstSize = 0;        return NULL;    }    if (isBSTEfficient(root, NULL, NULL)) { //以root为根结点的树为BST，则设置结果为root并返回。        *bstSize = btSize(root);        return root;    }    int lmax, rmax;    BTNode *leftBST = largestSubBSTTop2Down(root->left, &lmax);   //找出左子树中为BST的最大的子树    BTNode *rightBST = largestSubBSTTop2Down(root->right, &rmax);  //找出右子树中为BST的最大的子树    *bstSize = lmax > rmax ? lmax : rmax;      //设定结点最大数目    BTNode *result = lmax > rmax ? leftBST : rightBST;    return result;}
    //解2：自底向上解法
    //
    //自顶向下的解法时间复杂度为 O(N^2)，每个结点都要判断是否满足BST的条件，可以用从底向上方法优化。
    //
    //我们在判断上面结点为根的子树是否是BST之前已经知道底部结点为根的子树是否是BST，因此只要以底部结点为根的子树不是BST，则以它上面结点为根的子树一定不是BST。我们可以记录子树包含的结点数目，然后跟父结点所在的二叉树比较，来求得最大BST子树。
    //
    ///** * 查找二叉树最大的二叉搜索子树-自底向上方法 */BTNode *largestSubBSTDown2Top(BTNode *root, int *bstSize){    BTNode *largestBST = NULL;    int min, max, maxNodes=0;    findLargestSubBST(root, &min, &max, &maxNodes, &largestBST);    *bstSize = maxNodes;    return largestBST;}/** * 查找最大二叉搜索子树自底向上方法主体函数 * 如果是BST，则返回BST的结点数目，否则返回-1 */int findLargestSubBST(BTNode *root, int *min, int *max, int *maxNodes, BTNode **largestSubBST){    if (!root) return 0;    int isBST = 1;    int leftNodes = findLargestSubBST(root->left, min, max, maxNodes, largestSubBST);    int currMin = (leftNodes == 0) ? root->value : *min;    if (leftNodes == -1 || (leftNodes != 0 && root->value <= *max))        isBST = 0;    int rightNodes = findLargestSubBST(root->right, min, max, maxNodes, largestSubBST);    int currMax = (rightNodes == 0) ? root->value : *max;    if (rightNodes == -1 || (rightNodes != 0 && root->value > *min))        isBST = 0;    if (!isBST)        return -1;    *min = currMin;    *max = currMax;    int totalNodes = leftNodes + rightNodes + 1;    if (totalNodes > *maxNodes) {        *maxNodes = totalNodes;        *largestSubBST = root;    }    return totalNodes;}
    //5 距离类问题
    //5.1 二叉树两个结点之间的最短距离
    //题：已知二叉树中两个结点，求这两个结点之间的最短距离（注：最短距离是指从一个结点到另一个结点需要经过的边的条数）。
    //
    //         ___1___        /        \       2          3     /   \       /  \    4     5     6    7                 \                  8Distance(4, 5) = 2Distance(4, 6) = 4Distance(3, 4) = 3Distance(2, 4) = 1Distance(8, 5) = 5
    //解：两个结点的距离比较好办，先求出两个结点的最低公共祖先结点（LCA），然后计算 LCA 到两个结点的距离之和即可，时间复杂度 O(N) 。
    //
    ///** * 计算二叉树两个结点最短距离 */int distanceOf2BTNodes(BTNode *root, BTNode *p, BTNode *q){    if (!root) return 0;    BTNode *lca = btLCADown2Top(root, p, q);    int d1 = btDistanceFromRoot(lca, p, 0);    int d2 = btDistanceFromRoot(lca, q, 0);    return d1+d2;}/** * 计算二叉树结点node和root的距离 */int btDistanceFromRoot(BTNode *root, BTNode *node, int level){    if (!root) return -1;    if (root == node) return level;    int left = btDistanceFromRoot(root->left, node, level+1);    if (left == -1)        return btDistanceFromRoot(root->right, node, level+1);    return left;}
    //5.2 二叉搜索树两个结点的最短距离
    //题：求一棵二叉搜索树中的两个结点的最短距离。
    //
    //解：与前面不同的是，这是一棵BST，那么我们可以使用二叉搜索树的特点来简化距离计算流程，当然直接用 5.1 的方法是完全OK的，因为它是通用的计算方法。
    //
    ///** * 计算BST两个结点最短距离。 */int distanceOf2BSTNodes(BTNode *root, BTNode *p, BTNode *q){    if (!root) return 0;    if (root->value > p->value && root->value > q->value) {        return distanceOf2BSTNodes(root->left, p, q);    } else if(root->value <= p->value && root->value <= q->value){        return distanceOf2BSTNodes(root->right, p, q);    } else {        return bstDistanceFromRoot(root, p) + bstDistanceFromRoot(root, q);    }}/** * 计算BST结点node和root的距离 */int bstDistanceFromRoot(BTNode *root, BTNode *node){    if (root->value == node->value)        return 0;    else if (root->value > node->value)        return 1 + bstDistanceFromRoot(root->left, node);    else        return 1 + bstDistanceFromRoot(root->right, node);}
    //5.3 二叉树中结点的最大距离
    //题：写一个程序求一棵二叉树中相距最远的两个结点之间的距离。
    //
    //解：《编程之美》上有这道题，这题跟前面不同，要求相距最远的两个结点的距离，而且并没有指定两个结点位置。计算一个二叉树的最大距离有两个情况:
    //
    //路径为 左子树的最深节点 -> 根节点 -> 右子树的最深节点。
    //路径不穿过根节点，而是左子树或右子树的最大距离路径，取其大者。
    //         ___10___        /         \      _5_         15      ------ 第1种情况     /   \          \    1     8          7         10        /                5             /   \                ------ 第2种情况    1     8       /       \  2         3
    //我们定义函数 maxDistanceOfBT(BTNode *root) 用于计算二叉树相距最远的两个结点的距离，可以递归的先计算左右子树的最远结点距离，然后比较左子树最远距离、右子树最远距离以及左右子树最大深度之和，从而求出整个二叉树的相距最远的两个结点的距离。
    //
    //int btMaxDistance(BTNode *root, int *maxDepth){    if (!root) {        *maxDepth = 0;        return 0;    }    int leftMaxDepth, rightMaxDepth;    int leftMaxDistance = btMaxDistance(root->left, &leftMaxDepth);    int rightMaxDistance = btMaxDistance(root->right, &rightMaxDepth);    *maxDepth = max(leftMaxDepth+1, rightMaxDepth+1);    int maxDistance = max3(leftMaxDistance, rightMaxDistance, leftMaxDepth+rightMaxDepth); // max求两个数最大值，max3求三个数最大值，详见代码    return maxDistance;}
    //5.4 二叉树最大宽度
    //题：给定一棵二叉树，求该二叉树的最大宽度。二叉树的宽度指的是每一层的结点数目。如下面这棵二叉树，从上往下1-4层的宽度分别是 1，2，3，2，于是它的最大宽度为3。
    //
    //         1        /  \       2    3     /  \     \    4    5     8               /  \             6    7
    //解1：层序遍历法
    //
    //最容易想到的方法就是使用层序遍历，然后计算每一层的结点数，然后得出最大结点数。该方法时间复杂度为 O(N^2)。当然如果优化为使用队列来实现层序遍历，可以得到 O(N) 的时间复杂度。
    //
    ///** * 二叉树最大宽度 */int btMaxWidth(BTNode *root){    int h = btHeight(root);    int level, width;    int maxWidth = 0;    for (level = 1; level <= h; level++) {        width = btLevelWidth(root, level);        if (width > maxWidth)            maxWidth = width;    }    return maxWidth;}/** * 二叉树第level层的宽度 */int btLevelWidth(BTNode *root, int level){    if (!root) return 0;    if (level == 1) return 1;    return btLevelWidth(root->left, level-1) + btLevelWidth(root->right, level-1);}
    //解2：先序遍历法
    //
    //我们可以先创建一个大小为二叉树高度 h 的辅助数组来存储每一层的宽度，初始化为0。通过先序遍历的方式来遍历二叉树，并设置好每层的宽度。最后，从这个辅助数组中求最大值即是二叉树最大宽度。
    //
    ///** * 二叉树最大宽度-先序遍历法 */int btMaxWidthPreOrder(BTNode *root){    int h = btHeight(root);    int *count = (int *)calloc(sizeof(int), h);    btLevelWidthCount(root, 0, count);    int i, maxWidth = 0;    for (i = 0; i < h; i++) {        if (count[i] > maxWidth)            maxWidth = count[i];    }    return maxWidth;}/** * 计算二叉树从 level 开始的每层宽度，并存储到数组 count 中。 */void btLevelWidthCount(BTNode *root, int level, int count[]){    if (!root) return;    count[level]++;    btLevelWidthCount(root->left, level+1, count);    btLevelWidthCount(root->right, level+1, count);}
    //6 混合类问题
    //此类问题主要考察二叉树和链表/数组等结合，形式偏新颖。
    //
    //6.1 根据有序数组构建平衡二叉搜索树
    //题：给定一个有序数组，数组元素升序排列，试根据该数组元素构建一棵平衡二叉搜索树（Balanced Binary Search Tree）。所谓平衡的定义，就是指二叉树的子树高度之差不能超过1。
    //
    //         __3__        /     \       1       5       ---- 平衡二叉搜索树示例        \     / \         2   4   6
    //解：如果要从一个有序数组中选择一个元素作为根结点，应该选择哪个元素呢？我们应该选择有序数组的中间元素作为根结点。选择了中间元素作为根结点并创建后，剩下的元素分为两部分，可以看作是两个数组。这样剩下的元素在根结点左边的作为左子树，右边的作为右子树。
    //
    //BTNode *sortedArray2BST(int a[], int start, int end){    if (start > end) return NULL;    int mid = start + (end-start)/2;    BTNode *root = btNewNode(a[mid]);    root->left = sortedArray2BST(a, start, mid-1);    root->right = sortedArray2BST(a, mid+1, end);    return root;}
    //6.2 有序单向链表构建平衡二叉搜索树
    //题：给定一个有序的单向链表，构建一棵平衡二叉搜索树。
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
    //6.3 二叉搜索树转换为有序循环链表
    //题：给定一棵二叉搜索树(BST)，将其转换为双向的有序循环链表。
    //
    //6大类二叉树面试题汇总解答
    //解：如图所示，需要将 BST 的左右孩子指针替换成链表的 prev 和 next 指针，分别指向双向链表的前一个和后一个结点。相信大多数人第一反应就是中序遍历这棵二叉树，同时改变树中结点的 left 和 right 指针。这里需要额外考虑的是如何将最后一个结点的right 指针指向第一个结点，如下图所展示的那样。
    //
    //6大类二叉树面试题汇总解答
    //以中序遍历遍历一棵二叉树的时候，每遍历到一个结点，我们就可以修改该结点的left指针指向前一个遍历到的结点，因为在后续操作中我们不会再用到 left 指针；与此同时，我们还需要修改前一个遍历结点的 right 指针，让前一个遍历结点的 right 指针指向当前结点。
    //
    //比如我们遍历到结点2，则我们修改结点2的 left 指针指向结点1，同时需要修改结点1的 right 指针指向结点2。需要注意一点，这里的前一个遍历结点不是当前结点的父结点，而是当前结点的前一个比它小的结点。
    //
    //看似问题已经解决，慢着，我们其实落下了重要的两步。1）我们没有对头结点head赋值。2）最后一个结点的right指针没有指向第一个结点。
    //
    //解决这两个问题的方案非常简单：在每次递归调用的时候，更新当前遍历结点的 right 指针让其指向头结点 head，同时更新头结点 head 的 left 指针让其指向当前遍历结点。当递归调用结束的时候，链表的头尾结点会指向正确的位置。不要忘记只有一个结点的特殊情况，它的 left 和 right 指针都是指向自己。
    //
    //6大类二叉树面试题汇总解答
    //void bt2DoublyList(BTNode *node, BTNode **pPrev, BTNode **pHead){    if (!node) return;    bt2DoublyList(node->left, pPrev, pHead);    // 当前结点的left指向前一个结点pPrev    node->left = *pPrev;    if (*pPrev)        (*pPrev)->right = node;  // 前一个结点的right指向当前结点    else        *pHead = node; // 如果前面没有结点，则设置head为当前结点（当前结点为最小的结点）。    // 递归结束后，head的left指针指向最后一个结点，最后一个结点的右指针指向head结点。    // 注意保存当前结点的right指针，因为在后面代码中会修改该指针。    BTNode *right = node->right;    (*pHead)->left = node;    node->right = (*pHead);    *pPrev = node;//更新前一个结点    bt2DoublyList(right, pPrev, pHead); }
    //这个解法非常的精巧，因为该算法是对中序遍历的一个改进，因此它的时间复杂度为O(N)，N为结点数目。当然，相比中序遍历，我们在每次递归调用过程中增加了额外的赋值操作。

}
