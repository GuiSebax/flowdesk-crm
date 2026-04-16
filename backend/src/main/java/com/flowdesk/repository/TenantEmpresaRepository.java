package com.flowdesk.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flowdesk.domain.TenantEmpresa;

public interface TenantEmpresaRepository extends JpaRepository<TenantEmpresa, UUID> {

}
