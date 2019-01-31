package hzf.demo.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//快速排序的实现
public class QuickSort
{
    public static void quickSort(int arr[],int l,int r)
    {
        int q;
        if( l < r )
        {
             /*
              * 得到主元元素的位置下标
              * */
            q = partition(arr, l, r);
            quickSort(arr, l, q-1 );
            quickSort(arr, q + 1, r);
        }
    }

    public static int partition(int [] arr,int l,int r)
    {
        //选取第一个元素作为主元，也可以选取任意位置的元素作为主元
        int x = arr[l];
        int i=l;
        for(int j=l+1;j<=r;j++)
        {
            /*
             * 小于等于主元的数组元素放在主元元素的左边，其他的放在右边
             * */
            if(arr[j]<=x)
            {
                i++;
                swap(arr,i,j);
            }
        }
        swap(arr,i,l);
        return i;
    }

    public static void swap(int [] arr,int m,int n)
    {
        int temp=arr[m];
        arr[m]=arr[n];
        arr[n]=temp;
    }

    public static void swap(List<Integer> arr,int m,int n)
    {
        int temp=arr.get(m);
        arr.set(m, arr.get(n));
        arr.set(n, temp);
    }

    public static void sort(List<Integer> list){
        int size = list.size();
        int topEnd = 0;
        int bottomStart = size - 1;
        int current = 0;
        while(current <= bottomStart){
            int currentVal = list.get(current);
            if(currentVal < 1){
                swap(list, current, topEnd);
                topEnd++;
                current++;
            }else if(currentVal > 1){
                swap(list, current, bottomStart);
                bottomStart--;
            }else{
                current++;
            }
        }
    }

    public static void main(String[] args)
    {
        int [] a = {5,8,6,4,6,7,9,2,4,1,3,6,5,7};
        System.out.println("快速排序之前的数组如下："+Arrays.toString(a));
        quickSort(a,0,a.length-1);
        System.out.println("快速排序之后的数组如下："+Arrays.toString(a));

        Integer [] b = {5,8,6,4,6,7,9,2,4,1,3,6,5,7};
        List<Integer> list = Arrays.asList(b);

        sort(list);
        sort(list);
        sort(list);
        sort(list);
        sort(list);
        sort(list);
        sort(list);
        sort(list);
        sort(list);

        System.out.println(list);


    }
}