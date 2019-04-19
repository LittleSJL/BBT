package com.example.bbt_true_app;

/**
 * Created by leptop on 2019/3/11.
 */

public class Message {
    private String yonghu;
    private String currentmessage;
    private String time;
    private String number_unread;

    public Message(String yonghu, String currentmessage,String time,String number_unread)
    {
        this.yonghu = yonghu;
        this.currentmessage = currentmessage;
        this.time = time;
        this.number_unread = number_unread;
    }
    {

    }

    public String getYonghu() {
        return yonghu;
    }

    public void setYonghu(String yonghu) {
        this.yonghu = yonghu;
    }

    public String getCurrentmessage() {
        return currentmessage;
    }

    public void setCurrentmessage(String currentmessage) {
        this.currentmessage = currentmessage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNumber_unread() {
        return number_unread;
    }

    public void setNumber_unread(String number_unread) {
        this.number_unread = number_unread;
    }
}
