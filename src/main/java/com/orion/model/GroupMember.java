package com.orion.model;

import javax.persistence.*;

/**
 * Created by Vova on 13.05.2017.
 */
@Entity
@Table(name = "group_members")
public class GroupMember {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name="username", referencedColumnName = "username")
    private User username;

    @OneToOne(targetEntity = Group.class)
    @JoinColumn(name="group_id", referencedColumnName = "id")
    private Group group;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public boolean newEntity(){
        return id == null;
    }
}
