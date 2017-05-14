package com.orion.model;

import javax.persistence.*;

/**
 * Created by Vova on 13.05.2017.
 */
@Entity
@Table(name = "groups")
public class Group {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @Column(name = "group_name")
    private String group_name;
}
