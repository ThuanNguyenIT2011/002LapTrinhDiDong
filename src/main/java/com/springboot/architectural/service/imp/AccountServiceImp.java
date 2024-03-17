package com.springboot.architectural.service.imp;

import com.springboot.architectural.entity.Account;
import com.springboot.architectural.entity.Role;
import com.springboot.architectural.payload.Request.SignUpRequest;
import com.springboot.architectural.repository.AccountRepository;
import com.springboot.architectural.service.AccountService;
import com.springboot.architectural.service.RoleService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountServiceImp implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean addUser(SignUpRequest signUpRequest) {
        Account account = new Account();
        Role role = roleService.getRoleByName(signUpRequest.getNameRole());

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        account.setRoles(roles);
        account.setEnable(true);
        account.setUsername(signUpRequest.getUsername());

        String passwordEncode = passwordEncoder.encode(signUpRequest.getPassword());

        account.setPassword(passwordEncode);

        try {
            accountRepository.save(account);
            return true;
        } catch (Exception ex) {

            return false;
        }
    }

    @Override
    public boolean checkLogin(String userName, String password) {
        Account account = accountRepository.findByUsername(userName).get();
        return passwordEncoder.matches(password, account.getPassword());
    }
}
