package gov.nersc.newt.client.beans;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Date;

/**
 *
 * @author tonyj
 */
public class DirectoryEntry {

    private String user;
    private String group;
    private String name;
    private String perms;
    private int hardlinks;
    private long size;
    private Date date;

    public String getUser(){
        return user;
    }

    public void setUser(String user){
        this.user = user;
    }

    public String getGroup(){
        return group;
    }

    public void setGroup(String group){
        this.group = group;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPerms(){
        return perms;
    }

    public void setPerms(String perms){
        this.perms = perms;
    }

    public int getHardlinks(){
        return hardlinks;
    }

    public void setHardlinks(int hardlinks){
        this.hardlinks = hardlinks;
    }

    public long getSize(){
        return size;
    }

    public void setSize(long size){
        this.size = size;
    }

    @JsonDeserialize(using = FileDateDeserializer.class)
    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    @Override
    public String toString(){
        return "DirectoryEntry{" + "user=" + user + ", group=" + group + ", name=" + name + 
                ", perms=" + perms + ", hardlinks=" + hardlinks + ", size=" + size + 
                ", date=" + date + '}';
    }
}
