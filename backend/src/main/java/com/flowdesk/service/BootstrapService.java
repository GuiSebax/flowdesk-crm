package com.flowdesk.service;

import com.flowdesk.domain.TenantEmpresa;
import com.flowdesk.domain.Usuario;
import com.flowdesk.repository.TenantEmpresaRepository;
import com.flowdesk.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BootstrapService {

    private final TenantEmpresaRepository tenantRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario criarAdminInicial(String nomeEmpresa, String nome, String email, String senha) {

        if (usuarioRepository.count() > 0) {
            throw new RuntimeException("Bootstrap já executado");
        }

        // cria tenant
        TenantEmpresa tenant = new TenantEmpresa();
        tenant.setId(UUID.randomUUID());
        tenant.setNome(nomeEmpresa);
        tenant.setStatus("ATIVO");
        tenant.setCreatedAt(LocalDateTime.now());
        tenant.setUpdatedAt(LocalDateTime.now());

        tenantRepository.save(tenant);

        // cria usuário
        Usuario u = new Usuario();
        u.setId(UUID.randomUUID());
        u.setTenantId(tenant.getId());
        u.setNome(nome);
        u.setEmail(email);
        u.setSenhaHash(passwordEncoder.encode(senha));
        u.setAtivo(true);
        u.setCreatedAt(LocalDateTime.now());
        u.setUpdatedAt(LocalDateTime.now());
        u.setRole("ADMIN");

        return usuarioRepository.save(u);
    }
}
