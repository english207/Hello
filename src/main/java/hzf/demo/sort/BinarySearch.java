package hzf.demo.sort;

/**
 * Created by fan on 2016/10/10.
 *
 */
public class BinarySearch
{

    public static int search(int[] arr, int x)
    {
        int start = 0;
        int end = arr.length - 1;
        int mid = 0;
        while (start <= end)
        {
            mid = (start + end) / 2;

            int mid_val = arr[mid];

            if (mid_val == x)
            {
                return mid;
            }

            if (mid_val > x)
            {
                end = mid - 1;
            }
            else if (mid_val < x)
            {
                start = mid + 1;
            }
        }

        int mid_val = arr[mid];
        return mid_val == x ? mid : -1;
    }

    public static void main(String[] args) {
        //6,7,8,9,4,5,6
        //9
        //6,7
        //6,5
        int[] arr = {1,2,6,8,9,11,33,55,77,120,130,224};
        System.out.println(search(arr, 131));
    }
}