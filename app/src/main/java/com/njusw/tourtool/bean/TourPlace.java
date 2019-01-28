package com.njusw.tourtool.bean;

public class TourPlace {
    private Integer id;
    private String username;
    private String activityName;
    private String area;
    private String specificLocation;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setSpecificLocation(String specificLocation) {
        this.specificLocation = specificLocation;
    }

    public Integer getId() {

        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getActivityName() {
        return activityName;
    }

    public String getArea() {
        return area;
    }

    public String getSpecificLocation() {
        return specificLocation;
    }
}
