package com.springboot.architectural.service.imp;

import com.springboot.architectural.entity.Role;
import com.springboot.architectural.repository.RoleRepository;
import com.springboot.architectural.service.RoleService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role getRoleByName(String name) {
        Role role = roleRepository.findByName(name);
        return role;
    }
}
