package hzf.demo.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by WTO on 2018/9/28 0028.
 *
 */
public class TreeIterator
{

    public static void iterator(Node root)
    {
        BlockingQueue<Node> queue = new LinkedBlockingQueue<Node>();
        BlockingQueue<Node> next_queue = new LinkedBlockingQueue<Node>();
        queue.add(root);

        while ( !queue.isEmpty() )
        {
            Node node = queue.poll();

            System.out.print(node.val);

            if (node.left != null)
            {
                next_queue.add(node.left);
            }

            if (node.right != null)
            {
                next_queue.add(node.right);
            }

            if (queue.isEmpty())
            {
                BlockingQueue<Node> temp = queue;
                queue = next_queue;
                next_queue = temp;

                System.out.println();
            }
        }
    }

    static class Node
    {
        public String val;
        public Node left;
        public Node right;
    }

    public static void test()
    {

        Node D = new Node();
        D.val = "D";
        Node F = new Node();
        F.val = "F";

        Node B = new Node();
        B.val = "B";
        B.left = D;
        B.right = F;

        Node G = new Node();
        G.val = "G";

        Node C = new Node();
        C.val = "C";
        C.left = G;

        Node A = new Node();
        A.val = "A";
        A.left = B;
        A.right = C;

        iterator(A);
    }

    public static void main(String[] args)
    {
        TreeIterator.test();
    }


}
