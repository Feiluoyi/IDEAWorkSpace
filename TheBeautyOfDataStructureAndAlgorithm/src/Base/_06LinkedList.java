package Base;
/**
 * 自定义了一个单链表节点的类Node
 * isHuiwen方法以头结点为参数,检查单链表是否为回文
 * 要明确一个概念,java中的引用就是指针
 */
public class _06LinkedList {
    public static boolean isHuiwen(Node head)
    {
        Node fast=head;
        Node slow=head;
        while(head==null||head.next==null){return true;}
        /**
         * 快慢双指针需要注意链表数量奇偶的情况,这里while里面多加了一个if else语句
         * 对于奇数情况下,fast将刚好走到最后一个节点上,此时直接使用语句fast.next.next将提示 NoPointerException
         */
        while(fast!=null)
        {
            if(fast.next!=null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            else
            {
                slow=slow.next;
                break;
            }
        }
        Node tail=null;          //tail在外部初始化为null,恰好保证了最后 tail.next==null
        while(slow!=null)        //从指针的角度考虑这个方法
        {
            Node temp=slow.next; //先将next节点保存起来
            slow.next=tail;      //将solw节点的next保存成左侧的节点
            tail=slow;           //相将tail指针移动到当前slow节点上
            slow=temp;           //slow指向原来的temp,相当于指针向右移动一个位置
        }
        while(tail!=null)
        {
            if(head.val==tail.val)
            {
                head=head.next;
                tail=tail.next;
            }
            else return false;
        }
        return true;
    }
    public static void main(String[] args) {
        Node head=new Node(1);
        Node p2  =new Node(2);
        Node p3  =new Node(1);
//        Node p4  =new Node(1);
//        Node p5  =new Node(1);
        head.next=p2;
        p2.next=p3;
        //p3.next=p4;
        //p4.next=p5;
        System.out.println(isHuiwen(head));
       }
}
class Node
{
    public int val;
    public Node next;
    public Node head=null;
    public Node(int x)
    {
        this.val=x;
    }
    public void addNode(Node x)
    {


    }
    public Node head(){return head;}
}