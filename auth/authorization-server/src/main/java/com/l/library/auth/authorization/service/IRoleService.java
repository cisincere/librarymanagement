package com.l.library.auth.authorization.service;

import com.l.library.auth.authorization.entity.Role;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface IRoleService {
    Set<Role> queryUserRolesByUserId(String userId);
}
