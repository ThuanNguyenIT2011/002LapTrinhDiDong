package com.springboot.architectural.security;

import com.springboot.architectural.entity.Account;
import com.springboot.architectural.repository.AccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private AccountRepository accountRepository;


    public CustomUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = accountRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email: "+ username));

        Set<GrantedAuthority> authorities = user
                .getRoles()
                .stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                authorities);
    }
}
