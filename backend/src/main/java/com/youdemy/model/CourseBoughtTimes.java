package com.youdemy.model;

public class CourseBoughtTimes {

    private String courseTitle;
    private long boughtTimes;

    public CourseBoughtTimes() {}

    public CourseBoughtTimes(String courseTitle, long boughtTimes) {
        this.courseTitle = courseTitle;
        this.boughtTimes = boughtTimes;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public long getBoughtTimes() {
        return boughtTimes;
    }

    public void setBoughtTimes(long boughtTimes) {
        this.boughtTimes = boughtTimes;
    }
}
