package com.urjapawar.jagrukbharat;

/**
 * Created by DELL on 14-08-2016.
 */
public class FarmerInfo {
    String rights;
    String link;

    public FarmerInfo(String r, String t)
    {
        this.rights=r;
        this.link=t;

    }


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }
}
