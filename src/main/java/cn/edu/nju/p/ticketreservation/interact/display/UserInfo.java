package cn.edu.nju.p.ticketreservation.interact.display;


import cn.edu.nju.p.ticketreservation.enums.UserInfoState;
import cn.edu.nju.p.ticketreservation.exception.DbSexIntNotAvailable;
import cn.edu.nju.p.ticketreservation.exception.VipScoreNotRecognizedException;

import java.io.Serializable;

public class UserInfo implements Serializable {

    private String email;
    private String userName;
    private UserInfoState infoState;
    private String sex;
    private int age;
    private double score;
    private VipLevel vipLevel;

    public UserInfo(cn.edu.nju.p.ticketreservation.dao.entity.UserInfo userInfo) {
        this.email = userInfo.getEmail();
        this.userName = userInfo.getUserName();
        this.age = userInfo.getAge();
        this.infoState = UserInfoState.stateOf(userInfo.getInfoState());
        this.sex = resolveSex(userInfo.getSex());
        this.score = userInfo.getScore();
        this.vipLevel = VipLevel.getVipLevel(userInfo.getConsumption());
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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public VipLevel getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(VipLevel vipLevel) {
        this.vipLevel = vipLevel;
    }

    public enum VipLevel {

        VIP1,VIP2,VIP3,VIP4, VIP5;

        public static VipLevel getVipLevel(double consumption) {
            double[] levels = new double[]{0, 1000, 2000, 3000, 4000, 100000000};
            for (int i = 0; i < 5; i++) {
                if (consumption >= levels[i] && consumption < levels[i + 1]) {
                    return VipLevel.values()[i];
                }
            }
            throw new VipScoreNotRecognizedException("The value of vip consumption is " + consumption + " ! Can not resolve.");
        }

    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", infoState=" + infoState +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }
}
