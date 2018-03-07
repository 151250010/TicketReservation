package cn.edu.nju.p.ticketreservation.po;


import cn.edu.nju.p.ticketreservation.enums.UserInfoState;
import cn.edu.nju.p.ticketreservation.exception.DbSexIntNotAvailable;

import java.io.Serializable;

public class UserInfo implements Serializable {

    private String email;
    private String userName;
    private UserInfoState infoState;
    private String sex;
    private int age;

    public UserInfo(String email, String userName, UserInfoState infoState, String sex, int age) {
        this.email = email;
        this.userName = userName;
        this.infoState = infoState;
        this.sex = sex;
        this.age = age;
    }

    public UserInfo(cn.edu.nju.p.ticketreservation.dao.entity.UserInfo userInfo) {
        this.email = userInfo.getEmail();
        this.userName = userInfo.getUserName();
        this.age = userInfo.getAge();
        this.infoState = UserInfoState.stateOf(userInfo.getInfoState());
        this.sex = resolveSex(userInfo.getSex());
    }

    public UserInfo() {

    }

    private String resolveSex(int sex) {

        if (sex == 1) {
            return "Male";
        } else if (sex == 2) {
            return "Female";
        } else if (sex == 0) {
            return "Unknown";
        } else {
            throw new DbSexIntNotAvailable(sex + " is not available to resolve for sex !");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserInfoState getInfoState() {
        return infoState;
    }

    public void setInfoState(UserInfoState infoState) {
        this.infoState = infoState;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", infoState=" + infoState +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}
