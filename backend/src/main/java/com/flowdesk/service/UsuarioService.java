package com.flowdesk.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.flowdesk.domain.Usuario;
import com.flowdesk.repository.UsuarioRepository;
import com.flowdesk.security.JwtService;
import com.flowdesk.security.TenantContext;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @PreAuthorize("hasRole('ADMIN')")
    public Usuario criar(String nome, String email) {

        String tenantId = TenantContext.getTenant();

        if (tenantId == null) {
            throw new RuntimeException("Tenant não identificado. Faça login primeiro.");
        }

        if (repository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        Usuario u = new Usuario();
        u.setId(UUID.randomUUID());
        u.setTenantId(UUID.fromString(tenantId));
        u.setNome(nome);
        u.setEmail(email);
        u.setSenhaHash(passwordEncoder.encode("123456"));
        u.setAtivo(true);
        u.setCreatedAt(LocalDateTime.now());
        u.setUpdatedAt(LocalDateTime.now());
        u.setRole("USER");

        return repository.save(u);
    }

    public String login(String email, String senha) {
        Usuario usuario = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(senha, usuario.getSenhaHash())) {
            throw new RuntimeException("Senha inválida");
        }

        return jwtService.gerarToken(usuario.getEmail(), usuario.getTenantId().toString(), usuario.getRole());
    }

    public List<Usuario> listar() {
        String tenantId = TenantContext.getTenant();

        return repository.findAllByTenantId(UUID.fromString(tenantId));
    }

}
