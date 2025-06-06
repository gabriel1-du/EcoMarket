package com.gateway.jwt.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.gateway.redireccionApis.Carrito.CarritoPublicRoutes;
import com.gateway.redireccionApis.Inventario.InventarioPublicRoutes;
import com.gateway.redireccionApis.MedioDePago.MedioDePagoPublicRoutes;
import com.gateway.redireccionApis.Notificaciones.NotificacionesPublicRoutes;
import com.gateway.redireccionApis.Pedido.PedidoPublicRoutes;
import com.gateway.redireccionApis.Reporte.ReportePulicRoutes;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.http.HttpMethod; // Asegúrate de importar esto arriba

import static com.gateway.jwt.security.PublicRoutes.*; //importa las rutas publicas de jwt
import static com.gateway.redireccionApis.Usuario.UsuarioPublicRoutes.USUARIO_PUBLIC_GET; //Importa las rutas publicas de usuario
import static com.gateway.redireccionApis.Boleta.BoletaPublicRoutes.BOLETA_PUBLIC_GET; //import rutas boletas


@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                // URL públicas JWT
                .requestMatchers(HttpMethod.POST, PUBLIC_POST).permitAll() // rutas publicas POST de PublicRoutes de JWT
                .requestMatchers(HttpMethod.GET, PUBLIC_GET).permitAll() // rutas publicas GET de PublicRoutes de JWT

                // URL públicas API Gestion
                .requestMatchers(HttpMethod.GET, USUARIO_PUBLIC_GET).permitAll()   // lista pública api GESTION GET
                
                .requestMatchers(HttpMethod.GET, BOLETA_PUBLIC_GET).permitAll()  //lista publica la api Boleta
                
                // — RUTAS PÚBLICAS DE CARRITO (GET) — 
                .requestMatchers(HttpMethod.GET, CarritoPublicRoutes.CARRITO_PUBLIC_GET).permitAll()

                // - RUTAS PUBLICAS DE MEDIO DE PAGO
                .requestMatchers(HttpMethod.GET, MedioDePagoPublicRoutes.MEDIOPAGO_PUBLIC_GET).permitAll()

                // - RUTAS PUBLICAS DE PEDIDO
                .requestMatchers(HttpMethod.GET, PedidoPublicRoutes.PEDIDO_PUBLIC_GET).permitAll()

                // - RUTAS PUBLICAS DE REPORTES
                .requestMatchers(HttpMethod.GET, ReportePulicRoutes.PEDIDO_PUBLIC_GET).permitAll()

                // - RUTAS PUBLICAS DE INVENTARIO
                .requestMatchers(HttpMethod.GET, InventarioPublicRoutes.INVENTARIO_PUBLIC_ROUTES).permitAll()

                // - RUTAS PUBLICAS DE NOTIFICACIONES
                .requestMatchers(HttpMethod.GET, NotificacionesPublicRoutes.NOTIFICACIONES_PUBLIC_GET).permitAll()



                // Otras URL Token obligatorio
                .anyRequest().authenticated()

            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
