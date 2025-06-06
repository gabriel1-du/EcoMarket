package com.gateway.redireccionApis.Notificaciones;

public class NotificacionesPublicRoutes {

    public static final String[] NOTIFICACIONES_PUBLIC_GET = {
        "/api/proxy/notificaciones",       // coincide con GET EXACTO a /api/proxy/medio-pago
        "/api/proxy/notificaciones/**"
    };


}
