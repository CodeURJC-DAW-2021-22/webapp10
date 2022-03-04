package com.youdemy.model;

import javax.persistence.*;

@Entity
public class VideoThumbnail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Lob
    private byte[] data;

    private String name;
    private String type;

    public VideoThumbnail() {}

    public VideoThumbnail(long id, byte[] data, String name, String type) {
        this.id = id;
        this.data = data;
        this.name = name;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
