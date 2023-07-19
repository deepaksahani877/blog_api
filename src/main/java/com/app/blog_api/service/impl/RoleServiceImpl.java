package com.app.blog_api.service.impl;


import com.app.blog_api.entity.Role;
import com.app.blog_api.repository.RoleRepository;
import com.app.blog_api.service.RoleService;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {


    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public Role getRoleByName(String roleName) {
        return roleRepository.findRoleByName(roleName);
    }

}
