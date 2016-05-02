package hzf.remote.data;

import hzf.remote.base.Identity;
import hzf.remote.role.base.RoleDoSomeThing;
import hzf.remote.role.beans.Mediator;
import hzf.remote.role.beans.Relationship;

import java.net.InetAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by WTO on 2016/5/1 0001.
 *
 */
public class BrainData
{
    private String IP = null;
    private Integer PORT = null;
    private String GroupName = null;
    private String MediatorAddresses = "115.171.113.69:8080";
    private Map<String, Map<String, String>> mediatorsMap = new ConcurrentHashMap<String, Map<String, String>>();
    private Identity identity = Identity.NoBody;
    private String NickName = "";
    private RoleDoSomeThing role;
    private boolean isMediator = false;
    private long GetUpTime = System.currentTimeMillis();
    private Relationship relationship = new Relationship();

    public BrainData(Integer port, String groupName)
    {
        this.PORT = port;
        this.GroupName = groupName;
        init();
    }

    public void init()
    {
        try
        {
            IP = InetAddress.getLocalHost().getHostAddress();
            System.out.println("Local IP - " + IP + ":" + PORT);
            String[] addresses = MediatorAddresses.split(",");

            Map<String, Mediator> mediatorMap =  relationship.getMediatorMap();

            for (String hostAndPort : addresses)
            {
                String[] hostAndPortTmp = hostAndPort.split(":");
                String targetIP = hostAndPortTmp[0];
                String targetPort = hostAndPortTmp[1];
                String mediatorIP = "";

                Mediator mediator = new Mediator();
                mediator.setAddress(targetIP);
                mediator.setPort(targetPort);
                mediatorMap.put(hostAndPort, mediator);

                try
                {
                    mediatorIP = InetAddress.getByName(targetIP).getHostAddress();
                }
                catch (Exception e) { e.printStackTrace(); }

                if (mediatorIP.equals(IP) && targetPort.equals(PORT + "") )
                {
                    isMediator = true;
                    // todo 成为Mediator可能要做一些事情
                }
            }

            if (isMediator)
            {
                identity = Identity.Mediator;
                System.out.println("I'm Mediator!");
            }
            else
            {
                identity = Identity.NoBody;
                System.out.println("I'm a NoBody now!");
            }
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    public static void main(String[] args) {
        BrainData brainData = new BrainData(8080, "hzf");
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public String getMediatorAddresses() {
        return MediatorAddresses;
    }

    public void setMediatorAddresses(String mediatorAddresses) {
        MediatorAddresses = mediatorAddresses;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public Integer getPORT() {
        return PORT;
    }

    public void setPORT(Integer PORT) {
        this.PORT = PORT;
    }

    public RoleDoSomeThing getRole() {
        return role;
    }

    public void setRole(RoleDoSomeThing role) {
        this.role = role;
    }

    public boolean isMediator() {
        return isMediator;
    }

    public void setIsMediator(boolean isMediator) {
        this.isMediator = isMediator;
    }

    public long getGetUpTime() {
        return GetUpTime;
    }

    public void setGetUpTime(long getUpTime) {
        GetUpTime = getUpTime;
    }

    public Map<String, Map<String, String>> getMediatorsMap() {
        return mediatorsMap;
    }

    public void setMediatorsMap(Map<String, Map<String, String>> mediatorsMap) {
        this.mediatorsMap = mediatorsMap;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }
}
