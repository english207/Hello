package hzf.demo.sort;

/**
 * Created by WTO on 2018/9/28 0028.
 *
 */
public class Feibonaqielie
{
    public static int find(int idx)
    {
        if (idx == 1 || idx == 2)
        {
            return 1;
        }

        return find(idx -1 ) + find(idx -2);
    }

    public static void main(String[] args) {


        System.out.println(find(56));


    }
}
