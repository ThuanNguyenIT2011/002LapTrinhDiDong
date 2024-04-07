package com.springboot.architectural.service.imp;

import com.springboot.architectural.entity.Account;
import com.springboot.architectural.entity.Role;
import com.springboot.architectural.payload.Request.SignUpRequest;
import com.springboot.architectural.repository.AccountRepository;
import com.springboot.architectural.security.JwtTokenProvider;
import com.springboot.architectural.service.LoginService;
import com.springboot.architectural.service.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;
import java.util.Random;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
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

    @Autowired
    private  EmailSenderService emailSenderService;

    @Override
    public boolean addUser(SignUpRequest signUpRequest) {
        Account account = new Account();
        Role role = roleService.getRoleByName(signUpRequest.getNameRole());

        Random rand = new Random();
        String codeVerify = String.valueOf(rand.nextInt(1000000)).substring(0,6);

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        account.setRoles(roles);
        account.setDisable(true);
        account.setUsername(signUpRequest.getUsername());
        account.setFirstName(signUpRequest.getFirstName());
        account.setLastName(signUpRequest.getLastName());
        String passwordEncode = passwordEncoder.encode(signUpRequest.getPassword());

        account.setPassword(passwordEncode);

        account.setVerificationCode(codeVerify);

        System.out.println(account.toString());
        try {
            accountRepository.save(account);
            String bodyMail = "Code: " + codeVerify;
            emailSenderService.sendSimpleEmail(signUpRequest.getUsername()+ "@student.ptithcm.edu.vn",
                    "verification_code", bodyMail);
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean checkLogin(String userName, String password) {

       Account  account = accountRepository.findByUsername(userName).get();

       return passwordEncoder.matches(password, account.getPassword()) && !account.isDisable();
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

    @Override
    public boolean verifyCode(String code, String username) throws AccountNotFoundException {
        Optional<Account> accountOptional = accountRepository.findByUsername(username);
        if (accountOptional.isEmpty()) {
            throw new AccountNotFoundException("Username notfound");
        }
        Account account = accountOptional.get();
        if (account.getVerificationCode().equals(code)) {
            // update disable
            accountRepository.updateDisableAccount(username, false);
            return true;
        }
        return false;
    }
}
