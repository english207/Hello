package hzf.demo.bitmap;

/**
 * Created by WTO on 2018/6/9 0009.
 *
 */
public class ContainerArray
{
    // 一个 container 代表 65536个数字
    private Container[] containers = null;
    public ContainerArray()
    {
        this.containers = new Container[1024];
    }

    private int getIndex(short x)
    {
        return 0;
    }

    private Container getContainer(int idx)
    {
        Container container = containers[idx];
        if (container == null) {
            container = new ByteContainer();
            containers[idx] = container;
        }
        return container;
    }

    private void addContainer(int idx, Container container)
    {
        containers[idx] = container;
    }

    public static void main(String[] args)
    {
        ContainerArray containerArray = new ContainerArray();
        Container container = containerArray.getContainer(0);
        byte b = 63;
        containerArray.addContainer(0, container.add(b));

        try
        {
            for (int i = 0; i < 100000; i++) {
                Thread.sleep(600);
            }
        }
        catch (Exception e) { e.printStackTrace(); }
    }

}
