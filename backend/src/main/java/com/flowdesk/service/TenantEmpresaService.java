package com.flowdesk.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.flowdesk.domain.TenantEmpresa;
import com.flowdesk.repository.TenantEmpresaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TenantEmpresaService {

    private final TenantEmpresaRepository repository;

    public TenantEmpresa criar(String nome) {
        TenantEmpresa t = new TenantEmpresa();
        t.setId(UUID.randomUUID());
        t.setNome(nome);
        t.setStatus("ATIVO");
        t.setCreatedAt(LocalDateTime.now());
        t.setUpdatedAt(LocalDateTime.now());

        return repository.save(t);
    }

    public List<TenantEmpresa> listar() {
        return repository.findAll();
    }

}
