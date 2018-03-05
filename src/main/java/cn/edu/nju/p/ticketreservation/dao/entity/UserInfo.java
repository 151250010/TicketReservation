package cn.edu.nju.p.ticketreservation.dao.entity;

public class UserInfo {

    private String userName;
    private String email;
    private int age;
    private int infoState;
    private int sex;

    public UserInfo(String userName, String email, int age, int infoState, int sex) {
        this.userName = userName;
        this.email = email;
        this.age = age;
        this.infoState = infoState;
        this.sex = sex;
    }

    public UserInfo() {

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

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", infoState=" + infoState +
                ", sex=" + sex +
                '}';
    }
}
