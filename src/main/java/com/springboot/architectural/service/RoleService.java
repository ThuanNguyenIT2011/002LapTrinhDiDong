package com.springboot.architectural.service;

import com.springboot.architectural.entity.Role;

public interface RoleService {
    Role getRoleByName(String name);
}
