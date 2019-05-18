package com.example.neeraj.blackerspro.timelineWork.messagesWroking;

public class Messages {
    String msg;
    String time;
    String username;

    public Messages() {
// firestore needed a empty constructor
    }

    public Messages(String msg, String time, String username) {
        this.msg = msg;
        this.time = time;
        this.username = username;
    }

    public String getMsg() {
        return msg;
    }

    public String getTime() {
        return time;
    }

    public String getUsername() {
        return username;
    }
}
