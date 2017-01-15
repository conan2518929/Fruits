package com.demo.yige.fruits.entity;

/**
 * Created by yige on 2016/12/22.
 */

public class TPost {

    private int id;
    private String PostID;
    private String PostName;

    public TPost(){

    }

    public TPost(String PostID,String PostName){
        this.PostID = PostID;
        this.PostName = PostName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostID() {
        return PostID;
    }

    public void setPostID(String postID) {
        PostID = postID;
    }

    public String getPostName() {
        return PostName;
    }

    public void setPostName(String postName) {
        PostName = postName;
    }
}
