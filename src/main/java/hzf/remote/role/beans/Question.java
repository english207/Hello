package hzf.remote.role.beans;

/**
 * Created by WTO on 2016/5/2 0002.
 *
 */
public class Question
{
    private String IP;
    private Integer port;
    private String content;


    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
