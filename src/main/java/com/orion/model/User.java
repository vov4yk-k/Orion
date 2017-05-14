package com.orion.model;

import javax.persistence.*;

/**
 * Created by Vova on 13.05.2017.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private int enabled;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
