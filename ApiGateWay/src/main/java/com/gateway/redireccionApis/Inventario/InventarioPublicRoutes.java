package com.gateway.redireccionApis.Inventario;

public class InventarioPublicRoutes {

    public static final String[] INVENTARIO_PUBLIC_ROUTES = {
    "/api/proxy/inventario",       // Acceso exacto
    "/api/proxy/inventario/**"     // Acceso a cualquier subruta: productos, categorías, asociaciones, etc.
    };

}
