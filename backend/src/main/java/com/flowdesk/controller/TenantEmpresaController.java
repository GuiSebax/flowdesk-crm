package com.flowdesk.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flowdesk.domain.TenantEmpresa;
import com.flowdesk.service.TenantEmpresaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tenant")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TenantEmpresaController {

    private final TenantEmpresaService service;

    @PostMapping
    public TenantEmpresa criar(@RequestParam String nome) {
        return service.criar(nome);

    }

    @GetMapping
    public List<TenantEmpresa> listar() {
        return service.listar();
    }

}
