package hzf.remote.role.compoment;


import hzf.remote.base.Identity;
import hzf.remote.role.beans.Worker;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by WTO on 2016/4/30 0030.
 *
 */
public class IMMediator extends IMWorker
{
    private Map<String, List<Worker>> groupWorks = new ConcurrentHashMap<String, List<Worker>>();

    public IMMediator(Identity identity)
    {
        super(identity);
    }

    public String go(String content)
    {
        try
        {
            Map<String, Object> map = jackSon.readValue(content, Map.class);
            String groupName = (String) map.get("groupName");
            System.out.println(groupName);
        }
        catch (Exception e) { e.printStackTrace(); }

        return "receive";
    }

    public void xxx()
    {

    }
}
