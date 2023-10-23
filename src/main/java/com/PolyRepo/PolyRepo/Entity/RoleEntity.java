package com.PolyRepo.PolyRepo.Entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity(name="roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy ="role")
    Set<UserEntity> users;

    public RoleEntity(int id, String name, Set<UserEntity> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public RoleEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }
}
