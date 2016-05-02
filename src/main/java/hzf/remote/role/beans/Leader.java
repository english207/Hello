package hzf.remote.role.beans;

import java.util.List;
import java.util.Map;

/**
 * Created by WTO on 2016/4/30 0030.
 *
 *      每一个组的组长
 */
public class Leader
{
    private String address;
    private String port;
    private String groupName;

    private Map<String, Worker> workers;

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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Map<String, Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(Map<String, Worker> workers) {
        this.workers = workers;
    }
}
