package hzf.remote.role.beans;


/**
 * Created by WTO on 2016/4/30 0030.
 *
 *  中间人，所有关系由它关联
 *
 */
public class Mediator
{
    private String address;
    private String port;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
