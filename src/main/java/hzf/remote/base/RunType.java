package hzf.remote.base;

/**
 * Created by WTO on 2016/4/30 0030.
 *
 */
public enum RunType
{
    OK("OK", 1),OVER("OVER", -1);

    private String desc;
    private int type;

    RunType(String desc, int type)
    {
        this.desc = desc;
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public int getType() {
        return type;
    }

}
