package com.example.kalimdalwai.Data;

public class Data {

    private String Title;
    private int StoryId;
    private String Message;
    private String ImgUrl;
    private String rightUser;
    private String leftUser;

    public Data() {
    }

    public String getRightUser() {
        return rightUser;
    }

    public void setRightUser(String rightUser) {
        this.rightUser = rightUser;
    }

    public String getLeftUser() {
        return leftUser;
    }

    public void setLeftUser(String leftUser) {
        this.leftUser = leftUser;
    }

    public Data(String message) {
        this.Message = message;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        this.Message = message;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getStoryId() {
        return StoryId;
    }

    public void setStoryId(int storyId) {
        StoryId = storyId;
    }
}
