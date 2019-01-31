package hzf.demo.sort;

/**
 * Created by fan on 2016/10/10.
 *
 */
public class BinarySearchMeituan
{

    public static int search(int[] arr, int x)
    {
        //特殊处理
        if (arr == null || arr.length == 0)
        {
            return -1;
        }

        int start = 0;
        int end = arr.length - 1;
        int mid = 0;

        while ( start <= end)
        {
            mid = (start + end) / 2;

            int mid_val = arr[mid];

            if (mid_val == x)
            {
                return mid;
            }

            if (mid_val < arr[mid + 1] && mid_val < arr[mid - 1])   // 正好处于最低
            {
                break;
            }

            if (mid_val > arr[mid - 1] && mid_val < arr[mid + 1])   // 处于升序部分
            {
                end =  mid - 1;
            }

            if (mid_val < arr[mid - 1] && mid_val > arr[mid + 1])   // 处于降序部分
            {
                start =  mid + 1;
            }
        }
        System.out.println("mid - " + mid);
        return arr[mid] == x ? mid : -1;
    }

    public static void main(String[] args) {
        //6,7,8,9,4,5,6
        //9
        //6,7
        //6,5
        int[] arr = {99, 77, 55, 44, 33, 22, 3, 5, 21, 34, 55};
        System.out.println(search(arr, 55));
    }
}