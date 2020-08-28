package Base.MyLinkedList;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 尝试实现自己的单链表类
 * 采用头插的方法插入新的元素
 */
public class SingleLinkedList {
    Node head;
    private int count=0;
    //构造函数
    public SingleLinkedList(){Node head=null;}
    //插入新的元素
    public void addNode(int x)
    {
        Node newNode=new Node(x);
        if(head==null) {
            head = newNode;
            count++;
        }
        else
        {
            Node temp=head;
            head=newNode;
            head.next=temp;
            count++;
        }
    }
    //打印真个链表
    public void print()
    {
        if(head==null) return;
        Node travel=head;
        while(travel!=null)
        {
            System.out.print(travel.val+" ");
            travel=travel.next;
        }
        System.out.println();
    }
    //链表长度函数
    public int size()
    {
        return count;
    }
    //找到给定数值位置的节点
    public Node find(int d)
    {
        if(head==null) return null;
        if(d<1||d>count) return null;
        else {
            int a = 1;
            Node temp=head;
            while(a<d)
            {
                temp=temp.next;
                a++;
            }
            return temp;
        }
    }
    //删除函数,并且返回被删除的节点的值
    public boolean delete(int x)
    {
        if(head==null) return false;
        if(x<1||x>count) return false;
        if(x==1) //x=1 删除头结点
        {
            if(head.next!=null) head=head.next;
            else head=null;
            count--;
            return true;
        }
        else if(x==count) //删除尾节点
        {
            Node temp=head;
            for(int b=2;b<x;b++)     //这里需要注意,因为在开始temp已经指向head,
                temp=temp.next;      //并且for第一次循环temp向后移动一位,就把b初始为2
            temp.next=null;
            count--;
            return true;
        }
        else               //删除中间的节点
        {
            Node pre=head;
            Node todelete=find(x);
            while(pre.next!=todelete)
            {
                pre=pre.next;
            }
            Node temp=todelete.next;
            pre.next=temp;
            count--;
            return true;
        }
    }
    //在指定位置插入元素
//    public boolean insert(int x)
//    {
//
//    }
    //反转链表
    public void reverse()
    {
        if(head==null||count==1) return;
        Node tail=null;
        Node travel=head;
        while(travel!=null)
        {
            Node temp;
            temp=travel.next;
            travel.next=tail;
            tail=travel;
            travel=temp;
        }
        head=tail;
    }
    //判断链表是不是回文,空为否,一个为是,其他情况进行判断
    public boolean isPalindrome()
    {
        if(head==null) return false;
        if(count==1) return true;
        else {
            Node fast=head;
            Node slow=head;
            Stack<Node> stack=new Stack<>();
            while(fast!=null)
            {
                if(fast.next==null)  {slow=slow.next;break;}//如果没有break,就没办法跳出循环,fast条件一直为真
                else{fast=fast.next.next; slow=slow.next;}  //直到slow越界
            }
            while(slow!=null){
                stack.push(slow);
                slow=slow.next;
            }
            Node travel=head;
            while(!stack.isEmpty()) {
                if (stack.pop().val != travel.val) return false;
                else travel=travel.next;
            }
            return true;
        }
    }
    public boolean isPalindrome1(){
        if(head==null) return false;
        if(count==1) return true;
        Node fast=head;
        Node slow=head;
        Stack<Node> stack=new Stack<>();
        while(fast!=null){
            if(fast.next!=null) {
                stack.push(slow);
                fast=fast.next.next;
                slow=slow.next;
            }
            else{
                slow=slow.next;
                break;
            }
        }
        while(!stack.isEmpty()){
            if(stack.pop().val!=slow.val) return false;
            else {
                slow=slow.next;
            }
        }
        return true;
    }
    public boolean isEmpty()
    {
        if(count==0) return true;
        else return false;
    }

    public static void main(String[] args) {
        SingleLinkedList sin=new SingleLinkedList();
//        System.out.println(sin.isEmpty());
        sin.addNode(5);
//        sin.addNode(6);
//        sin.addNode(6);
//        sin.addNode(5);
        System.out.println(sin.isPalindrome1());
        Map<Integer,Integer> map=new HashMap<>();//        sin.print();
//        sin.reverse();
//        sin.print();
//        sin.reverse();
//        sin.print();
//        System.out.println(sin.size());
//        sin.delete(1);
//        sin.delete(1);
//        sin.print();
//        System.out.println(sin.size());
    }
}
