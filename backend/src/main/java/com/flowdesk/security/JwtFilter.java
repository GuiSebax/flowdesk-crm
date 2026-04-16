package com.flowdesk.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter implements Filter {

    private final JwtService jwtService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI();

        if (path.startsWith("/api/usuario/login") || path.startsWith("/api/bootstrap")) {
            chain.doFilter(request, response);
            return;
        }

        try {
            String authHeader = req.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                res.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }

            String token = authHeader.substring(7);

            if (!jwtService.validarToken(token)) {
                res.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }

            String tenantId = jwtService.extrairTenantId(token);
            TenantContext.setTenant(tenantId);

            String role = jwtService.extrairRole(token);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    null,
                    null,
                    List.of(new SimpleGrantedAuthority("ROLE_" + role)));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request, response);

        } finally {
            TenantContext.clear();
            SecurityContextHolder.clearContext();
        }
    }
}