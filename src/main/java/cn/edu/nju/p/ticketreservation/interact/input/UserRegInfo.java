package cn.edu.nju.p.ticketreservation.interact.input;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Range;

public class UserRegInfo {

    @Email
    private String email;

    private String userName;

    private int age;

    @Range(min = 1,max = 2)
    private int sex;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "UserRegInfo{" +
                "email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
