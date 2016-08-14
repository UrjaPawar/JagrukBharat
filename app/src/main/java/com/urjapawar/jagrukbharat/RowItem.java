package com.urjapawar.jagrukbharat;

public class RowItem {
    private int imageId;
    private String title;
    private String desc;
    private String pub;

    public RowItem(int imageId,String title, String desc, String pub) {
        this.imageId = imageId;
        this.title = title;
        this.desc = desc;
        this.pub = pub;
    }
    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String pub) {
        this.pub = pub;
    }
    public String getPub() {
        return pub;
    }
    public void setPub(String pub) {
        this.title = title;
    }
    @Override
    public String toString() {
        return title + "\n" + desc + "\n" + pub;
    }
}