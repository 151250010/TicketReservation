package cn.edu.nju.p.ticketreservation.dao.entity;

import cn.edu.nju.p.ticketreservation.enums.UserInfoState;
import cn.edu.nju.p.ticketreservation.interact.input.UserRegInfo;

public class UserInfo {

    private String userName;
    private String email;
    private int age;
    private int infoState;
    private int sex;
    private double score;
    private double consumption;

    public UserInfo() {

    }

    public UserInfo(UserRegInfo info) {
        this.userName = info.getUserName();
        this.age = info.getAge();
        this.sex = info.getSex();
        this.email = info.getEmail();
        this.infoState = UserInfoState.USER_NORMAL.getUserState();
        this.score = 0.0;
        this.consumption = 0;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getInfoState() {
        return infoState;
    }

    public void setInfoState(int infoState) {
        this.infoState = infoState;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", infoState=" + infoState +
                ", sex=" + sex +
                ", score=" + score +
                ", consumption=" + consumption +
                '}';
    }
}
