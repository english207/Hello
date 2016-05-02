package hzf.remote.socket;

import hzf.remote.base.Identity;
import hzf.remote.data.BrainData;
import hzf.remote.role.base.RoleDoSomeThing;
import hzf.remote.role.beans.Question;
import hzf.remote.role.compoment.RoleBuidler;
import hzf.remote.utils.socket.ServerBuilder;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by WTO on 2016/4/30 0030.
 *
 */
public class MySelf
{
    private String ip;
    private Integer port;
    private String groupName;
    private Map<String, String> map = new ConcurrentHashMap<String, String>();

    private static final int heartBeat = 1000;

    private IAskOther askOther;
    private OtherAskMe askMe;

    private ServerSocket myself;
    private BrainData brain;
    private RoleDoSomeThing role;
    private Identity identity;
    private long getUpTime = 0L;

    public MySelf(String groupName)
    {
        this.groupName = groupName;
        init();
    }

    public void init()
    {
        myself = ServerBuilder.createServer();
        this.port = myself.getLocalPort();
        brain = new BrainData(port, "hzf");
        getUpTime = brain.getGetUpTime();
        role = RoleBuidler.createRole(brain);
        identity = brain.getIdentity();

        askOther = new IAskOther(role);
        askMe = new OtherAskMe(myself, role);
        System.out.println("MySelf 初始化完成！");
    }

    public void askLeader()
    {
        if (identity.equals(Identity.Worker))
        {
//            askOther.go();
        }

        if (identity.equals(Identity.NoBody))
        {
//            askOther.go();
        }

    }

    public void askMediator()
    {
        if (identity.equals(Identity.NoBody))
        {
            askOther.go(getMediatorQuestion());
        }
    }

    public List<Question> getMediatorQuestion()
    {
        List<Question> questions = new ArrayList<Question>();
        String[] addresses = brain.getMediatorAddresses().split(",");

        for (String hostAndPort : addresses)
        {
            String[] hostAndPortTmp = hostAndPort.split(":");
            String targetIP = hostAndPortTmp[0];
            String targetPort = hostAndPortTmp[1];

            Question question = new Question();
            question.setIP(targetIP);
            question.setPort(Integer.parseInt(targetPort));
            String content = "{\"groupName\":\"hzf\",\"questionType\":\"new\"}";
            question.setContent(content);
            questions.add(question);
        }

        return questions;
    }

    public static void main(String[] args)
    {
        MySelf mySelf = new MySelf("hzf");

        mySelf.askMediator();
    }
}
