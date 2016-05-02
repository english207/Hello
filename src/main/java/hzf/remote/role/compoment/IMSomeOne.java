package hzf.remote.role.compoment;

import hzf.remote.base.Identity;
import hzf.remote.data.BrainData;
import hzf.remote.role.base.RoleDoSomeThing;
import hzf.remote.role.beans.Relationship;
import hzf.remote.role.beans.Worker;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by WTO on 2016/5/2 0002.
 *
 */
public class IMSomeOne implements RoleDoSomeThing
{
    private ObjectMapper jackSon = new ObjectMapper();
    private BrainData brain;
    private Identity identity;
    private Relationship relationship;

    public IMSomeOne(BrainData brain)
    {
        this.brain = brain;
        this.identity = brain.getIdentity();
        this.relationship = brain.getRelationship();
    }

    public String go(String content)
    {
        try
        {
            Map<String, Object> map = jackSon.readValue(content, Map.class);
            String questionType = (String) map.get("questionType");

            if ("new".equals(questionType))
            {

                welComeNewFriend(map);
            }
        }
        catch (Exception e) { e.printStackTrace(); }
        return endTheTalk();
    }

    public void welComeNewFriend(Map<String, Object> map)
    {
        String groupName = (String) map.get("groupName");

        Map<String, List<Worker>> allMap = relationship.getALL_WORKER();
        List<Worker> list = allMap.get(groupName);

        if (list != null && list.size() > 0)
        {
            // 存在leader
        }
        else
        {
            // 不存在leader
            Worker worker = new Worker();

        }



    }

    public String endTheTalk()
    {
        String receive = null;
        switch (identity)
        {
            case Mediator:
            case Leader:
                receive = "receive";
                break;
            case Worker:
                receive = "I want to see Luck, thanks!";
                break;
        }
        return receive;
    }
}
