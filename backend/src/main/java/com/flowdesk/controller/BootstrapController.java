package com.flowdesk.controller;

import com.flowdesk.domain.Usuario;
import com.flowdesk.service.BootstrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bootstrap")
@RequiredArgsConstructor
public class BootstrapController {

    private final BootstrapService service;

    @PostMapping("/admin")
    public Usuario criarAdmin(
            @RequestParam String empresa,
            @RequestParam String nome,
            @RequestParam String email,
            @RequestParam String senha) {
        return service.criarAdminInicial(empresa, nome, email, senha);
    }
}