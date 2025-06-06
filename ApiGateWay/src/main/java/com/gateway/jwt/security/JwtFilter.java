package com.gateway.jwt.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.micrometer.common.lang.NonNull;

import static com.gateway.jwt.security.PublicRoutes.PUBLIC_GET;
import static com.gateway.jwt.security.PublicRoutes.PUBLIC_POST;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter  {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();
        String method = request.getMethod();
        System.out.println("➡️ PATH: " + path + "  METHOD: " + method);

        // 1) Verificar si esta ruta es pública según tu PublicRoutes
        boolean isPublicGet = "GET".equalsIgnoreCase(method) &&
                               Arrays.stream(PUBLIC_GET).anyMatch(path::equals);
        boolean isPublicPost = "POST".equalsIgnoreCase(method) &&
                                Arrays.stream(PUBLIC_POST).anyMatch(path::equals);

        if (isPublicGet || isPublicPost) {
            // Si es ruta pública, la dejamos pasar sin intentar validar JWT
            filterChain.doFilter(request, response);
            return;
        }

        // 2) Si no es pública, intentar extraer y validar el JWT
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (jwtUtil.validateToken(token)) {
                String username = jwtUtil.extractUsername(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 3) Continuar con el resto de filtros
        filterChain.doFilter(request, response);
    }
}
