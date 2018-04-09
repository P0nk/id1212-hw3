package com.perloven.server.model;

public class File {

    private long id;
    private long fileId;
    private String name;
    private String size;
    private User owner;
    private String access;
    public File() {

    }

    public File(Long fileId, String name, String size, User owner, String access) {
        this.fileId = fileId;
        this.name = name;
        this.size = size;
        this.owner = owner;
        this.access = access;
    }

    public long getId() {
        return fileId;
    }

    public void setId(long fileId) {
        this.fileId = fileId;
    }

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }
}
