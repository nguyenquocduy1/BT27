package com.example.bt27;

import java.util.Date;

public class Song {
    String name;
    String createDate;

    public Song(String name, String createDate) {
        this.name = name;
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
