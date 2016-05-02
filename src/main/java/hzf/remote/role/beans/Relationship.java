package hzf.remote.role.beans;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by WTO on 2016/5/1 0001.
 *
 */
public class Relationship
{
    private Mediator mediator = new Mediator();
    private Leader leader = new Leader();
    private Map<String, Mediator> mediatorMap = new ConcurrentHashMap<String, Mediator>();
    private Map<String, Leader> leaderMap = new ConcurrentHashMap<String, Leader>();
    private Map<String, List<Worker>> SAME_GROUP = new ConcurrentHashMap<String, List<Worker>>();
    private Map<String, List<Worker>> OTHER_GROUP = new ConcurrentHashMap<String, List<Worker>>();

    private Map<String, String> GROUP_NAME = new ConcurrentHashMap<String, String>();

    private Map<String, List<Worker>> ALL_WORKER = new ConcurrentHashMap<String, List<Worker>>();


    private Map<String, Worker> hostWorker = new ConcurrentHashMap<String, Worker>();


    public Map<String, Mediator> getMediatorMap() {
        return mediatorMap;
    }

    public void setMediatorMap(Map<String, Mediator> mediatorMap) {
        this.mediatorMap = mediatorMap;
    }

    public Map<String, Leader> getLeaderMap() {
        return leaderMap;
    }

    public void setLeaderMap(Map<String, Leader> leaderMap) {
        this.leaderMap = leaderMap;
    }

    public Mediator getMediator() {
        return mediator;
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public Leader getLeader() {
        return leader;
    }

    public void setLeader(Leader leader) {
        this.leader = leader;
    }

    public Map<String, List<Worker>> getSAME_GROUP() {
        return SAME_GROUP;
    }

    public void setSAME_GROUP(Map<String, List<Worker>> SAME_GROUP) {
        this.SAME_GROUP = SAME_GROUP;
    }

    public Map<String, List<Worker>> getOTHER_GROUP() {
        return OTHER_GROUP;
    }

    public void setOTHER_GROUP(Map<String, List<Worker>> OTHER_GROUP) {
        this.OTHER_GROUP = OTHER_GROUP;
    }

    public Map<String, List<Worker>> getALL_WORKER() {
        return ALL_WORKER;
    }

    public void setALL_WORKER(Map<String, List<Worker>> ALL_WORKER) {
        this.ALL_WORKER = ALL_WORKER;
    }

    public Map<String, String> getGROUP_NAME() {
        return GROUP_NAME;
    }

    public void setGROUP_NAME(Map<String, String> GROUP_NAME) {
        this.GROUP_NAME = GROUP_NAME;
    }

    public Map<String, Worker> getHostWorker() {
        return hostWorker;
    }

    public void setHostWorker(Map<String, Worker> hostWorker) {
        this.hostWorker = hostWorker;
    }
}
