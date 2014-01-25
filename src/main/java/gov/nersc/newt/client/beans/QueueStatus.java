package gov.nersc.newt.client.beans;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Date;

/**
 *
 * @author tonyj
 */
public class QueueStatus {

    private String status;
    private String name;
    private String timeuse;
    private String hostname;
    private String rank;
    private String jobid;
    private String queue;
    private Date submittime;
    private String state;
    private String user;
    private int nodes;
    private String timereq;
    private int procs;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeuse() {
        return timeuse;
    }

    public void setTimeuse(String timeuse) {
        this.timeuse = timeuse;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    @JsonDeserialize(using = FileDateDeserializer.class)
    public Date getSubmittime() {
        return submittime;
    }

    public void setSubmittime(Date submittime) {
        this.submittime = submittime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getNodes() {
        return nodes;
    }

    public void setNodes(int nodes) {
        this.nodes = nodes;
    }

    public String getTimereq() {
        return timereq;
    }

    public void setTimereq(String timereq) {
        this.timereq = timereq;
    }

    public int getProcs() {
        return procs;
    }

    public void setProcs(int procs) {
        this.procs = procs;
    }

    @Override
    public String toString() {
        return "QueueStatus{" + "status=" + status + ", name=" + name + ", timeuse=" + timeuse + 
                ", hostname=" + hostname + ", rank=" + rank + ", jobid=" + jobid + 
                ", queue=" + queue + ", submittime=" + submittime + ", state=" + state + 
                ", user=" + user + ", nodes=" + nodes + ", timereq=" + timereq + 
                ", procs=" + procs + '}';
    }
    
}
