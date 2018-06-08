package hzf.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by WTO on 2016/10/19 0019.
 */
public class Test
{
    public String name  = "";

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode();
    }

    public static void main(String[] args) {
        Test a = new Test();
        Test b = new Test();

        a.name = "1";
        b.name = "1";

        System.out.println(a.hashCode());
        System.out.println(b.hashCode());

        System.out.println(a.equals(b));

        BitSet bitSet = new BitSet(10);
        bitSet.set(1);
        bitSet.get(1);

    }

}
