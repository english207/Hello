package hzf.demo.sort;

import java.util.Arrays;

//快速排序的实现
public class MyQuickSort
{
    public static void quickSort(int arr[],int l,int r)
    {
        if (l < r)
        {
            int q = partition(arr, l, r);
            quickSort(arr, l, q - 1);
            quickSort(arr, q + 1, r);
        }
    }

    public static int partition(int [] arr,int l,int r)
    {
        int x = arr[l];
        int i = l;
        for (int j = i + 1; j <= r; j ++)
        {
            if (arr[j] < x)
            {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, l, i);
        return i;
    }

    public static void swap(int [] arr,int m,int n)
    {
        int temp=arr[m];
        arr[m]=arr[n];
        arr[n]=temp;
    }

    public static void main(String[] args)
    {
        int [] a = {5,8,6,4,6,7,9,2,4,1,3,6,5,7};
        System.out.println("快速排序之前的数组如下："+Arrays.toString(a));
        quickSort(a,0,a.length-1);
        System.out.println("快速排序之后的数组如下："+Arrays.toString(a));
    }
}