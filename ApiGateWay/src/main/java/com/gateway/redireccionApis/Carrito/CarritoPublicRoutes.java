package com.gateway.redireccionApis.Carrito;

public class CarritoPublicRoutes {

    public static final String[] CARRITO_PUBLIC_GET = {
        "/api/proxy/carrito",       // coincide con GET EXACTO a /api/proxy/carrito
        "/api/proxy/carrito/**"
    };

}
