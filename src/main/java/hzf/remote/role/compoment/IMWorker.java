package hzf.remote.role.compoment;

import hzf.remote.base.Identity;
import hzf.remote.role.base.RoleDoSomeThing;
import org.codehaus.jackson.map.ObjectMapper;


/**
 * Created by WTO on 2016/4/30 0030.
 *
 */
public class IMWorker implements RoleDoSomeThing
{
    public ObjectMapper jackSon = new ObjectMapper();
    public Identity identity;

    public IMWorker(Identity identity)
    {
        this.identity = identity;
        init();
    }

    // 去访问一下Mediator
    public void init()
    {

    }

    public String go(String content)
    {
        return "I want to see Luck, thanks!";
    }
}
