package com.flowdesk.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

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

        if (path.startsWith("/api/usuario/login")) {
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

            chain.doFilter(request, response);

        } finally {
            TenantContext.clear();
        }
    }
}