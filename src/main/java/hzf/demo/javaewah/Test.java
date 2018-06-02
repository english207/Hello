package hzf.demo.javaewah;

/**
 * Created by WTO on 2018/6/2 0002.
 *
 */
public class Test
{
    public static void main(String[] args) {

        EWAHCompressedBitmap bitmap = new EWAHCompressedBitmap();

        for (int i = 0; i < 1000000; i++) {
            bitmap.set(60 + 64 * i);
            for (int j = 0; j < 60; j++) {
                bitmap.set(Double.valueOf(Math.random() * 60 +  64 * i).intValue());
            }
        }

        long start = System.currentTimeMillis();
        boolean result = bitmap.get(60 + 64 * 99999);
        System.out.println(result + " - " + (System.currentTimeMillis() - start) + " ms");


        System.out.println(bitmap.cardinality());






    }
}
