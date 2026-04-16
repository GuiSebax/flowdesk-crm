package com.flowdesk.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flowdesk.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByEmailAndTenantId(String email, UUID tenantId);

    List<Usuario> findAllByTenantId(UUID tenantId);
}