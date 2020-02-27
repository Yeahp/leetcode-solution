package solution;

import data.structure.ListNode;
import data.structure.TreeNode;

import java.util.Deque;
import java.util.Stack;

public class LinkedListPractice {

    // 1. 如何在一次传递中找到单链表的中间元素？
    // 假设：若链表长度为奇数，则返回正中间元素；若链表长度为偶数，则返回中间靠前的元素
    // 核心思想：设置快、慢指针，慢指针一次前进一步，慢指针一次前进两步
    public ListNode<Integer> findMiddleElementOfLinkedList(ListNode<Integer> head) {
        if (head == null) return null;
        ListNode<Integer> pre = head;
        ListNode<Integer> next = head;
        if (next.next != null) next = next.next;
        else return pre;
        while (true) {
            if (next.next != null) {
                next = next.next;
            } else {
                break;
            }
            if (next.next != null) {
                next = next.next;
                pre = pre.next;
            } else {
                break;
            }
        }
        return pre;
    }

    // 2. 如何在不使用递归的情况下反转单链表？ DONE 参见9

    // 3. 如何删除一个未排序链表中的重复节点？ DONE
    // 核心思想：用额外空间存储已经存在的节点值，每次便利到新节点时，查表
    // 若表中不存在该节点值，则在表中新建该值，继续遍历
    // 若表中已存在该节点值，则删除该节点 删除方法参见 10

    // 4. 如何找出一个单链表的长度？ DONE

