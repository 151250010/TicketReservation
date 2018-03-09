package cn.edu.nju.p.ticketreservation.dao.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class Account {

    @NotNull
    @Length(min = 7, max = 7)
    private String id;

    @NotNull
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
