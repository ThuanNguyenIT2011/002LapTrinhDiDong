package com.springboot.architectural.service.imp;

import com.springboot.architectural.entity.Account;
import com.springboot.architectural.entity.Role;
import com.springboot.architectural.payload.Request.SignUpRequest;
import com.springboot.architectural.repository.AccountRepository;
import com.springboot.architectural.security.JwtTokenProvider;
import com.springboot.architectural.service.LoginService;
import com.springboot.architectural.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class LoginServiceImp implements LoginService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

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

    @Override
    public String login(String userName, String password) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userName, password
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
//         = new JwtTokenProvider();
        String token =  jwtTokenProvider.generateToken(authentication);

        return token;
    }
}
