package hzf.remote.role.compoment;

import hzf.remote.role.base.RoleDoSomeThing;

/**
 * Created by WTO on 2016/5/2 0002.
 *
 */
public abstract class IMBase implements RoleDoSomeThing
{
    public abstract void init();

    public String go(String conten)
    {
        return null;
    }
}