    // 5. 如何查找链表是否包含循环？如何找出循环开始节点？
    // 核心思想：
    //   是否包含循环 - 设置快、慢指针，快指针速度是慢指针的两倍
    //   循环起点 - 计算出环的长度S，然后从头遍历，让快指针先走S步
    //   约定：（1）链表不为空；（2）若无环，返回空，若有环，返回环的起点
    public ListNode<Integer> circularCheck(ListNode<Integer> head) {
        ListNode<Integer> fast = new ListNode<>(null, head);
        ListNode<Integer> slow = new ListNode<>(null, head);
        boolean circle = false;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                circle = true;
                break;
            }
        }
        if (circle) {
            int cnt = 1;
            fast = fast.next;
            while (fast != slow) {
                fast = fast.next.next;
                slow = slow.next;
                cnt++;
            }
            fast = head;
            slow = head;
            for (int i = 0; i < cnt; i++) {
                fast = fast.next;
            }
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        } else {
            return null;
        }
    }

    // 6. 如何反转链表？ DONE 参见9

    // 7. 如何找到单链表中的倒数第三个节点？
    // 核心思想：设置前、后指针，前指针早于后指针前进2步
    public ListNode<Integer> find3rdElementFromEndInLinkedList(ListNode<Integer> head) {
        if (head == null) return null;
        ListNode<Integer> left = head;
        ListNode<Integer> right = head;
        for (int i = 0; i < 2; i++) {
            if (right.next != null) right = right.next;
            else return null;
        }
        while (right.next != null) {
            right = right.next;
            left = left.next;
        }
        return left;
    }

    // 8. 如何使用栈计算两个链表的和？
    // 核心思想：将链表依次压入栈，然后依次出栈进行加和
    public int sumOfTwoLinkedLists(ListNode<Integer> head1, ListNode<Integer> head2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (head1 != null) {
            s1.push(head1.value);
            head1 = head1.next;
        }
        while (head2 != null) {
            s2.push(head2.value);
            head2 = head2.next;
        }
        int flag = 0;  //用于标记是否进位
        int pow = 0;
        int sum = 0;
        while (!s1.empty() && !s2.empty()) {
            int tmp = s1.pop() + s2.pop() + flag;
            if (tmp >= 10) {
                flag = 1;
                sum = sum + (tmp%10) * ((int) Math.pow(10, pow));
            } else {
                flag = 0;
                sum = sum + tmp * ((int) Math.pow(10, pow));
            }
            pow++;
        }
        while (!s1.empty()) {
            int tmp = s1.pop()+ flag;
            if (tmp >= 10) {
                flag = 1;
                sum = sum + (tmp%10) * ((int) Math.pow(10, pow));
            } else {
                flag = 0;
                sum = sum + tmp * ((int) Math.pow(10, pow));
            }
            pow++;
        }
        while (!s2.empty()) {
            int tmp = s2.pop()+ flag;
            if (tmp >= 10) {
                flag = 1;
                sum = sum + (tmp%10) * ((int) Math.pow(10, pow));
            } else {
                flag = 0;
                sum = sum + tmp * ((int) Math.pow(10, pow));
            }
            pow++;
        }
        if (flag == 1) sum = sum + ((int) Math.pow(10, pow));
        return sum;
    }

    // 9. 如何在适当的位置反转链表？
    // CASE 1 - 反转第 n 个节点及其之前部分
    public ListNode<Integer> reverseLinkedListBeforwN(ListNode<Integer> head, int n) {
        if (head == null) return null;
        if (n == 1) return head;
        ListNode<Integer> res = head;
        ListNode<Integer> shadow = res;
        for (int i = 2; i <= n; i++) {
            ListNode<Integer> tmp = shadow.next;
            shadow.next = tmp.next;
            tmp.next = res;
            res = tmp;
        }
        return res;
    }
    // CASE 2 - 反转第 n 个节点及其之后部分
    public ListNode<Integer> reverseLinkedListAfterN(ListNode<Integer> head, int n) {
        ListNode<Integer> shadow = head;
        for (int i = 1; i < n; i++) shadow = shadow.next;
        ListNode<Integer> pre = shadow.next;
        if (pre == null || pre.next == null) return head;
        ListNode<Integer> next = pre.next;
        while (next != null) {
            shadow.next = next;
            pre.next = next.next;
            next.next = pre;
            next = pre.next;
        }
        return head;
    }

    // 10. 如何移除链表中的倒数第N个节点？
    // 核心思想：将第N+1及其之后的节点复制给第N个节点
    public ListNode<Integer> removeNthNodeFromEndOfList(ListNode<Integer> head, int n) {
        if (head != null) {
            ListNode<Integer> left = head;
            ListNode<Integer> right = head;
            for (int i = 1; i < n; i++) {
                if (right.next != null) right = right.next;
                else return null;
            }
            while (right.next != null) {
                left = left.next;
                right = right.next;
            }
            // left 为末尾节点
            if (left == right) {
                left = null;
                return head;
            }
            // left 为头节点
            else if (left == head) {
                ListNode<Integer> node = left.next;
                left.next = null;
                return node;
            }
            // left 为一般节点
            else {
                ListNode<Integer> node = left.next;
                left.value = node.value;
                left.next = node.next;
                node.next = null;
                return head;
            }
        }
        return null;
    }

    // 11. 如何合并两个排序的链表？ DONE

    // 12. 如何在链表中添加元素？
    public ListNode<Integer> insertElement(ListNode<Integer> head, int ele) {
        // 在头节点插入
        //return new ListNode<Integer>(ele, head);

        // 在一般节点插入
        ListNode<Integer> node = head;
        //for (int i = 0; i < 6; i++) {
        //    node = node.next;
        //}
        //node.next = new ListNode<Integer>(ele, node.next);

        // 在末尾插入
        while (node.next != null) node = node.next;
        node.next = new ListNode<>(ele, null);
        return head;

    }

    // 13. 如何实现链表排序？
    // 核心思想：链表中完成插入排序
    // 答案：http://www.java67.com/2016/02/how-to-sort-linkedlist-in-java-example.html
    public ListNode<Integer> sortLinkedList(ListNode<Integer> head) {
        if (head == null) return head;
        ListNode<Integer> tail = head;  // 用于标记已排序序列的尾部
        ListNode<Integer> node = tail.next;
        while (node != null) {
            ListNode<Integer> idx = head;
            if (node.value < idx.value) {
                tail.next = node.next;
                node.next = idx;
                head = node;
            } else if (tail.value < node.value) {
                tail = node;
            } else {
                while (true) {
                    if (idx.value < node.value && idx.next.value > node.value) {
                        idx.next = node;
                        tail.next = node.next;
                        node.next = idx.next;
                        break;
                    }
                    idx = idx.next;
                }
            }
            node = tail.next;
        }
        return head;
    }

    // 14. 数组和链表有什么区别？ DONE
    // 内存存储角度：数组是内存的连续空间，链表是内存的离散空间
    // 内存分配角度：申明数组，需要给定相应大小的内存空间，申明链表只需要给定头节点，后续节点按需分配

    // 15. 如何将排序列表转化为二分查找树？
    // 答案：https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/solution/
    public TreeNode<Integer> convertSortedList2BinarySearchTree(ListNode<Integer> head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode<>(head.value);
        ListNode<Integer> mid = findMiddleElementOfLinkedList(head);
        TreeNode<Integer> root = new TreeNode<>(mid.value);
        root.left = convertSortedList2BinarySearchTree(truncateLinkedList(head, mid));
        root.right = convertSortedList2BinarySearchTree(mid.next);
        return root;
    }
    private ListNode<Integer> truncateLinkedList(ListNode<Integer> head, ListNode<Integer> mid) {
        ListNode<Integer> pre = head;
        while (pre.next != mid) pre = pre.next;
        return head;
    }

    // 进阶：如何将未排序的链表转化为二分查找树？
    // 若简单地将头节点作为树的根节点，则树的查找时间复杂度可能时 o(n)，故为了查找效率，需要将链表先排序，参见13

    // 16. 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
    // 核心思想：设置两个哑链表，一个存较小节点，一个存较大节点
    //答案：https://leetcode.com/problems/partition-list/solution/
    public ListNode<Integer> partitionLinkedList(ListNode<Integer> head, int x) {
        if (head == null) return null;
        ListNode<Integer> dummySmall = new ListNode<>(null, null);
        ListNode<Integer> small = dummySmall;
        ListNode<Integer> dummyLarge = new ListNode<>(null, null);
        ListNode<Integer> large = dummyLarge;
        while (head != null) {
            if (head.value < x) {
                small.next = head;
                head = head.next;
                small = small.next;
                small.next = null;
            } else {
                large.next = head;
                head = head.next;
                large = large.next;
                large.next = null;
            }
        }
        small.next = dummyLarge.next;
        dummyLarge.next = null;  // 销毁较大哑节点
        ListNode<Integer> res = dummySmall.next;
        dummySmall.next = null;  // 销毁较小哑节点
        return res;
    }

    // 17. 如何在整数链表中删除所有与给定值相等的节点？
    // 核心思想：设置哑指针，指向表头，依次遍历删除符合要求的节点
    public ListNode<Integer> deleteListNodeWithGivenValue(ListNode<Integer> head, int value) {
        ListNode<Integer> dummy = new ListNode<>(null, head);
        ListNode<Integer> node = dummy;
        while (node.next != null) {
            if (node.next.value == value) {
                ListNode<Integer> _node = node.next;
                node.next = _node.next;
                _node.next = null;
            } else {
                node = node.next;
            }
        }
        return dummy.next;
    }

    // 18. 如何找到两个单链表相交的起始节点？ DONE

    // 19. 如何判断一个链表是否是回文结构？
    // 核心思想：利用双端队列
    public boolean isHuiwenLinkedList(ListNode<Integer> head) {
        Deque<Integer> dq = new java.util.LinkedList<>();
        while (head != null) {
            dq.add(head.value);
            head = head.next;
        }
        while (!dq.isEmpty()) {
            if (dq.size() == 1) return true;
            if (!dq.pollFirst().equals(dq.pollLast())) return false;
        }
        return true;
    }

    // 20. 如何从排序链表中删除重复项？
    public ListNode<Integer> removeDuplicatesFromSortedList(ListNode<Integer> head) {
        if (head == null) return null;
        ListNode<Integer> dummy = head;
        int current = dummy.value;
        while (dummy.next != null) {
            if (dummy.next.value == current) {
                ListNode<Integer> node = dummy.next;
                dummy.next = node.next;
                node.next = null;
            } else {
                dummy = dummy.next;
                current = dummy.value;
            }
        }
        return head;
    }
}
