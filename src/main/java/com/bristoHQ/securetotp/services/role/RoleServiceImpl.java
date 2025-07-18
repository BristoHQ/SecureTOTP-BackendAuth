package com.bristoHQ.securetotp.services.role;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bristoHQ.securetotp.models.role.Role;
import com.bristoHQ.securetotp.models.role.RoleName;
import com.bristoHQ.securetotp.repositories.RoleRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository iRoleRepository;

    @Override
    public Role saveRole(Role role) {
        return iRoleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return iRoleRepository.findAll();
    }

    @Override
    public Role getRoleById(Long id) {
        return iRoleRepository.findById(id);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return iRoleRepository.findByRoleName(roleName);
    }

    @Override
    public List<Role> getRoleByRoleName(RoleName roleName) {
        return iRoleRepository.findByRoleName(roleName);
    }
}