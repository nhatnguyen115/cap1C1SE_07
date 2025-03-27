package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.dto.Request.ModuleRequest;
import com.arkdev.z9tkvtu.dto.Response.ModuleResponse;
import com.arkdev.z9tkvtu.mapper.ModuleMapper;
import com.arkdev.z9tkvtu.model.Module;
import com.arkdev.z9tkvtu.repository.ModuleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ModuleService {
    ModuleRepository moduleRepository;
    ModuleMapper moduleMapper;

    public List<ModuleResponse> getModules() {
        return moduleRepository.findAll()
                .stream()
                .map(moduleMapper::toModuleResponse)
                .toList();
    }

    public ModuleResponse getModule(Integer moduleId) {
        return moduleRepository.findById(moduleId)
                .map(moduleMapper::toModuleResponse)
                .orElseThrow(() -> new RuntimeException("Module not found"));
    }

    public void addModule(ModuleRequest request) {
        Module module = moduleRepository.findByModuleType(request.getModuleType())
                .orElse(null);
        if (module != null)
            throw new RuntimeException("Module already exists");
        module = moduleMapper.toModule(request);
        moduleRepository.save(module);
    }

    public void updateModule(Integer moduleId, ModuleRequest request) {
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new RuntimeException("Module not found"));
        moduleMapper.UpdateModule(module, request);
        moduleRepository.save(module);
    }

    public void deleteModule(Integer moduleId) {
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new RuntimeException("Module not found"));
        moduleRepository.delete(module);
    }
}
