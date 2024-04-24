package com.springboot.architectural.service.imp;

import com.springboot.architectural.dto.AccountDto;
import com.springboot.architectural.dto.AccountInfoDto;
import com.springboot.architectural.entity.Account;
import com.springboot.architectural.entity.Role;
import com.springboot.architectural.exception.UsernameIsExistException;
import com.springboot.architectural.mapper.AccountInfoMapper;
import com.springboot.architectural.payload.Request.SignUpRequest;
import com.springboot.architectural.repository.AccountRepository;
import com.springboot.architectural.security.JwtTokenProvider;
import com.springboot.architectural.service.LoginService;
import com.springboot.architectural.service.LoginService2;
import com.springboot.architectural.service.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
public class LoginServiceImp2 implements LoginService2 {
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
    public AccountDto addUser(SignUpRequest signUpRequest) throws UsernameIsExistException {
        Optional<Account> accountOptional = accountRepository.findByUsername(signUpRequest.getUsername());
        if (!accountOptional.isEmpty()) {
            throw new UsernameIsExistException("Username is exist");
        }

        Account account = new Account();
        Role role = roleService.getRoleByName(signUpRequest.getNameRole());
        Random rand = new Random();
        String codeVerify = String.valueOf(rand.nextInt(1000000)).substring(0,6);
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        account.setRoles(roles);
        account.setDisable(false);
        account.setUsername(signUpRequest.getUsername());
        account.setFirstName("");
        account.setLastName("");
        String passwordEncode = passwordEncoder.encode(signUpRequest.getPassword());

        account.setPassword(passwordEncode);

//        account.setVerificationCode(codeVerify);

        System.out.println(account.toString());
        try {
            Account acc = accountRepository.save(account);
//            String bodyMail = "Code: " + codeVerify;
//            emailSenderService.sendSimpleEmail(signUpRequest.getUsername()+ "@student.ptithcm.edu.vn",
//                    "verification_code", bodyMail);
            AccountDto accDto = new AccountDto();
            accDto.setDisable(acc.isDisable());
            accDto.setFirstName(acc.getFirstName());
            accDto.setLastName(acc.getLastName());
            accDto.setUsername(acc.getUsername());
            return accDto;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
