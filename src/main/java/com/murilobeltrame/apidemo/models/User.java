package com.murilobeltrame.apidemo.models;

import javax.persistence.*;

@Entity
@Table(name="user")
public class  User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String name;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
