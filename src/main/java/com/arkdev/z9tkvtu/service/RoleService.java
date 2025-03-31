package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.dto.Request.RoleRequest;
import com.arkdev.z9tkvtu.dto.Response.RoleResponse;
import com.arkdev.z9tkvtu.mapper.RoleMapper;
import com.arkdev.z9tkvtu.model.Role;
import com.arkdev.z9tkvtu.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;

    public List<RoleResponse> getRoles() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toRoleResponse).toList();
    }

    public RoleResponse getRole(Integer roleId) {
        return roleRepository.findById(roleId)
                .map(roleMapper::toRoleResponse)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    public void addRole(RoleRequest request) {
        Role role = roleRepository.findByRoleType(request.getRoleType()).orElse(null);
        if (role != null)
            throw new RuntimeException("Role already exists");
        role = roleMapper.toRole(request);
        roleRepository.save(role);
    }

    public void updateRole(Integer roleId, RoleRequest request) {
        Role role = roleRepository.findById(roleId)
                .map(item -> {
                    roleMapper.updateRole(item, request);
                    return item;
                }).orElseThrow(() -> new RuntimeException("Role not found")
        );
        roleRepository.save(role);
    }

    public void deleteRole(Integer roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(
                () -> new RuntimeException("Role not found")
        );
        roleRepository.delete(role);
    }
}
