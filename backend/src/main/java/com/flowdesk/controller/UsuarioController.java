package com.flowdesk.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flowdesk.domain.Usuario;
import com.flowdesk.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping
    public Usuario criar(
            @RequestParam String nome,
            @RequestParam String email) {
        return service.criar(nome, email);
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String senha) {
        return service.login(email, senha);
    }

}
