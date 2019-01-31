package hzf.demo.sort;

/**
 * Created by WTO on 2018/9/28 0028.
 *
 */
public class ListTranslate
{
    public static void reverse(Node head)
    {
        Node cur = head.next;
        Node front = null;
        while(cur != null)
        {
            Node succ = cur.next;
            cur.next = front;
            front = cur;
            cur = succ;
        }
        head.next = front;
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


        reverse(head);


    }


    public static void main(String[] args) {


        ListTranslate.test();



    }



}
