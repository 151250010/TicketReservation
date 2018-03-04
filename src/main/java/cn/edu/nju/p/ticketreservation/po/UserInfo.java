package cn.edu.nju.p.ticketreservation.po;


import java.io.Serializable;

public class UserInfo implements Serializable {

    private String email;

    public UserInfo(String emailByUsername) {
        this.email = emailByUsername;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
