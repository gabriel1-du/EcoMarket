package com.gateway.redireccionApis.MedioDePago;

public class MedioDePagoPublicRoutes {

    public static final String[] MEDIOPAGO_PUBLIC_GET = {
        "/api/proxy/medio-pago",       // coincide con GET EXACTO a /api/proxy/medio-pago
        "/api/proxy/medio-pago/",
        "/api/proxy/medio-pago/**"
    };

}

