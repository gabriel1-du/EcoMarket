package com.gateway.redireccionApis.Envio;

public class EnvioPublicRoutes {

    public static final String[] ENVIOS_PUBLIC_GET = {
        "/api/proxy/envios",       // coincide con GET EXACTO a /api/proxy/carrito
        "/api/proxy/envios/**"
    };
}
