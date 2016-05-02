package hzf.remote.role.beans;

import hzf.remote.base.Identity;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by WTO on 2016/5/2 0002.
 *
 */
public class Help
{
    private String fromWho;
    private String groupName;
    private String targetIP;
    private String targetPort;
    private Identity identify;
    private String function;
    private String content;

    public String getFromWho() {
        return fromWho;
    }

    public void setFromWho(String fromWho) {
        this.fromWho = fromWho;
    }

    public Identity getIdentify() {
        return identify;
    }

    public void setIdentify(Identity identify) {
        this.identify = identify;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTargetIP() {
        return targetIP;
    }

    public void setTargetIP(String targetIP) {
        this.targetIP = targetIP;
    }

    public String getTargetPort() {
        return targetPort;
    }

    public void setTargetPort(String targetPort) {
        this.targetPort = targetPort;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public static void main(String[] args) {
        Help help = new Help();

        help.setGroupName("Galaxy");
        help.setTargetIP("192.168.137.1");
        help.setTargetPort("8080");
        help.setContent("check yourself");
        help.setFromWho("hzf");
        help.setFunction("say move");
        help.setIdentify(Identity.Leader);

        ObjectMapper jackSon = new ObjectMapper();
        List<Help> helps = new ArrayList<Help>();
        helps.add(help);
        try
        {
            System.out.println(jackSon.writeValueAsString(helps));
        }
        catch (Exception e) { e.printStackTrace(); }


    }
}
