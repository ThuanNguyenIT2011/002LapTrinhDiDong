package com.springboot.architectural.entity;

import com.springboot.architectural.dto.AccountDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Account")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account {
    @Id
    private String username;
    private String password;

    @Column(nullable = false, length = 20)
    private String firstName;

    @Column(nullable = false, length = 20)
    private String lastName;

    private boolean disable;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Users_roles",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "account")
    private Set<Notify> notifies = new HashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<Item> items = new HashSet<>();
    
    @OneToMany(mappedBy = "account")
    private Set<Regis> regis = new HashSet<>();
  
    public static AccountDto copyAccount(Account account) {
        AccountDto accountNew = new AccountDto();
        accountNew.setUsername(account.getUsername());
    //        accountNew.setFullName(account.getFullName());
        accountNew.setFirstName(account.getFirstName());
        accountNew.setLastName(account.getLastName());

        return accountNew;
    }
}
