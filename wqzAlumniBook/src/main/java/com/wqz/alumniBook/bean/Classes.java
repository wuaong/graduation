package com.wqz.alumniBook.bean;

public class Classes {

    private int classesId;
    private String classesNum;
    private String classesName;
    private  String headerURI;
    private  String monitorId;

    public Classes() {
    }

    public Classes(int classesId, String classesNum, String classesName, String headerURI, String monitorId) {
        this.classesId = classesId;
        this.classesNum = classesNum;
        this.classesName = classesName;
        this.headerURI = headerURI;
        this.monitorId = monitorId;
    }

    public int getClassesId() {
        return classesId;
    }

    public void setClassesId(int classesId) {
        this.classesId = classesId;
    }

    public String getClassesNum() {
        return classesNum;
    }

    public void setClassesNum(String classesNum) {
        this.classesNum = classesNum;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public String getHeaderURI() {
        return headerURI;
    }

    public void setHeaderURI(String headerURI) {
        this.headerURI = headerURI;
    }

    public String getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(String monitorId) {
        this.monitorId = monitorId;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "classesId=" + classesId +
                ", classesNum='" + classesNum + '\'' +
                ", classesName='" + classesName + '\'' +
                ", headerURI='" + headerURI + '\'' +
                ", monitorId='" + monitorId + '\'' +
                '}';
    }
}
