package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.dto.Request.PermissionRequest;
import com.arkdev.z9tkvtu.dto.Response.PermissionResponse;
import com.arkdev.z9tkvtu.mapper.PermissionMapper;
import com.arkdev.z9tkvtu.model.Permission;
import com.arkdev.z9tkvtu.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public List<PermissionResponse> getPermissions() {
        return permissionRepository.findAll()
                .stream()
                .map(permissionMapper::toPermissionResponse)
                .toList();
    }

    public PermissionResponse getPermission(Integer permissionId) {
        return permissionRepository.findById(permissionId)
                .map(permissionMapper::toPermissionResponse)
                .orElseThrow(() -> new RuntimeException("Permission not found"));
    }

    public void addPermission(PermissionRequest request) {
        Permission permission = permissionRepository.findByPermissionName(request.getPermissionName())
                .orElse(null);
        if (permission != null)
            throw new RuntimeException("Permission already exists");
        permission = permissionMapper.toPermission(request);
        permissionRepository.save(permission);
    }

    public void updatePermission(Integer permissionId, PermissionRequest request) {
        Permission permission = permissionRepository.findById(permissionId)
                .map(item -> {
                    permissionMapper.updatePermission(item, request);
                    return item;
                }).orElseThrow(() -> new RuntimeException("Permission not found"));
        permissionRepository.save(permission);
    }

    public void deletePermission(Integer permissionId) {
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new RuntimeException("Permission not found"));
        permissionRepository.delete(permission);
    }
}
