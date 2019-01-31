package hzf.demo.sort;

/**
 * Created by WTO on 2018/9/28 0028.
 *
 */
public class MyListTranslate
{
    public static void reverse(Node head)
    {
        Node newHead = new Node();
        Node node = head.next;

        while (node != null)
        {
            head.next = node.next;
            node.next = newHead.next;
            newHead.next = node;
            node = head.next;
        }

        head.next = newHead.next;
        newHead.next = null;
    }

    static class Node
    {
        public Object data;
        public Node next;
        public Node()
        {
            this.data=null;
            this.next=null;
        }
        public Node(Object data){
            this.data=data;
            this.next=null;
        }
        public Node(Object data,Node next){
            this.data=data;
            this.next=next;
        }

        @Override
        public String toString() {
            return "data - " + data ;
        }
    }

    public static void test()
    {
        Node head = new Node();
        head.data = null;

        Node node1 = new Node();
        node1.data = 1;

        head.next = node1;

        Node node2 = new Node();
        node2.data = 2;

        node1.next = node2;


        Node node3 = new Node();
        node3.data = 3;

        node2.next = node3;

        printList(head);
        reverse(head);
        printList(head);


    }

    private static void printList(Node node)
    {
        if (node != null)
        {
            System.out.println(node.data);
            printList(node.next);
        }

    }



    public static void main(String[] args) {


        MyListTranslate.test();



    }



}
