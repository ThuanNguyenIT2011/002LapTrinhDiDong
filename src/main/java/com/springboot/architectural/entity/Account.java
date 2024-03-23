package com.springboot.architectural.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    private String username;
    private String password;

    private String first_name;
    private String last_name;

    private boolean enable;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "account")
    private Set<Notify> notifies = new HashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<Item> items = new HashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<Regis> regis = new HashSet<>();


}
