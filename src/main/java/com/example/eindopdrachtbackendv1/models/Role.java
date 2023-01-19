package com.example.eindopdrachtbackendv1.models;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    private String rolename;

    public Role() {

    }

    public Role(String rolename) {
        this.rolename = rolename;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}


